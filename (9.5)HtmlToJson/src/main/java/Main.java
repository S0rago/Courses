import javafx.util.Pair;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

public class Main {
    static Document doc;
    static {
        try {
            doc = Jsoup.connect("https://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_%D1%81%D1%82%D0%B0%D0%BD%D1%86%D0%B8%D0%B9_%D0%9C%D0%BE%D1%81%D0%BA%D0%BE%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_%D0%BC%D0%B5%D1%82%D1%80%D0%BE%D0%BF%D0%BE%D0%BB%D0%B8%D1%82%D0%B5%D0%BD%D0%B0")
                        .maxBodySize(0)
                        .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static HashMap<String,String> lines = new HashMap<>();

    public static void main(String[] args) {
        Elements tables = doc.select("table.sortable");
        for (Element tab : tables) {
            Elements rows = tab.select("tbody tr");
            rows.remove(0);
            for (Element rw : rows) {
                Elements tds = rw.select("td");
                if (!tds.text().isBlank())
                    parseRow(tds);
            }
        }

        lines.keySet().forEach(k -> System.out.println(k + " - " + lines.get(k)));
        System.out.println(lines.size());

        addToJSON();
    }

    public static void parseRow(Elements els) {
        Pair<String, String> line = new Pair<>(els.get(0).select("span").first().text(), els.get(0).select("span").attr("title"));
        lines.put(line.getKey(), line.getValue());
        String station = els.get(1).select("a[href]").get(0).text();
        String cons = els.get(3).text();
        System.out.println( line + " - " + station + " - " + cons);
    }

    public static void addToJSON() {
        TreeMap<String, String> tree = new TreeMap<>(lines);
        JSONObject jsonObject = new JSONObject();
        String mainJsonObject = JSONValue.toJSONString(tree);
        JSONArray jsonArray = new JSONArray();

//        lines.keySet().forEach(k -> {
//                jsonObject.put("number", k);
//                jsonObject.put("name", lines.get(k));
//                jsonArray.add(jsonObject);
//        });
//
//        mainJsonObject.put("lines", jsonArray);
        try (FileWriter file = new FileWriter("src/main/test.json")) {
            file.write(mainJsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(mainJsonObject);
    }

    public static void addCon() {}
}