package br.com.alura.sync.task;

public class Bathroom {

    private boolean isDirty = true;

    public void doNumberOne() {
        String threadName = Thread.currentThread().getName();

        System.out.println(threadName + ": knocking on door");

        synchronized (this) {
            System.out.println(threadName + ": going inside the bathroom");
            while(isDirty) {
                waitOutside(threadName);
            }
            System.out.println(threadName + ": going number ONE");
            useToilet(2000L);
            isDirty = true;
            System.out.println(threadName + ": flushing");
            System.out.println(threadName + ": washing hands");
            System.out.println(threadName + ": going out the bathroom");
        }
    }



    public void doNumberTwo() {
        String threadName = Thread.currentThread().getName();

        System.out.println(threadName + ": knocking on door");

        synchronized (this) {
            System.out.println(threadName + ": going inside the bathroom");
            while(isDirty) {
                waitOutside(threadName);
            }
            System.out.println(threadName + ": going number TWO");
            useToilet(5000L);
            isDirty = true;
            System.out.println(threadName + ": flushing");
            System.out.println(threadName + ": washing hands");
            System.out.println(threadName + ": going out the bathroom");
        }
    }

    public void cleanBathroom() {
        String threadName = Thread.currentThread().getName();

        System.out.println(threadName + ": knocking on door");

        synchronized (this) {
            System.out.println(threadName + ": going inside the bathroom");
            if(!isDirty) {
                System.out.println(threadName + ": bathroom is clean, leaving the bathroom");
            }
            System.out.println(threadName + ": cleaning the bathroom");
            useToilet(10000L);
            isDirty = false;
            System.out.println(threadName + ": going out the bathroom");
            this.notifyAll();
        }
    }

    private static void useToilet(Long timeMs) {
        try {
            Thread.sleep(timeMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitOutside(String threadName) {
        try {
            System.out.println(threadName + ": bathroom is dirty, waiting outside");
            this.wait();
            System.out.println(threadName + ": going back inside the bathroom");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
