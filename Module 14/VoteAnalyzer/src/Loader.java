import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.text.RuleBasedCollator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Loader
{
    public static long start;

    public static void main(String[] args) throws Exception
    {
        String fileName = "res/data-1572M.xml";
        start = System.currentTimeMillis();
        System.out.println(start);
        long parseMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        DBConnection con = new DBConnection();

        QueryThread qt = new QueryThread(con);
        qt.start();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler(con);
        parser.parse(new File(fileName), handler);
        qt.join();
        parseMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - parseMem;
        System.out.println("Parsing took: " + parseMem + " bytes and " + (System.currentTimeMillis() - start - 60000) + " ms"); //Время без учета таймаута на втором потоке
        DBConnection.printVoterCounts();
    }
}