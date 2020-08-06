import java.util.ArrayList;

public class Shop {
    private String name;
    private ArrayList<Item> items = new ArrayList<>();

    public Shop(String name) {
        this.name = name;
    }
    public Shop(String name, ArrayList<Item> items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void addItem(Item newItem) {
        items.add(newItem);
    }
}
