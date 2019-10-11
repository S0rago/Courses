public class DepositAccount extends BankAccount {
    private boolean withdrawAllowed = true;
    public DepositAccount() {
        super();
    }

    @Override
    public void add(int amount) {
        super.add(amount);
        withdrawAllowed = false;
    }

    @Override
    public void withdraw(int amount) {
        if(withdrawAllowed) super.withdraw(amount);
        else System.out.println("Деп. счет: Месяц с пополнения не прошел");
    }
}
