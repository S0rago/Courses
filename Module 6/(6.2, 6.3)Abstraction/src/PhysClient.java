public class PhysClient extends Client{
    PhysClient() {
        super();
    }

    public void add(int amount) {
        setMoneyAmount(getMoneyAmount() + amount);
    }

    public void withdraw(int amount) {
        if (getMoneyAmount() < amount) System.out.println("Недостаточно средств");
        else setMoneyAmount(getMoneyAmount() - amount);
    }
}