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
        // Переменная типа String
        String special = isSpecial ? "СПЕЦТРАНСПОРТ " : "";  // ТУТ
        return "\n=========================================\n" +
            special + "Автомобиль с номером " + number +
            ":\n\tВысота: " + height + " мм\n\tМасса: " + weight + " кг";
    }

    public void setHeight(int height) { this.height = height; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setNumber(String number) { this.number = number; }
    public void setHasVehicle(boolean hasVehicle) { this.hasVehicle = hasVehicle; }
    public void setIsSpecial(boolean isSpecial) { this.isSpecial = isSpecial; }

    public int getHeight() { return height; }
    public double getWeight() { return weight; }
    public String getNumber() { return number; }
    public boolean getHasVehicle() { return hasVehicle; }
    public boolean getIsSpecial() { return isSpecial; }
}