import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        long start = System.currentTimeMillis();
        for (int j = 0; j < 4; j++) {
            int finalJ = j;
            new Thread(() -> {
                for (int i = 0; i < 100000; i++) {
                    bank.addAccount(genNumber(), ThreadLocalRandom.current().nextLong(1000000, 9000001));
                }
                System.out.println("DONE " + finalJ + " in " + (System.currentTimeMillis() - start));
            }).start();
        }

        List<String> accountNums = bank.getAccountNums();
        int listSize = accountNums.size();


        for (int j = 0; j < 8; j++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("Records: " + listSize);
                    for (int i = 0; i < 100000; i++) {
                        bank.transfer(accountNums.get(ThreadLocalRandom.current().nextInt(0, listSize)),
                                accountNums.get(ThreadLocalRandom.current().nextInt(0, listSize)),
                                ThreadLocalRandom.current().nextLong(0, 200000));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static String genNumber() {
        String digits = "1234567890";
        StringBuilder accNum = new StringBuilder();
        Random rnd = new Random();
        while (accNum.length() < 10) {
            int index = (int) (rnd.nextFloat() * digits.length());
            accNum.append(digits.charAt(index));
        }
        return accNum.toString();
    }
}
