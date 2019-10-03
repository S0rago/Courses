import java.util.Scanner;

public class Loader
{
    public static void main(String[] args) {
        findSum();
        printCodes();
        splitName();
    }

    private static void findSum() {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        System.out.println(text);
        System.out.println(text.length());

        int numStart = 0;
        int numEnd = 0;
        int sum = 0;
        while (numStart != -1) {
            numEnd = text.indexOf(" ", numStart + 1);
            if (Character.isDigit(text.charAt(numStart + 1))) {
                String word = text.substring(numStart + 1, numEnd);
                int number = Integer.parseInt(word);
                sum += number;
            }
            numStart = text.indexOf(" ", numEnd + 1);
        }
        System.out.println(sum);
    }

    private static void printCodes() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz" +
                "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        for (char letter : alphabet.toCharArray()) {
            System.out.println(letter + " | " + (int) letter);
        }
    }

    private static void splitName() {
        System.out.println("Введите ФИО: ");
        String[] name = (new Scanner(System.in)).nextLine().split(" ");

        if (name.length != 3)
            System.out.println("Имя введено некорректно");
        else {
            System.out.println("Фамилия: " + name[0]);
            System.out.println("Имя: " + name[1]);
            System.out.println("Отчество: " + name[2]);
        }
    }
}
