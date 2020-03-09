public class Account
{
    volatile private long money;
    private String accNumber;
    volatile private boolean isUnlocked;

    public Account(long money, String accNumber) {
        this.money = money;
        this.accNumber = accNumber;
        this.isUnlocked = true;
    }

    public long getMoney() { return money; }
    public void setMoney(long money) { this.money = money; }

    public void deposit(long amount) { this.money =+ amount; }
    public void withdraw(long amount) { this.money =- amount; }

    public String getAccNumber() { return accNumber; }
    public void setAccNumber(String accNumber) { this.accNumber = accNumber; }

    public boolean isUnlocked() { return isUnlocked; }
    public void block() { isUnlocked = false; }
    public void unlock() { isUnlocked = true; }
}
