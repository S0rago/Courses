import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //strReverse();
        //patientCount();
        printCross();
    }

    private static void strReverse() {
        String text = "Каждый охотник желает знать, где сидит фазан";
        String[] colours = text.split(",?\\s+");

        String temp = "";

        for (int i = 0; i < colours.length / 2; i++) {
            temp = colours[i];
            colours[i] = colours[colours.length - i - 1];
            colours[colours.length - i - 1] = temp;
        }

        System.out.println(Arrays.toString(colours));
    }

    private static void patientCount() {
        double[] patients = new double[30];
        double avg = 0;
        int healthy = 0;

        for (double p : patients) {
            p = 32 + Math.round(8.1 * Math.random() * 10.0) / 10.0;
            System.out.println(p);

            avg += p;

            if (p >= 36.2 && p <= 36.9) healthy++;
        }
        avg = Math.round(avg / patients.length * 10.0) / 10.0;
        System.out.println("Средняя температура по больнице: " + avg);
        System.out.println("Число здоровых пациентов: " + healthy);
    }

    private static void printCross() {
        String cross[][] = {{"x     x"},
                            {" x   x "},
                            {"  x x  "},
                            {"   x   "},
                            {"  x x "},
                            {" x   x "},
                            {"x     x"}};
        for (int i = 0; i < cross.length; i++) {
            for (int j = 0; j < cross[i].length; j++) {
                System.out.print(cross[i][j]);
            }
            System.out.println();
        }
    }
}
