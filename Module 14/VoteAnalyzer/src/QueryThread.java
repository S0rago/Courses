import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public class QueryThread extends Thread {
    private DBConnection dbConnection;

    public QueryThread(DBConnection con) {
        dbConnection = con;
    }

    @Override
    public void run() {
        while(isAlive()) {
            try {
                dbConnection.countVoter();
                dbConnection.getCount();
            } catch (SQLException | InterruptedException throwables) {
                throwables.printStackTrace();
            } catch (TimeoutException ex) {
                break;
            }
        }
    }
}
