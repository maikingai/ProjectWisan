

import java.util.ArrayList;
import java.util.Arrays;

public class Prim_algorithm {

    // It will store the path as a String
    String via;

    // It will store the shortest path
    ArrayList<Integer> path;

    // It will store the shortest distance
    int distance;

    // Constructor
    public Prim_algorithm(){
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

        // Initialize key array to store minimum weight edge
        int key[] = new int[size];
        Arrays.fill(key, Integer.MAX_VALUE); // Initialize with maximum values

        // Initialize parent array
        int parent[] = new int[size];
        Arrays.fill(parent, -1); // Initialize all vertices as independent sets

        // Set key for source vertex to 0
        key[source] = 0;

        // Construct MST
        for (int count = 0; count < size - 1; count++) {
            int u = minKey(key);
            selectedEdges.add(new Edge(parent[u], u, graph[u][parent[u]]));
            key[u] = 0;

            // Update key and parent for adjacent vertices of u
            for (int v = 0; v < size; v++) {
                if (graph[u][v] != 0 && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // Calculate total distance
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

    private int minKey(int key[]) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < key.length; v++) {
            if (key[v] != 0 && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
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
