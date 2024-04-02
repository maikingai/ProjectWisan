

import java.util.ArrayList;
import java.util.Arrays;

public class KruskalAlgorithm {

    // It will store the path as a String
    String via;

    // It will store the shortest path
    ArrayList<Integer> path;

    // It will store the shortest distance
    int distance;

    // Constructor
    public KruskalAlgorithm(){
        path = new ArrayList<Integer>();
        distance = 0;
        via = "";
    }

    public void Algorithm(int graph[][], int source){

        // Check if the source vertex is valid
        if (source < 0 || source >= graph.length) {
            System.out.println("Invalid source vertex.");
            return;
        }

        // size is the number of vertices
        int size = graph.length;

        // It will store the selected edges
        ArrayList<Edge> selectedEdges = new ArrayList<>();

        // Initialize parent array
        int parent[] = new int[size];
        Arrays.fill(parent, -1); // Initialize all vertices as independent sets

        // Sort edges based on weight
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (graph[i][j] != 0) {
                    edges.add(new Edge(i, j, graph[i][j]));
                }
            }
        }
        edges.sort((a, b) -> Integer.compare(a.weight, b.weight));

        // Selecting edges
        for (Edge edge : edges) {
            int parent1 = find(parent, edge.src);
            int parent2 = find(parent, edge.dest);
            if (parent1 != parent2) {
                selectedEdges.add(edge);
                union(parent, parent1, parent2);
            }
        }

        // Calculate distance
        for (Edge edge : selectedEdges) {
            distance += edge.weight;
        }

        // Form path
        for (Edge edge : selectedEdges) {
            path.add(edge.src);
            path.add(edge.dest);
        }

        // Construct via string
        for (int vertex : path) {
            via += " " + vertex;
        }
    }

    private int find(int parent[], int vertex) {
        if (parent[vertex] < 0) {
            return vertex;
        }
        return parent[vertex] = find(parent, parent[vertex]);
    }

    private void union(int parent[], int x, int y) {
        int xSet = find(parent, x);
        int ySet = find(parent, y);
        if (xSet != ySet) {
            parent[ySet] = xSet;
        }
    }

    public void reset(){
        path.clear();
        distance = 0;
        via = "";
    }

    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
}