package br.com.alura.exception;

public class ThreadExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Exception on thread " + t + " - Message: " + e.getMessage());
    }
}
