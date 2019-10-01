public class Main {

    public static void main(String[] args) {
        System.out.println("Byte: " + Byte.MAX_VALUE + " | " + Byte.MIN_VALUE);
        System.out.println("Short: " + Short.MAX_VALUE + " | " + Short.MIN_VALUE);
        System.out.println("Int: " + Integer.MAX_VALUE + " | " + Integer.MIN_VALUE);
        System.out.println("Long: " + Long.MAX_VALUE + " | " + Long.MIN_VALUE);
        System.out.println("Double: " + Double.MAX_VALUE + " | " + Double.MIN_VALUE);
        System.out.println("Float: " + Float.MAX_VALUE + " | " + Float.MIN_VALUE);
        System.out.println("Character: " + (int) Character.MAX_VALUE + " | " + (int) Character.MIN_VALUE);
        //Char переводится в int, так как \uFFFF и \u0000 не будут отображаться корректно
        System.out.println("Character: " + Character.MAX_VALUE + " | " + Character.MIN_VALUE);

    }
}
