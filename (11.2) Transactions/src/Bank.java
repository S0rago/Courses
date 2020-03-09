import java.awt.image.PackedColorModel;
import java.util.*;

public class Bank {
    private volatile HashMap<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();

    public synchronized boolean isFraud(Account fromAccount, Account toAccount, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }
    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(Account fromAccount, Account toAccount, long amount) throws InterruptedException {

        int fromId = Integer.parseInt(fromAccount.getAccNumber());
        int toId = Integer.parseInt(toAccount.getAccNumber());

        if (fromAccount.isUnlocked() && toAccount.isUnlocked() && fromAccount.getMoney() >= amount) {
            if (fromId < toId) {
                synchronized (fromAccount) {
                    synchronized (toAccount) {
                        fromAccount.withdraw(amount);
                        toAccount.deposit(amount);
                        System.out.println(Thread.currentThread().getName() + ": transferred " + fromAccount.getAccNumber() + " - " + toAccount.getAccNumber() + " - " + amount);

                        if (amount > 50000)
                            if (isFraud(fromAccount, toAccount, amount)) {
                                fromAccount.block();
                                toAccount.block();
                                System.out.println(Thread.currentThread().getName() + ": blocked " + fromAccount.getAccNumber() + " - " + toAccount.getAccNumber() + " - " + amount);
                            }
                    }
                }
            }
            else {
                synchronized (toAccount) {
                    synchronized (fromAccount) {
                        fromAccount.withdraw(amount);
                        toAccount.deposit(amount);
                        System.out.println(Thread.currentThread().getName() + ": transferred " + fromAccount.getAccNumber() + " - " + toAccount.getAccNumber() + " - " + amount);

                        if (amount > 50000)
                            if (isFraud(fromAccount, toAccount, amount)) {
                                fromAccount.block();
                                toAccount.block();
                                System.out.println(Thread.currentThread().getName() + ": blocked " + fromAccount.getAccNumber() + " - " + toAccount.getAccNumber() + " - " + amount);
                            }
                    }
                }
            }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public void addAccount(String accountNum, long moneyAmount) {
        accounts.put(accountNum, new Account(moneyAmount, accountNum));
    }

    public List<String> getAccountNums() {
        return new ArrayList<>(accounts.keySet());
    }

    public Account getAccount(String accNum) {
        return accounts.get(accNum);
    }
}
