import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // Максимум ящиков в контейнере и контейнеров в машине
        double boxesInContainer = 27;
        double containersInCar = 12;

        System.out.print("Введите число ящиков: ");
        int boxes = (new Scanner(System.in)).nextInt();

        int containers = (int) Math.ceil(boxes/boxesInContainer);
        int cars = (int) Math.ceil(containers/containersInCar);
/*
        Счетчик ящиков и контейнеров
        int boxCount = 1;
        int containerCount = 1;

        for(int i = 1; i <= cars; i++) {
            System.out.println("\nГрузовик " + i + ":");

            for (int j = 1; j <= containersInCar && containerCount <= containers; j++) {
                System.out.println("\n\tКонтейнер " + containerCount++ + ":");

                for(int k = 1; k <= boxesInContainer && boxCount <= boxes; k++ ) {
                    System.out.println("\n\t\tЯщик" + boxCount++);
                }
            }
        }
*/
        for (int boxCount = 0, containerCount = 0, carCount = 0; boxCount < boxes; boxCount++) {
            if (boxCount % boxesInContainer == 0) {
                if (containerCount % containersInCar == 0)
                    System.out.println("\nГрузовик " + ++carCount + ":");
                System.out.println("\n\tКонтейнер " + ++containerCount + ":");
            }
            System.out.println("\n\t\tЯщик" + (boxCount + 1));
        }

        System.out.println("Всего нужно грузовиков: " + cars);
        System.out.println("Всего нужно контейнеров: " + containers);
    }
}
