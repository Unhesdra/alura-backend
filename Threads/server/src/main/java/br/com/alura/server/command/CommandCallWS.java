package br.com.alura.server.command;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class CommandCallWS implements Callable<String> {

    private PrintStream commandResponse;

    public CommandCallWS(PrintStream commandResponse) {
        this.commandResponse = commandResponse;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Start executing WebService Call");
        commandResponse.println("Processing WebService Call");

        Thread.sleep(12000);
        Integer number = new Random().nextInt(100) + 1;

        System.out.println("Finishing WebService Call");
        commandResponse.println("WebService Call successfully executed");

        return number.toString();
    }
}
