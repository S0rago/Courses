import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    private static Document doc;
    static {
        try {
            doc = Jsoup.connect("https://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_%D1%81%D1%82%D0%B0%D0%BD%D1%86%D0%B8%D0%B9_%D0%9C%D0%BE%D1%81%D0%BA%D0%BE%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_%D0%BC%D0%B5%D1%82%D1%80%D0%BE%D0%BF%D0%BE%D0%BB%D0%B8%D1%82%D0%B5%D0%BD%D0%B0")
                        .maxBodySize(0)
                        .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static TreeMap<String,String> lines = new TreeMap<>();
    private static List<Station> stations = new ArrayList<>();

    public static void main(String[] args) {
        Elements tables = doc.select("table.sortable");
        for (Element tab : tables) {
            Elements rows = tab.select("tbody tr");
            for (Element rw : rows) {
                Elements tds = rw.select("td");
                if (!tds.text().isBlank()) parseRow(tds);
            }
        }
    }

    private static void parseRow(Elements els) {
        String lineNum = els.get(0).select("span").first().text();
        String lineName = els.get(0).select("span").attr("title");
        String station = els.get(1).select("a[href]").get(0).text();
        List<String> cons = getCons(els.get(3)); //TODO Parse cons from links

        Station st = new Station(lineNum, lineName, cons);
        stations.add(st);
        System.out.println(lineNum + " - " + lineName + " - " + station + " - " + cons);
    }

    private static void addStationsToJSON() {

    }

    private static void addLinesToJSON() {

    }

    private static void writeToJSON() {
    }

    private static List<String> getCons(Element el) {
        List<String> conList = new ArrayList<>();
        el.select("a").forEach(a -> {
            String decoded = URLDecoder.decode(a.attr("href"), StandardCharsets.UTF_8);
            for (String part : decoded.split(" ")) {
                String con = part.replace("/wiki/", "").replaceAll("_\\(.*?\\)", "");
                conList.add(con);
            }
        });
        return conList;
        //TODO Find a way to parse connections (go to con link and get name?)
    }
}