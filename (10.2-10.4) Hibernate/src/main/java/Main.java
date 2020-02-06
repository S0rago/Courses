import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        String hql = "SELECT s.id, p.studentName, c.id, p.courseName, p.price, p.subscriptionDate\n" +
                "FROM Purchase p, Student s, Course c\n" +
                "WHERE s.name = p.studentName AND c.name = p.courseName";
        List<Object[]> testList = session.createQuery(hql).getResultList();
        for (int i = 0; i < testList.size(); i++) {
            Object[] row = testList.get(i);
            LinkedPurchase linkedPurchase = new LinkedPurchase(row);
            session.save(linkedPurchase);
            if (i % 10 == 0) {
                session.flush();
                session.clear();
            }
           // System.out.println(row[0].toString() + " - " + row[1].toString() + " - " + row[2].toString()
                //    + " - " + row[3].toString() + " - " + row[4].toString() + " - " + row[5].toString());
        }

        transaction.commit();
        sessionFactory.close();
    }


    //select s.id, p.student_name, c.id, p.course_name, p.price, p.subscription_date from purchaselist p
    //	join students s on s.name = p.student_name
    //    join courses c on c.name = p.course_name;


    //SELECT s.id, p.student_name, c.id, p.course_name, p.price, p.subscription_date
    //FROM PurchaseList pl, Student s, Course c
    //WHERE s.name = p.student_name AND c.name = p.course_name;

    //SELECT op.username, op.email, orders.p_id, orders.o_id, product.listed_price
    //FROM Orders order, OrderProcessing op, Product product
    //WHERE op.u_id = order.u_id AND product.p_id = orders.p_id
    //ORDER BY op.username
}
