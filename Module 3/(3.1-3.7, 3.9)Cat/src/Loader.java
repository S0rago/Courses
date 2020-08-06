public class Loader
{
    public static void main(String[] args)
    {
        Cat cat = new Cat();
        System.out.println(Cat.getCatCount() + " cat(s) 1");

        Cat kitty = new Cat();
        System.out.println(Cat.getCatCount() + " cat(s) 2");

        cat.feed(9999.0);
        System.out.println(Cat.getCatCount() + " cat(s) 3");

        System.out.println(cat.getStatus());
        System.out.println(cat.getStatus());
        System.out.println(Cat.getCatCount() + " cat(s) 4");

        kitty.goToToilet();
        kitty.feed(100.0);
        System.out.println(kitty.getStatus());
        kitty.feed(195.5);
        System.out.println(kitty.getEatenFood());

        Cat myrka = genCat(1500.0 + 2000.0 * Math.random());
        myrka.setName("Мурка");
        System.out.println(myrka.getName());
        System.out.println(Cat.getCatCount() + " cat(s) 5");

        Cat copy = Cat.copyCat(cat);

        Cat ded = new Cat(9999.0);
        System.out.println(Cat.getCatCount() + " cat(s) 6");

    }

    public static Cat genCat(double weight)
    {
        Cat cat = new Cat(weight);
        return cat;
    }
}