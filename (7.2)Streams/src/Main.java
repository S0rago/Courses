import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.ArrayList;
import java.util.Date;


public class Main {

    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        System.out.println(airport.getAllAircrafts().size() + " самолетов");

        Date current = new Date();
        ArrayList<Terminal> terminals = new ArrayList<>(airport.getTerminals());

        terminals.forEach(t -> t.getFlights().stream().filter(f -> {
                    long timeDiff = ((f.getDate().getTime() - current.getTime())/3600000);
                    return f.getType() == Flight.Type.DEPARTURE && timeDiff <= 2 && timeDiff >= 0;
                }).forEach(System.out::println));
    }
}