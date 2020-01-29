import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        TreeMap<String, Integer> map = new TreeMap<>();
        for (int i = 1;; i++) {
//            Student student = session.get(Student.class, i);
//            if(student == null) break;
//            System.out.println(student.toString());
            Teacher teacher = session.get(Teacher.class, i);
            if (teacher == null) break;
            map.put(teacher.getName(), teacher.getCourses().size());
        }
        for (Map.Entry e : map.entrySet()) {
            System.out.println(e.getKey() + " - " + e.getValue());
        }
        sessionFactory.close();
    }
}
