package core;

public class Car
{
        // Переменная типа String
        public String number;
        // Переменная типа int
        public int height;
        // Переменная типа double
        public double weight;
        // Переменная типа boolean
        public boolean hasVehicle;
        // Переменная типа boolean
        public boolean isSpecial;

    public String toString()
    {
        String special = isSpecial ? "СПЕЦТРАНСПОРТ " : "";  // ТУТ
        return "\n=========================================\n" +
            special + "Автомобиль с номером " + number +
            ":\n\tВысота: " + height + " мм\n\tМасса: " + weight + " кг";
    }
}