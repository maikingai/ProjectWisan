import java.util.LinkedList;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Scanner;

public class prims1 {
    class newnode {
        int destination;
        int weight;

        newnode(int a, int b) {
            destination = a;
            weight = b;
        }
    }

    static class Graph {
        int V;
        LinkedList<newnode>[] adj;

        Graph(int edge) {
            V = edge;
            adj = new LinkedList[V];
            for (int o = 0; o < V; o++)
                adj[o] = new LinkedList<>();
        }
    }

    class node {
        int vertex;
        int key;
    }

    class comparator implements Comparator<node> {

        @Override
        public int compare(node originnode, node newnode) {
            return originnode.key - newnode.key;
        }
    }

    void addEdge(Graph graph, int src, int destination, int weight) {

        newnode originnode = new newnode(destination, weight);
        newnode node = new newnode(src, weight);
        graph.adj[src].addLast(originnode);
        graph.adj[destination].addLast(node);
    }

    void prims_mst(Graph graph) {
        Boolean[] mstset = new Boolean[graph.V];
        node[] edge = new node[graph.V];
        int[] parent = new int[graph.V];
        for (int o = 0; o < graph.V; o++)
            edge[o] = new node();
        for (int o = 0; o < graph.V; o++) {
            mstset[o] = false;
            edge[o].key = Integer.MAX_VALUE;
            edge[o].vertex = o;
            parent[o] = -1;
        }
        mstset[0] = true;
        edge[0].key = 0;
        TreeSet<node> queue = new TreeSet<node>(new comparator());

        for (int o = 0; o < graph.V; o++)
            queue.add(edge[o]);
        while (!queue.isEmpty()) {
            node originnode = queue.pollFirst();
            mstset[originnode.vertex] = true;
            for (newnode iterator : graph.adj[originnode.vertex]) {
                if (mstset[iterator.destination] == false) {
                    if (edge[iterator.destination].key > iterator.weight) {
                        queue.remove(edge[iterator.destination]);
                        edge[iterator.destination].key = iterator.weight;
                        queue.add(edge[iterator.destination]);
                        parent[iterator.destination] = originnode.vertex;
                    }
                }
            }
        }
        for (int o = 1; o < graph.V; o++)
            System.out.println(parent[o] + " " + "-" + " " + o);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of vertices : ");
        int V = scanner.nextInt();
        Graph graph = new Graph(V);
        prims1 g = new prims1();
        System.out.print("Number of edges: ");
        int E = scanner.nextInt();
        System.out.println("edge: (ex 0 1 10) : ");
        for (int i = 0; i < E; i++) {
            int src = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            g.addEdge(graph, src, destination, weight);
        }
        g.prims_mst(graph);
        scanner.close();
    }
}
