package pt.ipp.isep.dei.esoft.project.mdisc.util;

public class Edge {

    private String from;
    private String to;
    private int weight;

    public Edge(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}



