package pt.ipp.isep.dei.esoft.project.mdisc.US17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class EmergencyRoutePlanner {

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
    public static void dijkstra(int[][] graph, int src, int target, String[] names) {
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

        // Print the shortest distance and path from source to target
        printSolution(dist, pred, src, target, names);
    }

    // Function to print the shortest distance and path from source to target
    private static void printSolution(int[] dist, int[] pred, int src, int target, String[] names) {
        System.out.print(names[src] + "\t\t" + dist[target] + "                 \t\t" + names[src]);
        printPath(pred, target, names);
        System.out.println();
    }

    // Recursive function to print the path from source to the given vertex
    private static void printPath(int[] pred, int vertex, String[] names) {
        if (pred[vertex] == -1) {
            return;
        }
        printPath(pred, pred[vertex], names);
        System.out.print(" -> " + names[vertex]);
    }

    public static int[][] readGraphFromCSV(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
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
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            if ((line = br.readLine()) != null) {
                dataArray = line.split(";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataArray;
    }

    // Driver code
    public static void main(String[] args) throws IOException {
        int[][] graph = readGraphFromCSV("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/us17_matrix.csv");
        String[] names = readCSVIntoArray("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/us17_points_names.csv");
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
            System.out.print("Insert the signs you want to pass through(type done to finish): ");
            name = sc.nextLine();
            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(name)) {
                    mustpass.add(i);
                }
            }
        } while (!name.equals("done"));
        System.out.println(mustpass);
        System.out.println("Vertex\t        Distance from Source\tPath");
        int last = target;
        for (int sign : mustpass) {
            target = sign;
            if (sign == mustpass.get(mustpass.size() - 1) && mustpass.size() != 1) {
                target = last;
            }
            dijkstra(graph, src, target, names);
            src = target;
        }
            dijkstra(graph, src, last, names);
    }
}

