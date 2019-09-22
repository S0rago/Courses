public class Main {

    public static void main(String[] args) {
        for (int i = 200000; i <= 235000; i++) {
            System.out.println(i);
            if (i == 210000)
                i = 219999;
        }

        int i = 200000;
        while (i <= 235000) {
            System.out.println(i);
            if (i == 210000)
                i = 219999;
            i++;
        }
    }
}
