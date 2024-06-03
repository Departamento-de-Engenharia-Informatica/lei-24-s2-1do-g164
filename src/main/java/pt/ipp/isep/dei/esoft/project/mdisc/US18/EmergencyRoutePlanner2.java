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

    private static void writeFinalPathToCSV(Edge[] edges, String filename, String[] names) {
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            StringBuilder sb = new StringBuilder();
            for (Edge edge : edges) {
                sb.append(names[edge.from]).append(" -> ").append(names[edge.to]).append(";");
            }
            sb.append("\n");
            writer.write(sb.toString());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        BasicConfigurator.configure();

        try {
            Scanner scanner = new Scanner(new File("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/data.csv"));
            String[] names = scanner.nextLine().split(";");
            int V = names.length;
            int[][] graph = new int[V][V];

            for (int i = 0; i < V; i++) {
                String[] line = scanner.nextLine().split(";");
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

            for (int i = 0; i < V; i++) {
                if (!assemblyPoints.contains(i)) {
                    ArrayList<Integer> finalPath = new ArrayList<>();
                    Edge[] shortestPath = dijkstra(graph, i, assemblyPoints.get(0), names, finalPath);
                    int minDist = Integer.MAX_VALUE;
                    int closestAP = assemblyPoints.get(0);
                    for (int ap : assemblyPoints) {
                        ArrayList<Integer> tempPath = new ArrayList<>();
                        Edge[] tempEdges = dijkstra(graph, i, ap, names, tempPath);
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

                    System.out.println("Shortest path from " + names[i] + " to " + names[closestAP] + ": " + finalPath);
                    System.out.println("Distance: " + minDist);

                    Graph<String, DefaultEdge> g = new SimpleWeightedGraph<>(DefaultEdge.class);
                    for (String name : names) {
                        g.addVertex(name);
                    }
                    for (Edge edge : shortestPath) {
                        g.addEdge(names[edge.from], names[edge.to]);
                    }

                    writeGraphToCSV(graph, "src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/US18_initial_graph.csv", names);
                    writeFinalPathToCSV(shortestPath, "src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/US18_final_path.csv", names);

                    MST_PLOTTER.plotMST("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/US18_final_path.csv", "US18_SHORTESTPATH_OUTPUT");
                    MST_PLOTTER.plotMST("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/US18_initial_graph.csv", "US18_INPUT");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
