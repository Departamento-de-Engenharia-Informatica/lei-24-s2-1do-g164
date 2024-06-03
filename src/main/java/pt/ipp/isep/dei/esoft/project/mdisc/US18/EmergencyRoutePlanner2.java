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
    static class Edge {
        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

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

    public static Edge[] dijkstra(int[][] graph, int src, int target, String[] names, ArrayList<Integer> finalPath) {
        int V = graph.length;
        int[] dist = new int[V];
        boolean[] sptSet = new boolean[V];
        int[] pred = new int[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(sptSet, false);
        Arrays.fill(pred, -1);

        dist[src] = 0;

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

        ArrayList<Edge> edges = new ArrayList<>();
        int current = target;
        while (pred[current] != -1) {
            edges.add(new Edge(pred[current], current, graph[pred[current]][current]));
            current = pred[current];
        }

        Edge[] reversedEdges = new Edge[edges.size()];
        for (int i = 0; i < edges.size(); i++) {
            reversedEdges[i] = edges.get(edges.size() - 1 - i);
        }

        addPathToFinal(pred, target, finalPath);

        return reversedEdges;
    }

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

    private static void writeGraphToCSV(int[][] graph, String filename, String[] names) {
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            StringBuilder sb = new StringBuilder();
            for (String name : names) {
                sb.append(name).append(";");
            }
            sb.deleteCharAt(sb.length() - 1).append("\n");

            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph[i].length; j++) {
                    sb.append(graph[i][j]).append(";");
                }
                sb.deleteCharAt(sb.length() - 1).append("\n");
            }
            writer.write(sb.toString());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Implemented method as per your request
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
            Scanner scannerMatriz = new Scanner(new File("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/US18/us18_matrix.csv"));
            Scanner scannerPointsNames = new Scanner(new File("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/US18/us18_points_names.csv"));

            String[] names = scannerPointsNames.nextLine().split(";");
            int V = names.length;
            int[][] graph = new int[V][V];

            for (int i = 0; i < V; i++) {
                String[] line = scannerMatriz.nextLine().split(";");
                for (int j = 0; j < V; j++) {
                    graph[i][j] = Integer.parseInt(line[j].trim());
                }
            }

            List<Integer> assemblyPoints = new ArrayList<>();
            for (int i = 0; i < names.length; i++) {
                if (names[i].startsWith("AP")) {
                    assemblyPoints.add(i);
                }
            }

            Scanner inputScanner = new Scanner(System.in);
            System.out.println("Enter the number of the starting point:");
            int origin = inputScanner.nextInt();

            if (origin >= 0 && origin < V && !assemblyPoints.contains(origin)) {
                ArrayList<Integer> finalPath = new ArrayList<>();
                Edge[] shortestPath = null;
                int minDist = Integer.MAX_VALUE;
                int closestAP = -1;

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

                if (shortestPath != null) {
                    System.out.println("Shortest path from " + names[origin] + " to " + names[closestAP] + ": " + finalPath);
                    System.out.println("Distance: " + minDist);

                    Graph<String, DefaultEdge> g = new SimpleWeightedGraph<>(DefaultEdge.class);
                    for (String name : names) {
                        g.addVertex(name);
                    }
                    for (Edge edge : shortestPath) {
                        g.addEdge(names[edge.from], names[edge.to]);
                    }

                    writeGraphToCSV(graph, "src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/US18_initial_graph.csv", names);
                    writeFinalPathToCSV(new ArrayList<>(Arrays.asList(shortestPath)), "src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/US18_final_path.csv", names);

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
