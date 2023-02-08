package br.com.alura.list.runnable;

import br.com.alura.list.task.ListTask;

public class PrintListElements implements Runnable {
    private ListTask list;

    public PrintListElements(ListTask list) {
        this.list = list;
    }

    @Override
    public void run() {
        synchronized (list) {
            try {
                //It is important to be sure, that the wait() method is called before the notify()/notifyAll() method
                //Otherwise the wait() method will wait forever
                list.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for(int i = 0; i < list.size(); i++) {
                System.out.println(i + " - " + list.getElement(i));
            }
        }
    }
}
