public class Manager implements Employee {
    int incomeForCompany;
    int monthSalary;

    public Manager() {
        incomeForCompany = (int) (Math.random()*1100000);
        monthSalary =  (int) (Math.random()*70000);
    }

    public int getMonthSalary() {
        return (int) (monthSalary + incomeForCompany*0.05);
    }
}
