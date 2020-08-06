import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    public static void main(String[] args) throws IOException {
        Elements tables = doc.select("table.sortable");
        for (Element tab : tables) {
            Elements rows = tab.select("tbody tr");
            for (Element rw : rows) {
                Elements tds = rw.select("td");
                if (!tds.text().isBlank()) parseRow(tds);
            }
        }
        writeToJSON();
    }

    private static void parseRow(Elements els) {
        String lineNum = els.get(0).select("span").first().text();
        String lineName = els.get(0).select("span").attr("title");
        String stationName = els.get(1).select("a[href]").get(0).text();
        List<String> cons = getCons(els.get(3));

        Station st = new Station(lineNum, stationName, cons);
        stations.add(st);
        lines.putIfAbsent(lineNum, lineName);
        System.out.println(lineNum + " - " + lineName + " - " + stationName + " - " + cons);
    }

    private static List<String> getCons(Element el) {
        List<String> conList = new ArrayList<>();
        el.select("a").forEach(a -> {
            String decoded = URLDecoder.decode(a.attr("href"), StandardCharsets.UTF_8);
            for (String part : decoded.split(" ")) {
                String con = part.replace("/wiki/", "")
                        .replaceAll("_", " ")
                        .replaceAll(" \\(.*?\\)", "");
                conList.add(con);
            }
        });
        return conList;
    }

    private static void writeToJSON() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        ObjectNode objectNode = objectMapper.createObjectNode();

        addStationsToJSON(objectMapper, objectNode);
        addLinesToJSON(objectMapper, objectNode);
        objectMapper.writeValue(new FileOutputStream("src/main/test.json", false), objectNode);
    }

    private static void addStationsToJSON(ObjectMapper objectMapper, ObjectNode objectNode) {
        ObjectNode stationNode = objectMapper.createObjectNode();
        for(String lineName : lines.keySet()) {
            List<String> line = new ArrayList<>();
            stations.stream().filter(st -> st.getLine().equals(lineName)).forEach(st -> line.add(st.getName()));
            stationNode.putPOJO(lineName, line);
        }
        objectNode.putPOJO("Stations", stationNode);
    }

    private static void addLinesToJSON(ObjectMapper objectMapper, ObjectNode objectNode) {
        ArrayNode linesNode = objectMapper.createArrayNode();

        for (Map.Entry<String, String> entry : lines.entrySet()) {
            ObjectNode oneNode = objectMapper.createObjectNode();
            oneNode.put("number", entry.getKey());
            oneNode.put("name", entry.getValue());
            linesNode.add(oneNode);
        }
        objectNode.putPOJO("lines", linesNode);
    }
}