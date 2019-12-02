import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_%D1%81%D1%82%D0%B0%D0%BD%D1%86%D0%B8%D0%B9_%D0%9C%D0%BE%D1%81%D0%BA%D0%BE%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_%D0%BC%D0%B5%D1%82%D1%80%D0%BE%D0%BF%D0%BE%D0%BB%D0%B8%D1%82%D0%B5%D0%BD%D0%B0").get();
//            Elements lines = doc.select("td:eq(0) a");
            Elements lines = doc.select("td:eq(0) span");
            lines.stream()
                    .filter(l->l.text()
                    .matches("[а-яА-Я ]+"))
                    .forEach(l -> System.out.println(l.text()));

//            Elements stations = doc.select("td:eq(1) a");
//            stations.stream()
//                    .filter(st -> st.text().matches("[а-яА-Я ]+"))
//                    .forEach(st -> System.out.println("  " + st.text()));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}