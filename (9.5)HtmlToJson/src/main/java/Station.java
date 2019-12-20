import java.util.List;

public class Station {
    String line;
    String name;
    List<String> connections;

    public Station(String line, String name, List<String> connections) {
        setLine(line);
        setName(name);
        setConnections(connections);
    }

    public String getLine() { return line; }
    public void setLine(String line) { this.line = line; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<String> getConnections() { return connections; }
    public void setConnections(List<String> connections) { this.connections = connections; }
}
