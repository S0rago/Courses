import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) {
        File tableFile = new File("src/movementList.csv");
        ArrayList<Entry> entries = readFile(tableFile);
        //entries.forEach(System.out::println);

        double income = entries.stream().mapToDouble(Entry::getIncome).sum();
        System.out.println("Сумма доходов: " + income);
        double expense = entries.stream().mapToDouble(Entry::getExpense).sum();
        System.out.println("Сумма расходов: " + expense);

        Map<String, Double> expenseGroups = entries.stream().collect(Collectors.groupingBy(Entry::getPlace,
                Collectors.summingDouble(Entry::getExpense)));
        expenseGroups.forEach((s,d)->System.out.println(s + " - " + d));
    }

    public static ArrayList<Entry> readFile(File file) {
        ArrayList<Entry> entries = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(file.toPath());
            for(String line : lines.subList(1, lines.size()))
            {
                String[] fragments = line.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
                if(fragments.length != 8) {
                    System.out.println("Wrong line: " + fragments.length + "   " + line);
                    continue;
                }
                entries.add(new Entry(
                        fragments[0],
                        fragments[1],
                        fragments[2],
                        (new SimpleDateFormat(dateFormat)).parse(fragments[3]),
                        fragments[4],
                        fragments[5],
                        Double.parseDouble(fragments[6].replaceAll("\"", "").replace(',', '.')),
                        Double.parseDouble(fragments[7].replaceAll("\"", "").replace(',', '.'))
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return entries;
    }
}
