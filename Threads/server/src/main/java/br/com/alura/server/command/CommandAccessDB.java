package br.com.alura.server.command;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class CommandAccessDB implements Callable<String> {

    private PrintStream commandResponse;

    public CommandAccessDB(PrintStream commandResponse) {
        this.commandResponse = commandResponse;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Start executing Database Access");
        commandResponse.println("Processing Database Access");

        Thread.sleep(10000);
        Integer number = new Random().nextInt(100) + 1;

        System.out.println("Finishing Database Access");
        commandResponse.println("Database Access successfully executed");

        return number.toString();
    }
}
