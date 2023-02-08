package br.com.alura.threads;

import br.com.alura.threads.actions.TextSearch;

public class main {

    public static void main(String[] args) {
        String name = "Jeff";

        Thread signatureThread1 = new Thread(new TextSearch("assinaturas1.txt", name));
        Thread signatureThread2 = new Thread(new TextSearch("assinaturas2.txt", name));
        Thread authorThread = new Thread(new TextSearch("autores.txt", name));

        signatureThread1.start();
        signatureThread2.start();
        authorThread.start();
    }
}
