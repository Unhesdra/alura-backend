package br.com.alura.sync.action;

import br.com.alura.sync.task.Bathroom;

public class GoNumberTwo implements Runnable {
    private Bathroom bathroom;

    public GoNumberTwo(Bathroom bathroom) {
        this.bathroom = bathroom;
    }

    @Override
    public void run() {
        bathroom.doNumberTwo();
    }
}
