import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.RecursiveAction;

public class LinkAction extends RecursiveAction {
    private String baseLink;

    public LinkAction(String link) {
        baseLink = link;
    }

    @Override
    protected void compute() {
        System.out.println(String.format("%s: %s", Thread.currentThread().getName(),  baseLink));
        TreeSet<String> linkSet = new TreeSet<>();
        try {
            Document doc = Jsoup.connect(baseLink).get();
            Elements links = doc.select("a[href]");
            for (Element link : links) {
                String href = link.attr("abs:href");
                if (href.contains(Main.baseLink) && !Main.linkSet.contains(href)) {
                    Main.linkSet.add(href);
                    linkSet.add(href);
                }
            }
            List<LinkAction> actionList = new ArrayList<>();
            for (String link : linkSet) {
                LinkAction action = new LinkAction(link);
                action.fork();
                actionList.add(action);
            }
            for (LinkAction action : actionList) {
                action.join();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
