package pt.ipp.isep.dei.esoft.project.mdisc.US17;

import org.apache.log4j.BasicConfigurator;
import pt.ipp.isep.dei.esoft.project.mdisc.util.MST_PLOTTER;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class EmergencyRoutePlanner {

    public static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    // Function to find the vertex with minimum distance value
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

    // Function to implement Dijkstra's single source shortest path algorithm
    // for a graph represented using adjacency matrix representation
    public static Edge[] dijkstra(int[][] graph, int src, int target, String[] names, ArrayList<Integer> finalPath) {
        int V = graph.length;
        int[] dist = new int[V]; // The output array. dist[i] will hold the shortest distance from src to i
        boolean[] sptSet = new boolean[V]; // sptSet[i] will be true if vertex i is included in shortest path tree
        int[] pred = new int[V]; // Array to store the shortest path tree

        // Initialize all distances as INFINITE and sptSet[] as false
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(sptSet, false);
        Arrays.fill(pred, -1);

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices not yet processed
            int u = minDistance(dist, sptSet, V);

            // If the minimum distance vertex is the target, we can stop early
            if (u == target) {
                break;
            }

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the picked vertex
            for (int v = 0; v < V; v++) {
                // Update dist[v] if it's not in sptSet, there's an edge from u to v,
                // and total weight of path from src to v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    pred[v] = u;
                }
            }
        }

        // Construct the path as an array of edges
        ArrayList<Edge> edges = new ArrayList<>();
        int current = target;
        while (pred[current] != -1) {
            edges.add(new Edge(pred[current], current, graph[pred[current]][current]));
            current = pred[current];
        }

        // Reverse the edges to get them in the correct order
        Edge[] reversedEdges = new Edge[edges.size()];
        for (int i = 0; i < edges.size(); i++) {
            reversedEdges[i] = edges.get(edges.size() - 1 - i);
        }

        // Print the shortest distance and path from source to target
        addPathToFinal(pred, target, finalPath);

        // Return the array of edges representing the shortest path
        return reversedEdges;
    }

    // Add the path to the final path array
    private static void addPathToFinal(int[] pred, int target, ArrayList<Integer> finalPath) {
        ArrayList<Integer> tempPath = new ArrayList<>();
        while (target != -1) {
            tempPath.add(0, target);
            target = pred[target];
        }
        if (!finalPath.isEmpty()) {
            tempPath.remove(0); // Remove the first element to avoid duplication
        }
        finalPath.addAll(tempPath);
    }

    public static int[][] readGraphFromCSV(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
        String line;
        int[][] graph = null;
        int row = 0;

        while ((line = br.readLine()) != null) {
            // Remove BOM if present
            if (row == 0 && line.startsWith("\uFEFF")) {
                line = line.substring(1);
            }
            String[] values = line.split(";");
            if (graph == null) {
                graph = new int[values.length][values.length];
            }
            for (int col = 0; col < values.length; col++) {
                graph[row][col] = Integer.parseInt(values[col].trim());
            }
            row++;
        }

        br.close();
        return graph;
    }

    public static String[] readCSVIntoArray(String csvFile) {

        String line;
        String[] dataArray = null;
        try (        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "UTF-8"));
        ) {
            if ((line = br.readLine()) != null) {
                dataArray = line.split(";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataArray;
    }

    // Write the final path to a CSV file
    public static void writeFinalPathToCSV(ArrayList<Edge> edges, String filePath, String[] names) {
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            for (Edge edge : edges) {
                writer.println(names[edge.from] + ";" + names[edge.to] + ";" + edge.weight);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Write the initial graph to a CSV file
    public static void writeGraphToCSV(int[][] graph, String filePath, String[] names) {
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph[i].length; j++) {
                    if (graph[i][j] != 0) {
                        writer.println(names[i] + ";" + names[j] + ";" + graph[i][j]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Driver code
    public static void main(String[] args) throws IOException {
        int[][] graph = readGraphFromCSV("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/US17/us17_matrix.csv");
        String[] names = readCSVIntoArray("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/US17/us17_points_names.csv");
        System.out.println(names[0]);
        int target = 0;
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals("AP")) {
                target = i;
            }
        }

        Scanner sc = new Scanner(System.in);
        boolean success = false;
        int src = 0;
        do {
            System.out.print("Enter the name of the starting sign: ");
            String name = sc.nextLine();
            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(name)) {
                    src = i;
                    success = true;
                }
            }
        } while (!success);

        ArrayList<Integer> mustpass = new ArrayList<>();
        String name = "";
        do {
            System.out.print("Insert the signs you want to pass through (type done to finish): ");
            name = sc.nextLine();
            if (!name.equals("done")) {
                boolean added = false;
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        mustpass.add(i);
                        added = true;
                    }
                }
                if (!added) {
                    System.out.println("Invalid sign name. Please try again.");
                }
            }
        } while (!name.equals("done"));

        // Execute Dijkstra's algorithm for each segment
        int currentSrc = src;
        int totalCost = 0;
        ArrayList<Edge> finalEdges = new ArrayList<>();
        ArrayList<Integer> finalPath = new ArrayList<>();
        Edge[] edges = null;
        for (int sign : mustpass) {
            edges = dijkstra(graph, currentSrc, sign, names, finalPath);
            for (Edge edge : edges) {
                totalCost += edge.weight;
                finalEdges.add(edge);
            }
            currentSrc = sign;
        }

        // Finally, find the path from the last must-pass node to the target
        edges = dijkstra(graph, currentSrc, target, names, finalPath);
        for (Edge edge : edges) {
            totalCost += edge.weight;
            finalEdges.add(edge);
        }

        // Print the total cost
        System.out.println("Total cost: " + totalCost);

        // Print the final path
        System.out.print("Final Path: ");
        for (int i = 0; i < finalPath.size(); i++) {
            System.out.print(names[finalPath.get(i)]);
            if (i < finalPath.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();

        writeGraphToCSV(graph, "src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/US17_initial_graph.csv", names);
        writeFinalPathToCSV(finalEdges, "src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/US17_final_path.csv", names);
        BasicConfigurator.configure();
        System.out.println("-------------------------------------------------------------------------PLOTTING GRAPHS-----------------------------------------------------");
        MST_PLOTTER.plotMST("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/US17_final_path.csv", "US17_SHORTESTPATH_OUTPUT");
        MST_PLOTTER.plotMST("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/US17_initial_graph.csv", "US17_INPUT");
        System.out.println("-------------------------------------------------------------------------GRAPHS PLOTTED SUCCESSFULLY-----------------------------------------------------");

    }

}
