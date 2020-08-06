import javax.swing.text.html.parser.Entity;
import java.util.*;

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
            else if (command.matches("LIST")) printSet(phoneBook);
//                for (String phone : phoneBook.keySet())
//                    System.out.println(phoneBook.get(phone) + " - " + phone);
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

    private static void printSet(HashMap <String, String> set) {
        TreeMap<String, Integer> names = new TreeMap<>();
        HashMap<String, Integer> phones = new HashMap<>();

        int i = 0;
        for (Map.Entry<String, String> item : set.entrySet()) {
            names.put(item.getValue(), i);
            phones.put(item.getKey(), i++);
        }

        for (Map.Entry<String, Integer> name : names.entrySet()) {
            for (Map.Entry<String, Integer> phone : phones.entrySet()) {
                if (name.getValue() == phone.getValue())
                    System.out.println(name.getKey() + " - " + phone.getKey());
            }
        }
    }

    private static boolean checkPhone(String phone) {
        return phone.matches("((\\+?7)|8)\\s*\\(?\\d{3}\\)?\\s*\\d{3}(-|\\s*)(\\d{2}(-|\\s*)){2}");
    }
}