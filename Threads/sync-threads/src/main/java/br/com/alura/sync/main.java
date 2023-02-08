package br.com.alura.sync;

import br.com.alura.sync.action.Cleaning;
import br.com.alura.sync.action.GoNumberOne;
import br.com.alura.sync.action.GoNumberTwo;
import br.com.alura.sync.task.Bathroom;

public class main {
    public static void main(String[] args) {

        Bathroom bathroom = new Bathroom();

        Thread guyOne = new Thread(new GoNumberOne(bathroom), "Guy ONE");
        Thread guyTwo = new Thread(new GoNumberTwo(bathroom), "Guy TWO");
        Thread cleaning = new Thread(new Cleaning(bathroom), "Cleaning");
        cleaning.setDaemon(true);

        guyOne.start();
        guyTwo.start();
        cleaning.start();
    }
}
