public class Main {

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        CardAccount cardAccount = new CardAccount();
        DepositAccount depositAccount = new DepositAccount();

        bankAccount.add(10000);
        cardAccount.add(10000);
        depositAccount.add(10000);

        bankAccount.showAmount();
        cardAccount.showAmount();
        depositAccount.showAmount();

        bankAccount.withdraw(900);
        cardAccount.withdraw(900);
        depositAccount.withdraw(900);

        bankAccount.showAmount();
        cardAccount.showAmount();
        depositAccount.showAmount();
    }
}
