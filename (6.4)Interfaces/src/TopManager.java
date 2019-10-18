public class TopManager implements Employee {
    int companyIncome;
    int monthSalary;
    int bonus = 50000;

    public TopManager() {
        companyIncome = (int) (Math.random()*100000000);
        monthSalary =  (int) (Math.random()*100000);
    }

    public int getMonthSalary() {
        return ((companyIncome > 10000000)) ? monthSalary+bonus : monthSalary;
    }
}
