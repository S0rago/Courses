import java.util.ArrayList;

public class Company {
    ArrayList<Employee> employees;
    int yearIncome;

    public Company(int num)
    {
        setYearIncome((int) (Math.random() * 10000));
        employees = new ArrayList<>();
//        e1, e2) -> {
//        if (e1.getMonthSalary() > e2.getMonthSalary()) return 1;
//        if (e1.getMonthSalary() < e2.getMonthSalary()) return -1;
//        return 0;
//    });
        for (int i = 0; i < num; i++) {
            int x = (int) (Math.random()*100);
            if(x < 34) employees.add(new Manager());
            else if(x < 67) employees.add(new TopManager());
            else employees.add(new Operationist());
        }
    }

    public int getYearIncome() { return yearIncome; }
    public void setYearIncome(int yearIncome) { this.yearIncome = yearIncome; }

    public ArrayList<Employee> getTopSalaryStuff(int count) {
        employees.sort((e1, e2) -> {
        if (e1.getMonthSalary() > e2.getMonthSalary()) return 1;
        if (e1.getMonthSalary() < e2.getMonthSalary()) return -1;
        return 0;
        });
        ArrayList<Employee> temp = new ArrayList<>();
        for (int i = 0; i < employees.size() && i < count; i++) {
            temp.add(employees.get(employees.size() - i - 1));
        }
        return temp;
    }

    public ArrayList<Employee> getLowestSalaryStuff(int count) {
        employees.sort((e1, e2) -> {
            if (e1.getMonthSalary() > e2.getMonthSalary()) return 1;
            if (e1.getMonthSalary() < e2.getMonthSalary()) return -1;
            return 0;
        });
        ArrayList<Employee> temp = new ArrayList<>();
        for (int i = 0; i < employees.size() && i < count; i++) {
            temp.add(employees.get(i));
        }
        return temp;
    }
}
