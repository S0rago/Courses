import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashMap<String, String> phoneBook = new HashMap<>();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Введите имя/номер/LIST/EXIT: ");
            String command = input.nextLine();

            if (checkPhone(command)) {
                String phone = command.replaceAll("[^0-9]", "").replaceFirst("^8", "7");
                if (!phoneBook.containsValue(phone)) {
                    System.out.print("Введите имя: ");
                    String name = input.nextLine();
                    phoneBook.put(name, phone);
                }
            }
            else if (command.matches("LIST"))
                for (String name : phoneBook.keySet())
                    System.out.println(name + " - " + phoneBook.get(name));
            else if (command.matches("EXIT")) break;
            else if (command.isEmpty()) System.out.println("Неверный ввод");
            else {
                if (!phoneBook.containsKey(command)) {
                    System.out.print("Введите номер: ");
                    String phone = input.nextLine();
                    if (checkPhone(phone))
                        phoneBook.put(command, phone.replaceAll("[^0-9]", "").replaceFirst("^8", "7"));
                    else System.out.println("Некорректно введен номер");
                }
            }
        }
    }
    private static boolean checkPhone(String phone) {
        return phone.matches("((\\+?7)|8)\\s*\\(?\\d{3}\\)?\\s*\\d{3}(-|\\s*)(\\d{2}(-|\\s*)){2}");
    }
}