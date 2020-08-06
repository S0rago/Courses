import java.util.HashMap;
import java.util.NoSuchElementException;

public class CustomerStorage {
    private HashMap<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        String[] components = data.split("\\s+");
        if (components.length != 4 ||
                !(components[2].matches(".+@\\w+\\.\\w+") && (components[3].matches("\\+\\d{11}"))))
            throw new IllegalArgumentException("Wrong format! Correct format: \n" +
                "add Василий Петров vasily.petrov@gmail.com +79215637722");
        String name = components[0] + " " + components[1];
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers() {
        if (storage.size() == 0) throw new IndexOutOfBoundsException("No customers added!");
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        if (storage.size() == 0) throw new IndexOutOfBoundsException("No customers added!");
        if (!storage.containsKey(name)) throw new NoSuchElementException("Client not found!");
        storage.remove(name);
    }

    public int getCount() {
        if (storage.size() == 0) throw new IndexOutOfBoundsException("No customers added!");
        return storage.size();
    }
}
