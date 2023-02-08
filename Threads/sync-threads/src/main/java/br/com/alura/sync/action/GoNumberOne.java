package br.com.alura.sync.action;

import br.com.alura.sync.task.Bathroom;

public class GoNumberOne implements Runnable {
    private Bathroom bathroom;

    public GoNumberOne(Bathroom bathroom) {
        this.bathroom = bathroom;
    }

    @Override
    public void run() {
        bathroom.doNumberOne();
    }
}
