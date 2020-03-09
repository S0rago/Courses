import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        long start = System.currentTimeMillis();
        for (int j = 0; j < 4; j++) {
            int finalJ = j;
            Thread t = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    bank.addAccount(genNumber(), ThreadLocalRandom.current().nextLong(1000000, 9000001));
                }
                System.out.println("DONE " + finalJ + " in " + (System.currentTimeMillis() - start));
            });
            t.start();
            t.join();
        }

        List<String> accountNums = bank.getAccountNums();
        int listSize = accountNums.size();
        System.out.println(listSize);

        for (int j = 0; j < 5; j++) {
            new Thread(() -> {
                try {
                    for (int i = 0; i < 10; i++) {
                        Account fromAcc = bank.getAccount(accountNums.get(ThreadLocalRandom.current().nextInt(0, listSize)));
                        Account toAcc = bank.getAccount(accountNums.get(ThreadLocalRandom.current().nextInt(0, listSize)));
                        long amount = ThreadLocalRandom.current().nextLong(20000, 200000);
                        bank.transfer(fromAcc, toAcc, amount);
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
        while (accNum.length() < 1) {
            int index = (int) (rnd.nextFloat() * digits.length());
            accNum.append(digits.charAt(index));
        }
        return accNum.toString();
    }
}
