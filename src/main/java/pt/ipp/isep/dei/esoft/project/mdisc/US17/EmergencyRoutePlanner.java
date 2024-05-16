package pt.ipp.isep.dei.esoft.project.mdisc.US17;

import java.io.*;
import java.util.*;

class Vertex {
    public final String name;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;

    public Vertex(String argName) {
        name = argName;
    }

    public String toString() {
        return name;
    }
}

class Edge {
    public final Vertex target;
    public final double weight;

    public Edge(Vertex argTarget, double argWeight) {
        target = argTarget;
        weight = argWeight;
    }
}

public class EmergencyRoutePlanner {
    private static final String INPUT_CSV_FILE = "input.csv";
    private static final String OUTPUT_CSV_FILE = "output.csv";

    public static void computePaths(Vertex source) {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies) {
                Vertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);
                    v.minDistance = distanceThroughU;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target) {
        List<Vertex> path = new ArrayList<>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);
        Collections.reverse(path);
        return path;
    }

    public static void generateOutputCSV(List<Vertex> path, double duration) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(OUTPUT_CSV_FILE));
            writer.println("Path,Destination,Duration");
            StringBuilder pathStr = new StringBuilder();
            for (Vertex vertex : path) {
                pathStr.append(vertex.name).append(" -> ");
            }
            pathStr.delete(pathStr.length() - 4, pathStr.length());
            writer.println(pathStr.toString() + "," + duration);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing output CSV file: " + e.getMessage());
        }
    }

    public static void visualizeGraph(List<Vertex> path) {
        // Visualization logic goes here
        // You can use libraries like Graphviz or implement your own visualization
        System.out.println("Graph Visualization");
        // Placeholder for visualization code
    }

    public static void main(String[] args) {
        Map<String, Vertex> vertexMap = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(INPUT_CSV_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Vertex v1 = vertexMap.computeIfAbsent(parts[0].trim(), Vertex::new);
                Vertex v2 = vertexMap.computeIfAbsent(parts[1].trim(), Vertex::new);
                double distance = Double.parseDouble(parts[2].trim());
                if (v1.adjacencies == null) v1.adjacencies = new Edge[]{new Edge(v2, distance)};
                else {
                    Edge[] newAdjacencies = Arrays.copyOf(v1.adjacencies, v1.adjacencies.length + 1);
                    newAdjacencies[newAdjacencies.length - 1] = new Edge(v2, distance);
                    v1.adjacencies = newAdjacencies;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading input CSV file: " + e.getMessage());
            return;
        }

        Vertex assemblyPoint = vertexMap.get("Assembly Point");
        Vertex start = vertexMap.get("Start Point");
        computePaths(start);
        List<Vertex> path = getShortestPathTo(assemblyPoint);
        double duration = assemblyPoint.minDistance;
        visualizeGraph(path);
        generateOutputCSV(path, duration);
    }
}

