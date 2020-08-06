public class IndividualClient extends Client {
    IndividualClient() {
        super();
    }
    public void add(int amount) {
        if (amount < 1000)
            setMoneyAmount(getMoneyAmount() + (int)(0.99 * amount));
        else
            setMoneyAmount(getMoneyAmount() + (int)(0.995 * amount));
    }

    public void withdraw(int amount) {
        if (getMoneyAmount() < amount) System.out.println("Недостаточно средств");
        else setMoneyAmount(getMoneyAmount() - amount);
    }
}