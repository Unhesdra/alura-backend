package br.com.alura.server;

import br.com.alura.server.command.CommandAccessDB;
import br.com.alura.server.command.CommandC1;
import br.com.alura.server.command.CommandCallWS;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class DistributeTasks implements Runnable{
    private ExecutorService threadPool;
    private Socket socket;
    private BlockingQueue<String> commandQueue;
    private TaskServer taskServer;

    public DistributeTasks(ExecutorService threadPool, Socket socket, BlockingQueue<String> commandQueue, TaskServer taskServer) {
        this.threadPool = threadPool;
        this.socket = socket;
        this.commandQueue = commandQueue;
        this.taskServer = taskServer;
    }

    @Override
    public void run() {
        System.out.println("Distributing tasks for " + socket);

        try {
            Scanner scanner = new Scanner(socket.getInputStream());
            PrintStream commandResponse = new PrintStream(socket.getOutputStream());

            while(scanner.hasNextLine()) {
                String command = scanner.nextLine();
                System.out.println("Received command: "+ command);

                switch(command) {
                    case "c1": {
                        commandResponse.println("Command c1 confirmed");
                        CommandC1 c1 = new CommandC1(commandResponse);
                        threadPool.execute(c1);
                        break;
                    }
                    case "c2": {
                        commandResponse.println("Command c2 confirmed");
                        CommandCallWS c2WS = new CommandCallWS(commandResponse);
                        CommandAccessDB c2DB = new CommandAccessDB(commandResponse);
                        Future<String> futureWS = threadPool.submit(c2WS);
                        Future<String> futureDB = threadPool.submit(c2DB);

                        threadPool.submit(new JoinResults(futureWS, futureDB, commandResponse));

                        String resultWS = futureWS.get();
                        String resultDB = futureDB.get();
                        break;
                    }
                    case "c3": {
                        commandQueue.put(command);
                        commandResponse.println("Command c3 added to queue");
                        break;
                    }
                    case "end": {
                        taskServer.stop();
                        break;
                    }
                    default: {
                        commandResponse.println("Command not found");
                    }
                }
            }
            commandResponse.close();
            scanner.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
