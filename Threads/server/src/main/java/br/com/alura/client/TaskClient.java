package br.com.alura.client;

import br.com.alura.client.runnable.ReceiveResponse;
import br.com.alura.client.runnable.SendCommand;

import java.io.IOException;
import java.net.Socket;

public class TaskClient {

    public static void main(String[] args) throws IOException {

        String host = "localhost";
        Integer port = 55555;

        Socket socket = new Socket(host, port);

        System.out.println("Connection established");

        Thread sendCommandThread = new Thread(new SendCommand(socket));
        Thread receiveResponseThread = new Thread(new ReceiveResponse(socket));

        sendCommandThread.start();
        receiveResponseThread.start();

        try {
            sendCommandThread.join();
        } catch(InterruptedException e) {
            throw new RuntimeException(e);
        }

        socket.close();
    }
}
