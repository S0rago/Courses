import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.RecursiveAction;

public class LinkAction extends RecursiveAction {
    private static String mainLink;
    private String baseLink;
    private static final Object mutexObj = new Object();
    private static volatile TreeSet<String> linkSet = new TreeSet<>();
    private List<LinkAction> actionList = new ArrayList<>();

    public LinkAction(String link) {
        baseLink = link;
    }
    public LinkAction(String link, String mainLink) {
        baseLink = link;
        LinkAction.mainLink = mainLink;
    }

    public static TreeSet<String> getLinkSet() {
        return linkSet;
    }
    public void setLinkSet(TreeSet<String> linkSet) {
        LinkAction.linkSet = linkSet;
    }

    @Override
    protected void compute() {
        System.out.println(String.format("%s: %s", Thread.currentThread().getName(),  baseLink));
        try {
            Document doc = Jsoup.connect(baseLink).get();
            Elements links = doc.select("a[href]");
            for (Element link : links) {
                String href = link.attr("abs:href");
                if (href.contains(mainLink) && !linkSet.contains(href)) {
                    synchronized (mutexObj) {
                        linkSet.add(href);
                    }
                    LinkAction action = new LinkAction(href);
                    action.fork();
                    actionList.add(action);
                }
            }
            for (LinkAction action : actionList)
                action.join();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
