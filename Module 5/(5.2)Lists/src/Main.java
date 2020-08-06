import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Введите команду: ");
            String command = input.nextLine();

            if (command.matches("ADD \\d+ .+")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                if (index > list.size()) index = list.size();
                list.add(index, command.replaceFirst("^(ADD \\d+ )", ""));
            }
            else if (command.matches("ADD .+")) {
                list.add(command.split(" ")[1]);
            }
            else if (command.matches("DELETE \\d+")){
                int index = Integer.parseInt(command.split(" ")[1]);
                if (index > list.size()) System.out.println("Неверный индекс");
                else list.remove(index);
            }
            else if (command.matches("LIST")){
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + " - " + list.get(i));
                }
            }
            else if (command.matches("EDIT \\d+ .+")){
                int index = Integer.parseInt(command.split(" ")[1]);
                if (index > list.size()) System.out.println("Неверный индекс");
                else {
                    list.remove(index);
                    list.add(index, command.replaceFirst("^(EDIT \\d+ )", ""));
                }
            }
            else if (command.matches("EXIT"))
            { break; }
            else {
                System.out.println("Некорректная команда");
            }
        }
    }
}
