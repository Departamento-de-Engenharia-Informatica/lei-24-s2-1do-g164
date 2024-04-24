package pt.ipp.isep.dei.esoft.project.mdisc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

public class Us12 {

    static class Edge {
        String from;
        String to;
        int weight;

        public Edge(String from, String to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }


    public static void main(String[] args) {
        String csvFile = "src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/US13_JardimDosSentimentos.csv"; // Path to your CSV file
        List<Edge> edges = readGraphFromCSV(csvFile);

        List<Edge> mstEdges = new ArrayList<>();
        int minCost = calculateMinimumSpanningTreeCost(edges, mstEdges);

        System.out.println("Minimum cost of the spanning tree: " + minCost);
        System.out.println();
        System.out.println("Minimum cost path in MST: " + getMinimumCostPath(mstEdges, "src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/output.csv"));
        System.out.println("Graph exported into: files/output.csv");
    }

    public static ArrayList<Edge> readGraphFromCSV(String filename) {
        ArrayList<Edge> edges = new ArrayList<>();
        ArrayList<String> nodeSet = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String from = parts[0];
                String to = parts[1];
                int weight = Integer.parseInt(parts[2]);

                edges.add(new Edge(from, to, weight));
                nodeSet.add(from);
                nodeSet.add(to);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return edges;
    }

    public static int calculateMinimumSpanningTreeCost(List<Edge> edges, List<Edge> mstEdges) {
        edges.sort(Comparator.comparingInt(edge -> edge.weight));

        int minCost = 0;
        Map<String, String> parent = new HashMap<>();

        for (Edge edge : edges) {
            String rootX = find(parent, edge.from);
            String rootY = find(parent, edge.to);

            if (!rootX.equals(rootY)) {
                parent.put(rootX, rootY);
                minCost += edge.weight;
                mstEdges.add(edge);
            }
        }

        return minCost;
    }

    private static String find(Map<String, String> parent, String node) {
        if (!parent.containsKey(node)) {
            parent.put(node, node);
        }
        while (!parent.get(node).equals(node)) {
            node = parent.get(node);
        }
        return node;
    }

    public static String getMinimumCostPath(List<Edge> mstEdges, String outputFilePath) {
        StringBuilder path = new StringBuilder();
        Set<String> visited = new HashSet<>();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (Edge edge : mstEdges) {
                if (!visited.contains(edge.from)) {
                    path.append(edge.from).append(" - ");
                    visited.add(edge.from);
                }
                if (!visited.contains(edge.to)) {
                    path.append(edge.to).append(" - ");
                    visited.add(edge.to);
                }

                writer.write(edge.from + ";" + edge.to + ";" + edge.weight);
                writer.newLine();
            }
            if (!mstEdges.isEmpty()) {
                path.append(mstEdges.get(0).from);
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path.toString();
    }
}
