public class Cat {
    public static final int LEGS_COUNT = 4;
    public static final int EYES_COUNT = 2;
    private static final double MIN_WEIGHT = 1000;
    private static final double MAX_WEIGHT = 9000;
    private static int catCount;

    private double originWeight;
    private double weight;
    private double eatenFood;
    private CatColours colour;
    private CatStatus status;

    private String name;

    public Cat() {
        status = CatStatus.PLAYING;
        catCount++;
        eatenFood = 0.0;
        colour = CatColours.BLACK;
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        getStatus();
    }

    public Cat(double setWeight) {
        this();
        weight = setWeight;
        colour = CatColours.GREY;
        getStatus();
    }

    public Cat(String name, double setWeight) {
        this(setWeight);
        this.name = name;
        colour = CatColours.ORANGE;
        getStatus();
    }

    public void meow() {
        if (status != CatStatus.DEAD && status != CatStatus.EXPLODED) {
            weight = weight - 1;
            System.out.println("Meow");
        }
        getStatus();
    }

    public void feed(Double amount)
    {
        if (status != CatStatus.DEAD && status != CatStatus.EXPLODED) {
            eatenFood += amount;
            weight = weight + amount;
        }
        getStatus();
    }

    public void drink(Double amount)
    {
        if (status != CatStatus.DEAD && status != CatStatus.EXPLODED) {
            weight = weight + amount;
        }
        getStatus();
    }

    public double getWeight() { return weight; }
    public double getOriginWeight() { return originWeight; }
    public String getName() { return name; }
    public CatColours getColour() { return colour; }

    public void setWeight(double weight) { this.weight = weight; }
    public void setOriginWeight(double originWeight) { this.originWeight = originWeight; }
    public void setName(String name) { this.name = name; }
    public void setColour(CatColours colour) { this.colour = colour; }

    public Double getEatenFood() { return eatenFood; }

    public static int getCatCount() { return catCount; }

    public String getStatus()
    {
        if (status != CatStatus.DEAD && status != CatStatus.EXPLODED) {
            if(weight < MIN_WEIGHT) {
                status = CatStatus.DEAD;
                catCount--;
                return "Dead";
            }
            else if(weight > MAX_WEIGHT) {
                status = CatStatus.EXPLODED;
                catCount--;
                return "Exploded";
            }
            else if(weight > originWeight) {
                status = CatStatus.SLEEPING;
                return "Sleeping";
            }
            else {
                status = CatStatus.PLAYING;
                return "Playing";
            }
        }
        else {
            if (status == CatStatus.DEAD) return "Dead";
            else return "Exploded";
        }
    }

    public void goToToilet()
    {
        if (status != CatStatus.DEAD && status != CatStatus.EXPLODED) {
            weight = weight - 300.0 * Math.random();
            System.out.println("Feels relieved");
        }
        getStatus();
    }

    public static Cat copyCat(Cat oldCat)
    {
        if (oldCat.status != CatStatus.DEAD && oldCat.status != CatStatus.EXPLODED) {
            Cat newCat = new Cat();
            newCat.name = oldCat.name;
            newCat.originWeight = oldCat.originWeight;
            newCat.weight = oldCat.weight;
            newCat.eatenFood = oldCat.eatenFood;
            newCat.colour = oldCat.colour;
            return newCat;
        }
        else {
            System.out.println("Dead cat can't be copied");
            return null;
        }
    }
}