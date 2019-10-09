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
                if (!phoneBook.containsKey(phone)) {
                    System.out.print("Введите имя: ");
                    String name = input.nextLine();
                    phoneBook.put(phone, name);
                }
            }
            else if (command.matches("LIST"))
                for (String phone : phoneBook.keySet())
                    System.out.println(phoneBook.get(phone) + " - " + phone);
            else if (command.matches("EXIT")) break;
            else if (command.isEmpty()) System.out.println("Неверный ввод");
            else {
                if (!phoneBook.containsValue(command)) {
                    System.out.print("Введите номер: ");
                    String phone = input.nextLine();
                    if (checkPhone(phone))
                        if (!phoneBook.containsKey(phone))
                            phoneBook.put(phone.replaceAll("[^0-9]", "").replaceFirst("^8", "7"), command);
                    else System.out.println("Некорректно введен номер");
                }
            }
        }
    }
    private static boolean checkPhone(String phone) {
        return phone.matches("((\\+?7)|8)\\s*\\(?\\d{3}\\)?\\s*\\d{3}(-|\\s*)(\\d{2}(-|\\s*)){2}");
    }
}