import javafx.animation.ScaleTransition;

public class Loader
{
    public static void main(String[] args)
    {
        Cat cat = new Cat();
        System.out.println(Cat.getCatCount() + " cat(s)");

        Cat kitty = new Cat();
        System.out.println(Cat.getCatCount() + " cat(s)");

        cat.feed(9999.0);
        System.out.println(Cat.getCatCount() + " cat(s)");

        System.out.println(cat.getStatus());
        System.out.println(Cat.getCatCount() + " cat(s)");
        ;
        kitty.goToToilet();
        kitty.feed(100.0);
        System.out.println(kitty.getStatus());
        kitty.feed(195.5);
        System.out.println(kitty.getEatenFood());

        Cat myrka = genCat(1500.0 + 2000.0 * Math.random());
        myrka.setName("Мурка");
        System.out.println(myrka.getName());
        System.out.println(Cat.getCatCount() + " cat(s)");

        Cat meowth = new Cat("Мяут");
        meowth.copyCat(myrka);
    }

    public static Cat genCat(double weight)
    {
        Cat cat = new Cat(weight);
        return cat;
    }
}