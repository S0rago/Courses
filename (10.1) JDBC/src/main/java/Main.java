import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?serverTimezone=Europe/Moscow";
        String user = "root";
        String pass = "root";

        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select name, ifnull(count(subscription_date)/(max(month(subscription_date)) - min(month(subscription_date))), 0) as avg from courses c left outer join subscriptions s on c.id = s.course_id group by name");
            while (rs.next())
                System.out.println(rs.getString("name") + " - " + rs.getDouble("avg"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

//select name, ifnull(count(subscription_date)/(max(month(subscription_date)) - min(month(subscription_date))), 0) as avg from courses c left outer join subscriptions s on c.id = s.course_id group by name;