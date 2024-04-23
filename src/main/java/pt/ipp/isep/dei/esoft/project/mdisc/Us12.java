package pt.ipp.isep.dei.esoft.project.mdisc;

import pt.ipp.isep.dei.esoft.project.mdisc.util.Edge;
import pt.ipp.isep.dei.esoft.project.mdisc.util.Vertice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Us12 {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Edge> graph = readGraphFromCSV("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/US13_JardimDosSentimentos.csv");
        System.out.println(graph.get(0));

    }

    public static void readFile(String[] args){
        String file="";
        System.out.print("Insert file: ");
        Scanner scan= new Scanner(System.in);
        file= scan.next();
        ArrayList<Edge> edge= new ArrayList<>();
        try{
            edge=readToEdges(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<Edge> readToEdges (String file) throws FileNotFoundException {
        Scanner scanFile = new Scanner(new File(file));
        String[] line;
        ArrayList<Edge> edges = new ArrayList<>();
        while (scanFile.hasNextLine()) {
            line = scanFile.nextLine().split(";");
            Edge edge = new Edge(new Vertice(line[0]), new Vertice(line[1]), Integer.parseInt(line[2]));
            edges.add(edge);
        }
        return edges;
    }

    private static ArrayList<Edge> readGraphFromCSV(String filePath) {
        ArrayList<Edge> edges = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split(";");
                    if (parts.length == 3) {
                        String u = parts[0];
                        String v = parts[1];
                        int weight = Integer.parseInt(parts[2].trim());
                        Edge edge = new Edge(new Vertice(u), new Vertice(v), weight);
                        edges.add(edge);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (NumberFormatException e) {
            System.out.println("Invalid data format in CSV file.");
        }
        return edges;
    }


}
