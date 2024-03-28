import java.util.*;
public class Graph {
    private Map<Character, Vertex> vertices;
    private List<Edge> edges;

    public Graph() {
        vertices = new HashMap<>();
        edges = new ArrayList<>();
    }

    public void addVertex(Vertex vertex) {
        vertices.put(vertex.getLabel(), vertex);
    }

    public void addEdge(Vertex source, Vertex destination, int weight) {
        edges.add(new Edge(source, destination, weight));
    }

    public Vertex getVertex(char label) {
        return vertices.get(label);
    }

    public Map<Character, Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
