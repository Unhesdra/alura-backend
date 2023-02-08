package br.com.alura.list.task;

public class ListTask {
    private String[] elements = new String[100];
    private int index = 0;

    //Notice that it is defined that the method is synchronized in order to work in multiple threads.
    //Were it not synchronized, some elements could be overwritten before the index were incremented.
    public synchronized void addElements(String element) {
        this.elements[index] = element;
        this.index++;

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(index == elements.length) {
            System.out.println("All indexes of the list have an element assigned to them. It is ok to print the list now");
            this.notify();
        }
    }

    public int size() {
        return this.elements.length;
    }

    public String getElement(int position) {
        return this.elements[position];
    }
}
