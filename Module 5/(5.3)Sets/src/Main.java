import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashSet<String> emailList = new HashSet<>();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Введите команду: ");
            String command = input.nextLine();

            if (command.matches("ADD (\\w+@\\w+\\.\\w+)")) {
                emailList.add(command.replaceFirst("^(ADD\\s)", ""));
            } else if (command.matches("LIST")) {
                for (String email : emailList) {
                    System.out.println(email);
                }
            } else if (command.matches("EXIT")) {
                break;
            } else {
                System.out.println("Некорректная команда");
            }
        }
    }
}
