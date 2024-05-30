package pt.ipp.isep.dei.esoft.project.mdisc.US17;

import java.util.*;

public class teste {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] graph = {
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        int source = 0;
        int destination = 4;
        int[] mustPassNodes = {2, 6};

        PathResult result = dijkstra(graph, source, destination, mustPassNodes);

        if (result.shortestDistance != INF) {
            System.out.println("Shortest distance from node " + source + " to node " + destination + ": " + result.shortestDistance);
            System.out.println("Path: " + result.path);
        } else {
            System.out.println("No path exists from node " + source + " to node " + destination);
        }
    }

    public static PathResult dijkstra(int[][] graph, int source, int destination, int[] mustPassNodes) {
        int V = graph.length;
        int[][] distances = new int[V][V];
        boolean[] visited = new boolean[V];
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        for (int[] row : distances) {
            Arrays.fill(row, INF);
        }

        // Initialize distances from source to all nodes
        distances[source][0] = 0;
        pq.offer(new Node(source, 0, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.vertex;
            int mustPassCount = node.mustPassCount;

            if (visited[u] || distances[u][mustPassCount] < node.distance) continue;
            visited[u] = true;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0) {
                    int newDistance = node.distance + graph[u][v];
                    int newMustPassCount = Arrays.binarySearch(mustPassNodes, v) >= 0 ? mustPassCount + 1 : mustPassCount;

                    if (newMustPassCount <= mustPassNodes.length && newDistance < distances[v][newMustPassCount]) {
                        distances[v][newMustPassCount] = newDistance;
                        pq.offer(new Node(v, newDistance, newMustPassCount));
                    }
                }
            }
        }

        // Find the shortest distance among paths that pass through all must pass nodes
        int shortestDistance = INF;
        for (int i = 0; i <= mustPassNodes.length; i++) {
            shortestDistance = Math.min(shortestDistance, distances[destination][i]);
        }

        // Construct the shortest path
        StringBuilder path = new StringBuilder();
        if (shortestDistance != INF) {
            path.append(destination);
            int current = destination;
            int mustPassCount = 0;
            while (current != source) {
                for (int v = 0; v < V; v++) {
                    if (distances[current][mustPassCount] - graph[current][v] == distances[v][mustPassCount - 1]) {
                        current = v;
                        if (Arrays.binarySearch(mustPassNodes, current) >= 0) {
                            mustPassCount--;
                        }
                        path.insert(0, " -> " + current);
                        break;
                    }
                }
            }
        }

        return new PathResult(shortestDistance, path.toString());
    }

    static class Node {
        int vertex;
        int distance;
        int mustPassCount;

        Node(int vertex, int distance, int mustPassCount) {
            this.vertex = vertex;
            this.distance = distance;
            this.mustPassCount = mustPassCount;
        }
    }

    static class PathResult {
        int shortestDistance;
        String path;

        PathResult(int shortestDistance, String path) {
            this.shortestDistance = shortestDistance;
            this.path = path;
        }
    }
}
