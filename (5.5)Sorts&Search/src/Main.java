import java.util.*;

public class Main {
    public static void main(String[] args) {
        long start = 0;
        long duration = 0;
        boolean found = false;
        ArrayList<String> numList = genList();
        Collections.sort(numList);

        HashSet<String> hashSet = new HashSet<>(numList);
        TreeSet<String> treeSet = new TreeSet<>(numList);

        Scanner input = new Scanner(System.in);

        System.out.print("Введите номер(a111aa000)/EXIT: ");
        String str = input.next().toUpperCase();
        System.out.println(str);
        while (!str.equals("EXIT")) {
            if (str.matches("\\w\\d{3}\\w{2}\\d{3}")) {
                start = System.currentTimeMillis();
                found = numList.contains(str);
                duration = System.currentTimeMillis() - start;
                System.out.println("Прямой поиск: " + duration);

                start = System.currentTimeMillis();
                int index = Collections.binarySearch(numList, str);
                duration = System.currentTimeMillis() - start;
                System.out.println("Бинарный поиск: " + duration);

                start = System.currentTimeMillis();
                found = hashSet.contains(str);
                duration = System.currentTimeMillis() - start;
                System.out.println("Поиск в HashSet: " + duration);

                start = System.currentTimeMillis();
                found = treeSet.contains(str);
                duration = System.currentTimeMillis() - start;
                System.out.println("Поиск в TreeSet: " + duration);

                if (!found) System.out.println("Номер не найден");
            }
            else System.out.println("Некорретный номер");
            System.out.print("Введите номер/EXIT: ");
            str = input.next().toUpperCase();
        }
    }

    private static ArrayList<String> genList() {
        String letters = "ABCEHMOPTY";
        ArrayList<String> list = new ArrayList<>();

        //A 333 A A 197
        String num = "A000AA001";
        for (char a : letters.toCharArray()) {
            num = num.replaceFirst( "\\p{Upper}" , Character.toString(a));
            for (int i = 0; i < 10; i++) {
                String digits = Integer.toString(i) + i + i;
                num = num.replaceFirst("\\d{3}", digits);
                for (char b : letters.toCharArray()) {
                    num = num.replaceFirst("\\p{Upper}(?=\\p{Upper})", Character.toString(b));
                    for (char c : letters.toCharArray()) {
                        num = num.replaceFirst("(?<=\\p{Upper})\\p{Upper}", Character.toString(c));
                        for (int j = 1; j < 198; j++) {
                            num = num.replaceFirst("(\\d{3})$", addZero(j));
                            list.add(num);
                        }
                    }
                }
            }
        }

        return list;
    }

    private static String addZero(int i) {
        StringBuilder str = new StringBuilder(Integer.toString(i));
        if (str.length() == 1)
            str.insert(0, "00");
        else if (str.length() == 2)
            str.insert(0, "0");

        return str.toString();
    }
}
