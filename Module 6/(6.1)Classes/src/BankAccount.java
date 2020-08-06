public class BankAccount {
    private int moneyAmount;

    public BankAccount() {
        setMoneyAmount(0);
    }

    public void add(int amount) {
        setMoneyAmount(getMoneyAmount() + amount);
    }

    public void withdraw(int amount) {
        if (getMoneyAmount() < amount) System.out.println("Недостаточно средств");
        else setMoneyAmount(getMoneyAmount() - amount);
    }

    public void showAmount() {
        System.out.println(this.getClass().getTypeName() + ": " + getMoneyAmount());
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }
}
