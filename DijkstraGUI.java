import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
class DijkstraGUI extends JFrame {
    private Graph graph;
    private JTextField verticesField, edgesField, sourceField, destinationField, resultField;
    private JButton calculateButton;

    public DijkstraGUI() {
        setTitle("Dijkstra's Algorithm GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2));

        JLabel verticesLabel = new JLabel("Vertices (comma separated):");
        verticesField = new JTextField();
        add(verticesLabel);
        add(verticesField);

        JLabel edgesLabel = new JLabel("Edges (source,destination,weight):");
        edgesField = new JTextField();
        add(edgesLabel);
        add(edgesField);

        JLabel sourceLabel = new JLabel("Source:");
        sourceField = new JTextField();
        add(sourceLabel);
        add(sourceField);

        JLabel destinationLabel = new JLabel("Destination:");
        destinationField = new JTextField();
        add(destinationLabel);
        add(destinationField);

        JLabel resultLabel = new JLabel("Shortest Path:");
        resultField = new JTextField();
        resultField.setEditable(false);
        add(resultLabel);
        add(resultField);

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateShortestPath();
            }
        });
        add(calculateButton);

        setVisible(true);
    }

    private void calculateShortestPath() {
        String verticesInput = verticesField.getText();
        String[] vertexLabels = verticesInput.split(",");
        graph = new Graph();

        for (String vertexLabel : vertexLabels) {
            Vertex vertex = new Vertex(vertexLabel.charAt(0));
            graph.addVertex(vertex);
        }

        String edgesInput = edgesField.getText();
        String[] edges = edgesInput.split(",");
        for (int i = 0; i < edges.length; i += 3) {
            char sourceLabel = edges[i].charAt(0);
            char destinationLabel = edges[i + 1].charAt(0);
            int weight = Integer.parseInt(edges[i + 2]);

            Vertex source = graph.getVertex(sourceLabel);
            Vertex destination = graph.getVertex(destinationLabel);
            graph.addEdge(source, destination, weight);
        }

        char sourceLabel = sourceField.getText().charAt(0);
        char destinationLabel = destinationField.getText().charAt(0);

        // Dijkstra's algorithm implementation here...
        Map<Vertex, Integer> distances = new HashMap<>();
        Map<Vertex, Vertex> previousVertices = new HashMap<>();
        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (Vertex vertex : graph.getVertices().values()) {
            distances.put(vertex, Integer.MAX_VALUE);
            previousVertices.put(vertex, null);
        }

        Vertex sourceVertex = graph.getVertex(sourceLabel);
        distances.put(sourceVertex, 0);
        pq.add(sourceVertex);

        while (!pq.isEmpty()) {
            Vertex current = pq.poll();

            for (Edge edge : graph.getEdges()) {
                if (edge.getSource().equals(current)) {
                    Vertex neighbor = edge.getDestination();
                    int newDistance = distances.get(current) + edge.getWeight();
                    if (newDistance < distances.get(neighbor)) {
                        distances.put(neighbor, newDistance);
                        previousVertices.put(neighbor, current);
                        pq.add(neighbor);
                    }
                }
            }
        }

        // Reconstruct shortest path
        LinkedList<Vertex> shortestPath = new LinkedList<>();
        Vertex currentVertex = graph.getVertex(destinationLabel);
        while (currentVertex != null) {
            shortestPath.addFirst(currentVertex);
            currentVertex = previousVertices.get(currentVertex);
        }

        // Display shortest path
        StringBuilder pathBuilder = new StringBuilder();
        for (Vertex vertex : shortestPath) {
            pathBuilder.append(vertex.getLabel()).append(" -> ");
        }
        if (pathBuilder.length() > 0) {
            pathBuilder.delete(pathBuilder.length() - 4, pathBuilder.length());
        }
        resultField.setText(pathBuilder.toString());
    }

        // Here goes the implementation of Dijkstra's algorithm
    }

    public void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DijkstraGUI();
            }
        });
    }
