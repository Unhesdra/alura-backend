package br.com.alura.list.runnable;

import java.util.List;

public class AddElementToVector implements Runnable {
    private List<String> list;
    private int threadNumber;

    public AddElementToVector(List<String> list, int threadNumber) {
        this.list = list;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            list.add("Thread: " + threadNumber + " Element: " + i);
        }
    }
}
