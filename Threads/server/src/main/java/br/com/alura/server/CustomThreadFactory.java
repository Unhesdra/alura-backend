package br.com.alura.server;

import br.com.alura.exception.ThreadExceptionHandler;

import java.util.concurrent.ThreadFactory;

public class CustomThreadFactory implements ThreadFactory {

    private static Integer threadNumber;

    public CustomThreadFactory() {
        threadNumber = 0;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread  thread = new Thread(r, "TaskServer Thread " + threadNumber);
        threadNumber++;
        thread.setUncaughtExceptionHandler(new ThreadExceptionHandler());
        return thread;
    }
}
