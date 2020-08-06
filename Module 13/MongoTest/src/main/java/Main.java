import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class Main {
    static MongoClientURI uri = new MongoClientURI(
            "mongodb+srv://sorago:8a1V5HeG4f9b@cluster0-ctjuj.gcp.mongodb.net/SkillBox?retryWrites=true&w=majority");
    static MongoClient mongoClient = new MongoClient(uri);
    static MongoDatabase database = mongoClient.getDatabase("SkillBox");
    static MongoCollection<Document> collection = database.getCollection("TestSkillDemo");
    public static void main(String[] args) {
        try {
            collection.drop();
            ArrayList<Student> students = readFile(new File("src/main/resources/mongo.csv"));

            for (Student st : students) {
                Document studentDoc = new Document()
                        .append("Name", st.getName())
                        .append("Age", st.getAge())
                        .append("Courses", Arrays.asList(st.getCourses()));

                collection.insertOne(studentDoc);
            }

            //Общее число студентов
            System.out.println("Студентов: " +
                    collection.countDocuments());

            //Число студентов старше 40 лет
            System.out.println("Студентов старше 40: " +
                    collection.countDocuments(Document.parse("{Age: {$gt: 40}}")));

            //Имя самого молодого студента
            System.out.println("Самый молодой студент: " +
                    Objects.requireNonNull(collection.find().sort(BsonDocument.parse("{Age: 1}")).first()).get("Name"));

            //Список курсов самого старого студента
            System.out.println("Курсы самого старого студента: " +
                    Objects.requireNonNull(collection.find().sort(BsonDocument.parse("{Age: -1}")).first()).get("Courses"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Student> readFile(File file) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            String pattern  = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
            for(String line : lines) {
                String[] fragments = line.split(pattern);
                students.add(new Student(
                        fragments[0],
                        Integer.parseInt(fragments[1]),
                        fragments[2].replace("\"", "").split(pattern) ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return students;
    }
}
