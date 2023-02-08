package br.com.alura.client.runnable;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class SendCommand implements Runnable {

    private Socket socket;

    public SendCommand(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            Scanner inputScanner = new Scanner(System.in);
            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);

            while(inputScanner.hasNextLine()) {
                String command = inputScanner.nextLine();
                if(command.trim().equals(""))
                {
                    break;
                }
                printStream.println(command);
            }
            outputStream.close();
            inputScanner.close();
            printStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
