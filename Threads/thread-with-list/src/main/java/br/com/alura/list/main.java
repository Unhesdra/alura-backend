package br.com.alura.list;

import br.com.alura.list.runnable.AddElementToList;
import br.com.alura.list.runnable.PrintListElements;
import br.com.alura.list.task.ListTask;

public class main {
    public static void main(String[] args) throws InterruptedException {

        ListTask list = new ListTask();

        for(int i = 0; i < 10; i++) {
            Thread thread = new Thread(new AddElementToList(list, i));
            thread.start();
        }

//        Thread.sleep(2000);

        System.out.println("Printing list elements");
        new Thread(new PrintListElements(list)).start();

        //Doing the same thing but using a Java.Util object
//        List<String> javaList = new Vector<>();
//
//        for(int i = 0; i < 10; i++) {
//            Thread thread = new Thread(new AddElementToVector(javaList, i));
//            thread.start();
//        }
//
////        Thread.sleep(2000);
//
//        System.out.println("\nPrinting Java.Util list elements");
//        for(int i = 0; i < javaList.size(); i++) {
//            System.out.println(i + " - " + javaList.get(i));
//        }
    }
}
