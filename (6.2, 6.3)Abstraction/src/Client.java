public abstract class Client {
    private int moneyAmount;

    Client() {
        setMoneyAmount(0);
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public abstract void add(int amount);
    public abstract void withdraw(int amount);

    public void showAmount() {
        System.out.println(this.getClass().getTypeName() + ": " + getMoneyAmount());
    }
}