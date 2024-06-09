package pt.ipp.isep.dei.esoft.project.mdisc.US18;
import org.apache.log4j.BasicConfigurator;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import pt.ipp.isep.dei.esoft.project.mdisc.util.MST_PLOTTER;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EmergencyRoutePlanner2 {
    // Inner class to represent an edge
    static class Edge {
        int from; // source vertex
        int to;   // destination vertex
        int weight; // edge weight

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    // Method to find the vertex with the minimum distance that hasn't been included in the SPT set
    private static int minDistance(int[] dist, boolean[] sptSet, int V) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // Implementation of Dijkstra's algorithm to find the shortest path
    public static Edge[] dijkstra(int[][] graph, int src, int target, String[] names, ArrayList<Integer> finalPath) {
        int V = graph.length;
        int[] dist = new int[V];
        boolean[] sptSet = new boolean[V];
        int[] pred = new int[V];

        // Initialize all distances as infinity and sptSet[] as false
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(sptSet, false);
        Arrays.fill(pred, -1);

        // Distance from source vertex to itself is 0
        dist[src] = 0;

        // Find the shortest path to all vertices
        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet, V);
            if (u == target) {
                break;
            }
            sptSet[u] = true;
            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && graph[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    pred[v] = u;
                }
            }
        }

        // Create a list of edges to store the shortest path
        ArrayList<Edge> edges = new ArrayList<>();
        int current = target;
        while (pred[current] != -1) {
            edges.add(new Edge(pred[current], current, graph[pred[current]][current]));
            current = pred[current];
        }

        // Reverse the list of edges
        Edge[] reversedEdges = new Edge[edges.size()];
        for (int i = 0; i < edges.size(); i++) {
            reversedEdges[i] = edges.get(edges.size() - 1 - i);
        }

        addPathToFinal(pred, target, finalPath);

        return reversedEdges;
    }

    // Method to add the final path to a list
    private static void addPathToFinal(int[] pred, int target, ArrayList<Integer> finalPath) {
        ArrayList<Integer> tempPath = new ArrayList<>();
        while (target != -1) {
            tempPath.add(0, target);
            target = pred[target];
        }
        if (!finalPath.isEmpty()) {
            tempPath.remove(0);
        }
        finalPath.addAll(tempPath);
    }

    // Method to write the graph to a CSV file
    private static void writeGraphToCSV(int[][] graph, String filename, String[] names) {
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph[i].length; j++) {
                    if (graph[i][j] != 0) {
                        sb.append(names[i]).append(";").append(names[j]).append(";").append(graph[i][j]).append("\n");
                    }
                }
            }
            writer.write(sb.toString());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to write the final path to a CSV file
    public static void writeFinalPathToCSV(ArrayList<Edge> edges, String filePath, String[] names) {
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            for (Edge edge : edges) {
                writer.println(names[edge.from] + ";" + names[edge.to] + ";" + edge.weight);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();

        try {
            // Reading CSV files
            Scanner scannerMatriz = new Scanner(new File("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/US18/us18_matrix.csv"));
            Scanner scannerPointsNames = new Scanner(new File("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/US18/us18_points_names.csv"));

            // Reading point names
            String[] names = scannerPointsNames.nextLine().split(";");
            int V = names.length;
            int[][] graph = new int[V][V];

            // Converting the adjacency matrix into a two-dimensional array of integers
            for (int i = 0; i < V; i++) {
                String[] line = scannerMatriz.nextLine().split(";");
                for (int j = 0; j < V; j++) {
                    graph[i][j] = Integer.parseInt(line[j].trim());
                }
            }

            // Identifying assembly points
            List<Integer> assemblyPoints = new ArrayList<>();
            for (int i = 0; i < names.length; i++) {
                if (names[i].startsWith("AP")) {
                    assemblyPoints.add(i);
                }
            }

            // Input the origin point
            Scanner inputScanner = new Scanner(System.in);
            System.out.println("Enter the number of the starting point:");
            int origin = inputScanner.nextInt();

            // Checking the validity of the origin point
            if (origin >= 0 && origin < V && !assemblyPoints.contains(origin)) {
                ArrayList<Integer> finalPath = new ArrayList<>();
                Edge[] shortestPath = null;                int minDist = Integer.MAX_VALUE;
                int closestAP = -1;

                // Finding the nearest assembly point using Dijkstra's algorithm
                for (int ap : assemblyPoints) {
                    ArrayList<Integer> tempPath = new ArrayList<>();
                    Edge[] tempEdges = dijkstra(graph, origin, ap, names, tempPath);
                    int dist = 0;
                    for (Edge edge : tempEdges) {
                        dist += edge.weight;
                    }
                    if (dist < minDist) {
                        minDist = dist;
                        closestAP = ap;
                        shortestPath = tempEdges;
                        finalPath = tempPath;
                    }
                }

                // Displaying the shortest path and distance
                if (shortestPath != null) {
                    System.out.println("Shortest path from " + names[origin] + " to " + names[closestAP] + ": " + finalPath);
                    System.out.println("Distance: " + minDist);

                    // Creating the graph and adding vertices and edges
                    Graph<String, DefaultEdge> g = new SimpleWeightedGraph<>(DefaultEdge.class);
                    for (String name : names) {
                        g.addVertex(name);
                    }
                    for (Edge edge : shortestPath) {
                        g.addEdge(names[edge.from], names[edge.to]);
                    }

                    // Writing the initial graph and the final path to CSV files
                    writeGraphToCSV(graph, "src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/US18_initial_graph.csv", names);
                    writeFinalPathToCSV(new ArrayList<>(Arrays.asList(shortestPath)), "src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/US18_final_path.csv", names);

                    // Plotting the graphs using MST_PLOTTER
                    MST_PLOTTER.plotMST("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/US18_final_path.csv", "US18_SHORTESTPATH_OUTPUT");
                    MST_PLOTTER.plotMST("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/US18_initial_graph.csv", "US18_INPUT");
                } else {
                    System.out.println("No valid path found.");
                }
            } else {
                System.out.println("Invalid starting point.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
