public class JuridicClient extends Client{
    JuridicClient() {
        super();
    }

    public void add(int amount) {
        setMoneyAmount(getMoneyAmount() + amount);
    }

    public void withdraw(int amount) {
        if (getMoneyAmount() < amount) System.out.println("Недостаточно средств");
        else setMoneyAmount(getMoneyAmount() - (int) (amount * 1.01));
    }
}