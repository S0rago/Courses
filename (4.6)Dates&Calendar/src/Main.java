import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, Calendar.NOVEMBER, 1);
        for (int i = 0; calendar.getTime().getTime() <= System.currentTimeMillis(); i++) {
            System.out.println(i + " - " + dateFormat.format(calendar.getTime()) + " - " + calendar.getDisplayName(Calendar.DAY_OF_WEEK, 1, new Locale("Ru")));
            calendar.add(Calendar.YEAR, 1);
        }
    }
}
