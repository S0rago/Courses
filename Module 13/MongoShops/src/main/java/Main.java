import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.*;
import java.util.function.Consumer;

import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Updates.set;

public class Main {
    static MongoClientURI uri = new MongoClientURI(
            "mongodb+srv://sorago:8a1V5HeG4f9b@cluster0-ctjuj.gcp.mongodb.net/SkillBox?retryWrites=true&w=majority");
    static MongoClient mongoClient = new MongoClient(uri);
    static MongoDatabase database = mongoClient.getDatabase("SkillBox");
    static MongoCollection<Document> shopCollection = database.getCollection("Shops");
    static MongoCollection<Document> itemCollection = database.getCollection("Items");

    public static void main(String[] args) {

        //collection.drop();
        ArrayList<Shop> shops = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        String input = "";
        while (!input.equalsIgnoreCase("выйти")) {
            try {
                System.out.print("Введите команду: ");
                input = sc.nextLine();
                String[] command = input.split(" ");
                switch (command[0]) {
                    case ("ДОБАВИТЬ_МАГАЗИН"):
                        addShop(command[1]);
                        break;
                    case ("ДОБАВИТЬ_ТОВАР"):
                        addItem(command[1], Integer.parseInt(command[2]));
                        break;
                    case ("ВЫСТАВИТЬ_ТОВАР"):
                        putItem(command[1], command[2]);
                        break;
                    case ("СТАТИСТИКА_ТОВАРОВ"):
                        showStats();
                        break;
                    case ("выйти"):
                        break;
                    default:
                        System.out.println("Неверная команда!");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void addShop(String shopName) {
        Shop shop = new Shop(shopName);
        shopCollection.insertOne(new Document().append("name", shop.getName())
                .append("items", Collections.emptyList()));
    }

    public static void addItem(String itemName, int price) {
        Item item = new Item(itemName, price);
        itemCollection.insertOne(new Document().append("name", item.getName())
                .append("price", item.getPrice()));
    }

    public static void putItem(String shopName, String itemName) {
        Document shopDoc = Objects.requireNonNull(shopCollection.find(eq("name", shopName)).first());
        Shop shop = new Shop(shopDoc.get("name").toString());

        ArrayList<Document> itemDocs = (ArrayList<Document>) shopDoc.get("items");
        if (itemDocs != null) {
            for (Document item : itemDocs) {
                shop.addItem(new Item(item.getString("name"), (int) item.get("price")));
            }
        }

        Document newItemDoc = Objects.requireNonNull(itemCollection.find(eq("name", itemName)).first());
        Item newItem = new Item(newItemDoc.getString("name"), (int) newItemDoc.get("price"));
        shop.addItem(newItem);

        itemDocs = new ArrayList<>();

        for (Item item : shop.getItems()) {
            itemDocs.add(new Document().append("name", item.getName()).append("price", item.getPrice()));
        }

        shopCollection.updateOne(eq("name", shopName), set("items", itemDocs));
    }

    public static void showStats() {
        Consumer<Document> printBlock = document -> System.out.println(document.toJson());
//db.Shops.aggregate({$project: {_id: {name: "$name"}, avgprice: {$avg: "$items.price"}, high: {$max: "$items.price"},
// low: {$min: "$items.price"}, amount: {$size: "$items"}}, lt100: {$size: {$filter: {input: "$items", as: "item", cond: {$lt:["$$item.price", 100]}}}}} })

        shopCollection.aggregate(Arrays.asList(project(
                fields(excludeId(),
                include("name"),
                computed("amount", new Document("$size", Arrays.asList("$items"))),
                computed("avgprice", new Document("$avg", Arrays.asList("$items.price"))),
                computed("high", new Document("$max", Arrays.asList("$items.price"))),
                computed("low", new Document("$min", Arrays.asList("$items.price"))),
                computed("lt100", new Document("$size", new Document("$filter", new Document("input", "$items")
                        .append("as", "item").append("cond", new Document("$lt", Arrays.asList("$$item.price", 100))))))
                )))).forEach(printBlock);
    }
}
