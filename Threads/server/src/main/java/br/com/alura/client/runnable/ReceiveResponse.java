package br.com.alura.client.runnable;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ReceiveResponse implements Runnable{

    private Socket socket;

    public ReceiveResponse(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            Scanner serverResponse = new Scanner(socket.getInputStream());
            while(serverResponse.hasNextLine()) {
                String response = serverResponse.nextLine();
                System.out.println(response);
            }

            serverResponse.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
