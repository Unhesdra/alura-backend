package br.com.alura.threads.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextSearch implements Runnable {

    private String fileName;
    private String name;

    public TextSearch(String fileName, String name) {
        this.fileName = fileName;
        this.name = name.toLowerCase();
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            Integer lineNumber = 1;

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.toLowerCase().contains(name)) {
                    System.out.println(fileName + " - " + line + " - " + lineNumber);
                }
                lineNumber++;
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
