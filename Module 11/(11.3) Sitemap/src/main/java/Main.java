import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    static File mapFile = new File("sitemap.txt");
    static String baseLink = "https://lenta.ru/";

    public static void main(String[] args) {
        try {
            new ForkJoinPool().invoke(new LinkAction(baseLink, baseLink));
            System.out.println("Pool finished");
            List<String> linkList = new ArrayList<String>(LinkAction.getLinkSet());
            linkList.subList(1, linkList.size()).replaceAll(link -> link = "\t".repeat(StringUtils.countMatches(link, '/') - 2) + link);
            FileUtils.writeLines(mapFile, linkList);
            System.out.println("Main finished");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
