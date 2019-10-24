import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RoadCalculatorTest extends TestCase {
    List<Station> route;

    @Override
    protected void setUp() throws Exception {
        route = new ArrayList<>();
        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторая");

        route.add(new Station("Апельсиновая", line1));
        route.add(new Station("Театральная", line1));
        route.add(new Station("Временная", line2));
        route.add(new Station("Проходная", line2));

       // RouteCalculator cal = new RouteCalculator();

    }
    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 8.5;
        assertEquals(expected, actual);
    }
}
