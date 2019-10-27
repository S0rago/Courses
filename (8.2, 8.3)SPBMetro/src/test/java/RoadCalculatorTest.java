import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RoadCalculatorTest extends TestCase {
    List<Station> route;
    List<Station> testRouteOnLine;
    List<Station> testRouteWithConnection;
    List<Station> testRouteViaLine;
    List<Station> testShortestRoute;
    RouteCalculator routeCalculator;
    StationIndex index;

    @Override
    protected void setUp() throws Exception {
        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");
        line1.addStation(new Station("Апельсиновая", line1));
        line1.addStation(new Station("Театральная", line1));
        line1.addStation(new Station("Елизаровская", line1));
        line2.addStation(new Station("Временная", line2));
        line2.addStation(new Station("Проходная", line2));
        line2.addStation(new Station("Петроградская", line2));
        line3.addStation(new Station("Садовая", line3));
        line3.addStation(new Station("Приморская", line3));
        line3.addStation(new Station("Балтийская", line3));

        testRouteOnLine = new ArrayList<>();
        testRouteOnLine.add(line1.getStations().get(0));
        testRouteOnLine.add(line1.getStations().get(1));
        testRouteOnLine.add(line1.getStations().get(2));

        testRouteWithConnection = new ArrayList<>();
        testRouteWithConnection.add(line1.getStations().get(0));
        testRouteWithConnection.add(line1.getStations().get(1));
        testRouteWithConnection.add(line1.getStations().get(2));
        testRouteWithConnection.add(line2.getStations().get(0));
        testRouteWithConnection.add(line2.getStations().get(1));
        testRouteWithConnection.add(line2.getStations().get(2));

        testRouteViaLine = new ArrayList<>();
        testRouteViaLine.add(line1.getStations().get(0));
        testRouteViaLine.add(line1.getStations().get(1));
        testRouteViaLine.add(line1.getStations().get(2));
        testRouteViaLine.add(line2.getStations().get(0));
        testRouteViaLine.add(line2.getStations().get(1));
        testRouteViaLine.add(line3.getStations().get(1));
        testRouteViaLine.add(line3.getStations().get(2));

        testShortestRoute = new ArrayList<>();
        testShortestRoute.add(line1.getStations().get(0));
        testShortestRoute.add(line1.getStations().get(1));
        testShortestRoute.add(line1.getStations().get(2));
        testShortestRoute.add(line2.getStations().get(0));
        testShortestRoute.add(line2.getStations().get(1));


        index = new StationIndex();
        index.addLine(line1);
        index.addLine(line2);
        index.addStation(line1.getStations().get(0));
        index.addStation(line1.getStations().get(1));
        index.addStation(line1.getStations().get(2));
        index.addStation(line2.getStations().get(0));
        index.addStation(line2.getStations().get(1));
        index.addStation(line2.getStations().get(2));
        index.addStation(line3.getStations().get(0));
        index.addStation(line3.getStations().get(1));
        index.addStation(line3.getStations().get(2));

        ArrayList<Station> list = new ArrayList<>();
        list.add(line1.getStations().get(2));
        list.add(line2.getStations().get(0));
        index.addConnection(list);
        list.clear();
        list.add(line2.getStations().get(1));
        list.add(line3.getStations().get(1));
        index.addConnection(list);
//        list.clear();
//        list.add(line1.getStations().get(0));
//        list.add(line3.getStations().get(2));
//        index.addConnection(list);

        routeCalculator = new RouteCalculator(index);

    }
    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(testRouteOnLine);
        double expected = 5.0;
        assertEquals(expected, actual);
    }

    public void testGetShortestRoute() {
        route = routeCalculator.getShortestRoute(index.getStation("Апельсиновая"),
                index.getStation("Проходная"));
        assertEquals(testShortestRoute, route);
    }

    public void testGetRouteOnline() {
        route = routeCalculator.getShortestRoute(index.getStation("Апельсиновая"),
                index.getStation("Елизаровская"));
        assertEquals(testRouteOnLine, route);
    }

    public void testGetRouteWithOneConnection() {
        route = routeCalculator.getShortestRoute(index.getStation("Апельсиновая"),
                index.getStation("Петроградская"));
        assertEquals(testRouteWithConnection, route);
    }

    public void testGetRouteViaConnectedLine() {
        route = routeCalculator.getShortestRoute(index.getStation("Апельсиновая"),
                index.getStation("Балтийская"));
        assertEquals(testRouteViaLine, route);
    }


}
