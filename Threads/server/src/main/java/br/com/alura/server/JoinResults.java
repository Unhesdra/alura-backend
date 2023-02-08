package br.com.alura.server;

import java.io.PrintStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class JoinResults implements Runnable {

    private Future<String> futureWS;
    private Future<String> futureDB;
    private PrintStream commandResponse;

    public JoinResults(Future<String> futureWS, Future<String> futureDB, PrintStream commandResponse) {
        this.futureWS = futureWS;
        this.futureDB = futureDB;
        this.commandResponse = commandResponse;
    }

    @Override
    public void run() {
        System.out.println("Waiting Future WS and DB results");
        try {
            String resultWS = futureWS.get(15, TimeUnit.SECONDS);
            String resultDB = futureDB.get(15, TimeUnit.SECONDS);
            commandResponse.println("Result from command: " + resultWS + " - " + resultDB);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            commandResponse.println("Command timed out");
            futureWS.cancel(true);
            futureDB.cancel(true);
        }
    }
}
