import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

public class DBConnection {
    private static Connection connection;

    private static String dbName = "learn";
    private static String dbUser = "root";
    private static String dbPass = "2f1720dbd0e1abfa";
    private static volatile AtomicInteger totalCounter = new AtomicInteger();
    private static ArrayList<Voter> voters;
//    private static StringBuilder insertQuery = new StringBuilder();

    private final static String INSERT = "INSERT INTO voter_count(NDhash, name, birthDate, `count`) " +
            "VALUES(?, ?, ?, ?) ON DUPLICATE KEY UPDATE `count`=`count` + 1";

    public DBConnection() {
        voters = new ArrayList<>();
        totalCounter.set(0);
        getConnection();
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName +
                        "?user=" + dbUser + "&password=" + dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "NDhash INT NOT NULL, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "`count` INT NOT NULL, " +
                        "PRIMARY KEY(id))");
//                                "UNIQUE KEY(NDhash))");
//                                "UNIQUE KEY name_date(birthDate, name(50)))");
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void getCount() {
        System.out.println("Timestamp: " + (System.currentTimeMillis() - Loader.start) + " - " + String.format("%,d", totalCounter.get()));
    }

    public synchronized void setVoters(ArrayList<Voter> v) {
        voters.addAll(v);
        notify();
    }

    public synchronized void countVoter() throws SQLException, InterruptedException, TimeoutException {
        if (voters.isEmpty()) {
            long time = System.currentTimeMillis();
            wait(60000);
            if (System.currentTimeMillis() - time >= 60000) throw new TimeoutException();
        }
        PreparedStatement ps = getConnection().prepareStatement(INSERT);
        final int batchSize = voters.size();
        for (Voter v : voters) {
            ps.setInt(1, v.hashCode());
            ps.setString(2, v.getName());
            ps.setString(3, v.getBirthDay());
            ps.setInt(4, 1);
            ps.addBatch();
            if (totalCounter.incrementAndGet() % batchSize == 0) {
                ps.executeBatch();
            }
        }
        ps.executeBatch();
        voters.clear();
        connection.commit();
        ps.close();
    }

    public static void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
        ResultSet rs = getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
    }
}
