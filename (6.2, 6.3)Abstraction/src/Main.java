public class Main {
    public static void main(String[] args) {
        Client physClient = new PhysClient();
        Client juridClient = new JuridicClient();
        Client indivClient = new IndividualClient();

        physClient.add(1000);
        juridClient.add(1000);
        indivClient.add(1000);

        physClient.showAmount();
        juridClient.showAmount();
        indivClient.showAmount();

        physClient.add(1000);
        juridClient.add(1000);
        indivClient.add(1000);

        physClient.showAmount();
        juridClient.showAmount();
        indivClient.showAmount();

        physClient.withdraw(500);
        juridClient.withdraw(500);
        indivClient.withdraw(500);

        physClient.showAmount();
        juridClient.showAmount();
        indivClient.showAmount();
    }
}