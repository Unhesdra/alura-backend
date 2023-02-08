package br.com.alura.server.command;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;

public class CommandC1 implements Runnable{

    private PrintStream commandResponse;

    public CommandC1(PrintStream commandResponse) {
        this.commandResponse = commandResponse;
    }

    @Override
    public void run() {
        System.out.println("Start executing C1");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        commandResponse.println("Command C1 successfully executed");
    }
}
