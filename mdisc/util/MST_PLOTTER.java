package pt.ipp.isep.dei.esoft.project.mdisc.util;

import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static guru.nidi.graphviz.model.Factory.*;

public class MST_PLOTTER {
    public static void plotMST(String filePath, String fName) throws IOException {
        Graph g = graph("MST");

        String csvFilePath = filePath;

        Map<String, Node> nodeMap = new HashMap<>();

        // Read CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean headerSkipped = true;

            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue; // Skip header line
                }

                String[] parts = line.split(";");
                if (parts.length < 2) {
                    continue; // Invalid line format, skip
                }

                String fromNodeName = parts[0];
                String toNodeName = parts[1];
                String weightStr = (parts.length > 2) ? parts[2] : "";

                // Get or create nodes
                Node fromNode = nodeMap.computeIfAbsent(fromNodeName, name -> node(name));
                Node toNode = nodeMap.computeIfAbsent(toNodeName, name -> node(name));

                // Add edge with weight (if weight is present)
                if (!weightStr.isEmpty()) {
                    int weight = Integer.parseInt(weightStr);
                    g = g.with(fromNode.link(to(toNode).with(Label.of(weightStr))));
                } else {
                    g = g.with(fromNode.link(to(toNode)));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("CSV file not found: " + csvFilePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + csvFilePath);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Invalid weight format in CSV");
            e.printStackTrace();
        }

        // Render the graph to a PNG file
        Graphviz.fromGraph(g).width(900).render(Format.PNG).toFile(new File("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/" + fName + ".png"));
    }
}
