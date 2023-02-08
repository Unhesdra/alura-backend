package br.com.alura.list.runnable;

import br.com.alura.list.task.ListTask;

public class AddElementToList implements Runnable {
    private ListTask list;
    private int threadNumber;

    public AddElementToList(ListTask list, int threadNumber) {
        this.list = list;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            list.addElements("Thread: " + threadNumber + " Element: " + i);
        }
    }
}
