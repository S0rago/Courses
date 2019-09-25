
public class Cat
{
    public static final int LEGS_COUNT = 4;
    public static final int EYES_COUNT = 2;
    private static final double MIN_WEIGHT = 1000;
    private static final double MAX_WEIGHT = 9000;

    private double originWeight;
    private double weight;
    private double eatenFood;
    private static int catCount;
    private CatColours colour;

    private String name;

    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        eatenFood = 0.0;
        catCount++;
        colour = CatColours.BLACK;
    }

    public Cat(double setWeight)
    {
        weight = setWeight;
        originWeight = weight;
        eatenFood = 0.0;
        catCount++;
        colour = CatColours.GREY;
    }

    public Cat(String name)
    {
        this();
        this.name = name;
        colour = CatColours.ORANGE;
    }

    public void meow()
    {
        weight = weight - 1;
        System.out.println("Meow");
    }

    public void feed(Double amount)
    {
        eatenFood += amount;
        weight = weight + amount;
    }

    public void drink(Double amount)
    {
        weight = weight + amount;
    }

    public Double getWeight()
    {
        return weight;
    }
    public Double getOriginWeight() { return originWeight; }
    public String getName() { return name; }
    public CatColours getColour() { return colour; }
    public void setWeight(Double weight) { this.weight = weight; }
    public void setOriginWeight(Double originWeight) { this.originWeight = originWeight; }
    public void setName(String name) { this.name = name; }
    public void setColour(CatColours colour) { this.colour = colour; }

    public Double getEatenFood()
    {
        return eatenFood;
    }

    public static int getCatCount() { return catCount; }

    public String getStatus()
    {
        if(weight < MIN_WEIGHT) {
            catCount--;
            return "Dead";
        }
        else if(weight > MAX_WEIGHT) {
            catCount--;
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }

    public void goToToilet()
    {
        weight = weight - 300.0 * Math.random();
        System.out.println("Feels relieved");
    }

    public void copyCat(Cat cat)
    {
        this.name = cat.name;
        this.originWeight = cat.originWeight;
        this.weight = cat.weight;
        this.eatenFood = cat.eatenFood;
        this.colour = cat.colour;
    }
}