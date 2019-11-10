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
            Document doc = Jsoup.connect("https://lenta.ru").get();
            Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
            for (Element im : images) {
                System.out.println(im.attr("src"));
                getImage(im.attr("src"));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void getImage(String imgURL) throws Exception {
        URL url = new URL(imgURL);
        FileUtils.copyURLToFile(url, new File("images/"+ FilenameUtils.getName(url.getPath())));
    }
}
