package action;

import java.math.BigInteger;

public class NonOptimalMultiplication implements Runnable {

    private Long value1;
    private Long value2;
    private Long result;

    public NonOptimalMultiplication(Long value1, Long value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public void run() {
        BigInteger calculation = new BigInteger("0");

        for(int i = 0; i < value1; i++) {
            for(int j = 0; j < value2; j++) {
                calculation = calculation.add(new BigInteger("1"));
            }
        }
        result = calculation.longValue();
        System.out.println("Result: " + result);
    }
}
