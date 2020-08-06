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
        String cross[][] = new String[7][7];
        int rowLength = cross.length;
        /**
         * 00     06
         *  11   15
         *   22 24
         *    33
         *   42 44
         *  51   55
         * 60     66
         */
        for (int i = 0; i < rowLength; i++) {
            int columnLength = cross[i].length;
            for (int j = 0; j < columnLength; j++) {
                cross[i][j] = (i == j || i == columnLength - j - 1) ? "x" : " ";
                System.out.print(cross[i][j]);
            }
            System.out.println();
        }
    }
}
