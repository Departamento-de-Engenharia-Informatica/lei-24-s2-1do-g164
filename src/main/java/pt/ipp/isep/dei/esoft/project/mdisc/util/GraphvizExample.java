package pt.ipp.isep.dei.esoft.project.mdisc.util;

import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Factory;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.Node;

import java.io.File;
import java.io.IOException;

public class GraphvizExample {

    public static void main(String[] args) {
        // Create a mutable graph
        MutableGraph graph = Factory.mutGraph("example").setDirected(true);

        // Add nodes to the graph
        Node nodeA = Factory.node("A");
        Node nodeB = Factory.node("B");
        Node nodeC = Factory.node("C");
        Node nodeD = Factory.node("D");
        nodeA.link(nodeB);
        graph.add(nodeA);
        graph.add(nodeB);
        graph.add(nodeC);
        graph.add(nodeD);

        // Add edges to the graph
        graph.add(Factory.mutNode(nodeA.toString()));
        graph.add(Factory.mutNode(nodeB.toString()).addLink(Factory.to(nodeC)));
        graph.add(Factory.mutNode(nodeC.toString()).addLink(Factory.to(nodeD)));
        graph.add(Factory.mutNode(nodeD.toString()).addLink(Factory.to(nodeA)));

        // Render the graph using Graphviz and save as PNG
        try {
            Graphviz.fromGraph(graph).width(800).render(Format.PNG).toFile(new File("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/output_graph.png"));
            System.out.println("Graph visualization saved as 'output_graph.png'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
