import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class XMLHandler extends DefaultHandler {
    private Voter voter;
    private final ArrayList<Voter> voters;
    private int voterCount = 0;
    private DBConnection dbConnection;

    public XMLHandler(DBConnection con) {
        voters = new ArrayList<>();
        dbConnection = con;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            if (qName.equals("voter") && voter == null) {
                voter = new Voter(attributes.getValue("name"), attributes.getValue("birthDay").replace('.', '-'));
            } else if (qName.equals("visit") && voter != null) {
                voters.add(voter);
                voterCount++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("voter")) {
            voter = null;
            if (voterCount == 200000) {
                dbConnection.setVoters(voters);
                voterCount = 0;
                voters.clear();
            }
        } else if (qName.equals("voters") && voterCount < 200000) {
            dbConnection.setVoters(voters);
        }
    }
}
