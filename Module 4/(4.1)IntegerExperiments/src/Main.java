public class Main
{
    public static void main(String[] args)
    {
        Container container = new Container();
        container.count += 7843;
        System.out.println(container.count);
        System.out.println(sumDigits(157));
    }

    public static Integer sumDigits(Integer number)
    {
        String num = number.toString();
        Integer sum = 0;
        for (int i = 0; i < num.length(); i++)
        {
            sum += Character.getNumericValue(num.charAt(i));
        }
        return sum;
    }
}
