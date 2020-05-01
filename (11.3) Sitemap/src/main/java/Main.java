import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;

public class Main {
    static File mapFile = new File("sitemap.txt");
    static String baseLink = "https://www.chipdip.ru/";
    static TreeSet<String> linkSet = new TreeSet<>();

    public static void main(String[] args) {
        try {
            new ForkJoinPool().invoke(new LinkAction(baseLink));
            System.out.println("Pool finished");
            List<String> linkList = new ArrayList<String>(linkSet);
            linkList.subList(1, linkList.size()).replaceAll(link -> link = "\t".repeat(StringUtils.countMatches(link, '/') - 2) + link);
            FileUtils.writeLines(mapFile, linkList);
            System.out.println("Main finished");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
