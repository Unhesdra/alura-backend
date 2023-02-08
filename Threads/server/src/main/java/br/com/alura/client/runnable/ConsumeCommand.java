package br.com.alura.client.runnable;

import java.util.concurrent.BlockingQueue;

public class ConsumeCommand implements Runnable {

    private BlockingQueue<String> commandQueue;

    public ConsumeCommand(BlockingQueue<String> commandQueue) {
        this.commandQueue = commandQueue;
    }

    @Override
    public void run() {

        try {
            String command = commandQueue.take();
            System.out.println("Consuming command: " + command);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
