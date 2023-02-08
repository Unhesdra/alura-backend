package br.com.alura.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaskServer {

    private Integer port;
    private Integer commandCapacity;
    private ServerSocket serverSocket;
    private ExecutorService threadPool;
    private BlockingQueue<String> commandQueue;
//    private volatile boolean isRunning;
    private AtomicBoolean isRunning;

    public TaskServer() throws IOException {
        this.port = 55555;
        this.commandCapacity = 2;
        System.out.println("--- Initializing server on port " + port + " ---");
        this.serverSocket = new ServerSocket(port);
        this.threadPool = Executors.newFixedThreadPool(4, new CustomThreadFactory()); //newCachedThreadPool();
        this.commandQueue =  new ArrayBlockingQueue<>(commandCapacity);
        this.isRunning = new AtomicBoolean(true);
    }

    public static void main(String[] args) throws IOException {

        TaskServer taskServer = new TaskServer();
        taskServer.start();
    }

    public void start() throws IOException {
        while(isRunning.get()) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("--- New client accepted on port " + socket.getPort() + " ---");

                DistributeTasks distributeTasks = new DistributeTasks(threadPool, socket, commandQueue, this);
                threadPool.execute(distributeTasks);
            }
            catch (SocketException e) {
                System.out.println("Waiting for all clients to disconnect");
            }
        }
    }

    public void stop() throws IOException {
        System.out.println("--- Server stopping --- ");
        isRunning.set(false);
        serverSocket.close();
        threadPool.shutdown();
    }
}
