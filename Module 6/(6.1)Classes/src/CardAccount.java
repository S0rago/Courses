public class CardAccount extends BankAccount {
    public CardAccount() {
        super();
    }

    @Override
    public void withdraw(int amount) {
        super.withdraw(amount);
        setMoneyAmount(getMoneyAmount() - (int) (getMoneyAmount()*0.01));
    }
}
