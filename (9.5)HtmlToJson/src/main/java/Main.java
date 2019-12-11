import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
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
    private static List<String> stationsOnLine = new ArrayList<>();
    private static JSONObject mainJsonObject = new JSONObject();
    private static JSONObject lineObj = new JSONObject();


    public static void main(String[] args) {
        Elements tables = doc.select("table.sortable");
        for (Element tab : tables) {
            Elements rows = tab.select("tbody tr");
            for (Element rw : rows) {
                Elements tds = rw.select("td");
                if (!tds.text().isBlank()) parseRow(tds);
            }
        }
        mainJsonObject.put("stations", lineObj);
        addLinesToJSON();
        writeToJSON(mainJsonObject);
    }

    private static void parseRow(Elements els) {
        String lineNum = els.get(0).select("span").first().text();
        String lineName = els.get(0).select("span").attr("title");
        String station = els.get(1).select("a[href]").get(0).text();
        String cons = els.get(3).text();

        if (lines.isEmpty()) {
            lines.put(lineNum, lineName);
            stationsOnLine.add(station);
        }
        else if (!lines.containsKey(lineNum)) {
            addStationsToJSON(lines.lastKey());
            lines.put(lineNum, lineName);
            stationsOnLine.clear();
        }
        stationsOnLine.add(station);
        System.out.println(lineNum + " - " + lineName + " - " + station + " - " + cons);
    }

    private static void addStationsToJSON(String lineNum) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(stationsOnLine);
        lineObj.put(lineNum, jsonArray);
    }

    private static void addLinesToJSON() {
        JSONArray jsonArray = new JSONArray();
        lines.forEach((key, value) -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("number", key);
            jsonObject.put("name", value);
            jsonArray.add(jsonObject);
        });
        mainJsonObject.put("lines", jsonArray);
    }

    private static void writeToJSON(JSONObject jObj) {
        try (FileWriter file = new FileWriter("src/main/test.json")) {
            file.write(jObj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addCon() {/*
    TODO Find a way to parse connections (go to con link and get name?)
    */}
}