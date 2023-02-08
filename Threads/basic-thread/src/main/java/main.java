import action.NonOptimalMultiplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {

    public static void main(String[] args) throws IOException {
        Long value1;
        Long value2;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the first value of the multiplication:");
        value1 = Long.parseLong(reader.readLine());
        System.out.print("Enter the second value of the multiplication:");
        value2 = Long.parseLong(reader.readLine());

        Runnable multiplication = new NonOptimalMultiplication(value1, value2);
        //Creates a thread using the given runnable object. The name of the thread can be defined by the second parameter of the method
        Thread multiplicationThread = new Thread(multiplication, "Multiplication Thread");
        multiplicationThread.start();
        System.out.println("This output will not be blocked by the multiplication thread!");
    }
}
