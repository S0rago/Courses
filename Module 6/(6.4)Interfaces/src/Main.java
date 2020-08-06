import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Company company = new Company(270);

        ArrayList<Employee> top = company.getTopSalaryStuff(15);
        System.out.println("Top:");
        for (Employee e : top) {
            System.out.println(e.getMonthSalary());
        }
        System.out.println();
        ArrayList<Employee> low = company.getLowestSalaryStuff(15);
        System.out.println("Lowest:");
        for (Employee e : low) {
            System.out.println(e.getMonthSalary());
        }
    }
}
