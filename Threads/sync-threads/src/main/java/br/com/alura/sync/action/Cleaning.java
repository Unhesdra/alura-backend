package br.com.alura.sync.action;

import br.com.alura.sync.task.Bathroom;

public class Cleaning implements Runnable {
    private Bathroom bathroom;

    public Cleaning(Bathroom bathroom) {
        this.bathroom = bathroom;
    }

    @Override
    public void run() {
        while(true) {
            bathroom.cleanBathroom();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
