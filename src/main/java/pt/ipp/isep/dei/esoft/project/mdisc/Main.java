package pt.ipp.isep.dei.esoft.project.mdisc;

import org.apache.log4j.BasicConfigurator;
import pt.ipp.isep.dei.esoft.project.mdisc.util.Edge;
import pt.ipp.isep.dei.esoft.project.mdisc.util.MST_PLOTTER;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BasicConfigurator.configure();
        Scanner sc = new Scanner(System.in);
        String csvFile;
        List<Edge> edges;

        while (true) {
            System.out.print("\nType the name of the CSV file you want to use: ");
            String name = sc.nextLine();
            csvFile = "src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/" + name;
            try {
                edges = readGraphFromCSV(csvFile);
                break;
            } catch (FileNotFoundException e) {
                System.out.println("File doesn't exist, make sure that it is in the files directory and that you typed the '.csv'");
            }
        }
        List<Edge> mstEdges = new ArrayList<>();
        System.out.println("------------------LOADING----------------");
        MST_PLOTTER.plotMST(csvFile,"input");
        System.out.println("-----------------------------------------");
        System.out.println("Input graph has been plotted into: files/input.png");
        System.out.println("-----------------------------------------");
        int minCost = calculateMinimumSpanningTreeCost(edges, mstEdges);
        System.out.println("Minimum cost of the spanning tree: " + minCost);
        System.out.println("-----------------------------------------");
        exportToCSV(mstEdges, "src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/output.csv");
        System.out.println("MST exported into: files/output.csv");
        System.out.println("-----------------------------------------");
        MST_PLOTTER.plotMST("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/output.csv","outputMST");
        System.out.println("The MST has been plotted into files/outputMST.png");
    }

    public static ArrayList<Edge> readGraphFromCSV(String filename) throws FileNotFoundException{
        ArrayList<Edge> edges = new ArrayList<>();
        ArrayList<String> nodeSet = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        try {
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
        } catch (FileNotFoundException e){

        } catch (IOException e) {

        }
        finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return edges;
    }

    public static int calculateMinimumSpanningTreeCost(List<Edge> edges, List<Edge> mstEdges) {
        bubbleSort(edges);
        int nNodes = countUniqueNodes(edges);
        int minCost = 0;
        Map<String, String> parent = new HashMap<>();
        int n = 0;
        while (n < nNodes-1) {
            for (Edge edge : edges) {
                String rootX = find(parent, edge.getFrom());
                String rootY = find(parent, edge.getTo());
                if (!rootX.equals(rootY)) {
                    parent.put(rootX, rootY);
                    minCost += edge.getWeight();
                    mstEdges.add(edge);
                }
            }
            n++;
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

    public static void exportToCSV(List<Edge> mstEdges, String outputFilePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (Edge edge : mstEdges) {
                writer.write(edge.getFrom() + ";" + edge.getTo() + ";" + edge.getWeight());
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int countUniqueNodes(List<Edge> edges) {
        Set<String> uniqueNodes = new HashSet<>();

        for (Edge edge : edges) {
            uniqueNodes.add(edge.getFrom());
            uniqueNodes.add(edge.getTo());
        }

        return uniqueNodes.size();
    }

    public static void bubbleSort(List<Edge> arr) {
        int n = arr.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr.get(j).getWeight() > arr.get(j+1).getWeight()) {
                    // Swap arr[j] and arr[j+1]
                    Edge temp = arr.get(j);
                    arr.set(j, arr.get(j+1));
                    arr.set(j+1, temp);
                }
            }
        }
    }
}
