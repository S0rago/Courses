public class Operationist implements Employee {
    int monthSalary;
    public Operationist() {
        monthSalary = (int) (Math.random()*50000);
    }
    @Override
    public int getMonthSalary() {
        return monthSalary;
    }
}
