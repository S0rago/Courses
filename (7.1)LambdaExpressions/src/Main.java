import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class Main
{
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args)
    {
        ArrayList<Employee> staff = loadStaffFromFile();
        Calendar start = Calendar.getInstance();
        start.set(2016, Calendar.DECEMBER, 31);

        Calendar end = Calendar.getInstance();
        end.set(2017, Calendar.DECEMBER, 31);

        staff.stream()
                .filter(e -> e.getWorkStart().after(start.getTime()) && e.getWorkStart().before(end.getTime()))
                .max(Comparator.comparing(Employee::getSalary))
                .ifPresent(System.out::println);
//        staff.sort((s1, s2) -> {
//            int salaryCompare = s1.getSalary().compareTo(s2.getSalary());
//            //Если зарплаты равны, сравниваем имена
//            return (salaryCompare == 0) ? s1.getName().compareTo(s2.getName()) : salaryCompare;
//        });
//        staff.sort(Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName));
//        for (Employee emp : staff) {
//            System.out.println(emp);
//        }
    }

    private static ArrayList<Employee> loadStaffFromFile()
    {
        ArrayList<Employee> staff = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for(String line : lines)
            {
                String[] fragments = line.split("\t");
                if(fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                    fragments[0],
                    Integer.parseInt(fragments[1]),
                    (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}