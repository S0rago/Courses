import java.util.*;

public class Bank {
    private HashMap<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
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
    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        Account fromAccount = accounts.get(fromAccountNum);
        Account toAccount = accounts.get(toAccountNum);

        if (!(fromAccount.isBlocked() && toAccount.isBlocked())) {
            fromAccount.setMoney(fromAccount.getMoney() - amount);
            toAccount.setMoney(toAccount.getMoney() + amount);
        }

        if (amount > 50000)
            if (isFraud(fromAccountNum, toAccountNum, amount)) {
                System.out.println(Thread.currentThread().getName() + ": " + "blocked " + fromAccountNum + " - " + toAccountNum + " - " + amount);
                fromAccount.Block();
                toAccount.Block();
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
}
