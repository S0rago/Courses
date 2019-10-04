import java.util.Scanner;

public class Loader
{
    public static void main(String[] args) {
        //regSum();
        //regSplit();
        //regName();
        regPhone();
    }

    private static void regSum() {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

        int sum = 0;
        for (String s : text.split("[,\\- \\s+]"))
            if (s.matches("\\d+"))
                sum += Integer.parseInt(s);

        System.out.println(text);
        System.out.println(sum);
    }

    private static void regSplit() {
        String text = "A young man who appears in tales from a distant world, where he features in a story of an empire and the rebellion against it." +
                "To defeat the Palamecian Empire, which destroyed his homeland, he joins the rebel army led by Fynn's own Princess Hilda." +
                " His friend and adopted brother Leon also survived the fall of Fynn, but now stands against him as a key member of the Palamecian army. " +
                "Firion loses many allies, including the White Wizard Minwu, and is toyed with continually by cruel fate, but never do his eyes stray from his goal: to put a stop to the Emperor of Palamecia.";

        for (String word : text.split("[\\s+]|[\\p{Punct}]")) {
            System.out.println(word);
        }
    }

    private static void regName() {
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

    private static void regPhone() {
        System.out.print("Введите номер телефона");
        String phone = "8 (909) 123-45-67";
        phone = (new Scanner(System.in)).nextLine();
        System.out.println(phone.replaceAll("[^0-9]", "").replaceFirst("^8", "7"));
    }
}