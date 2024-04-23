package pt.ipp.isep.dei.esoft.project.mdisc.util;

public class Vertice {
    private String x;
    private static final String xomissao ="0";

    public Vertice (){
        x = xomissao;
    }

    public Vertice(String nome) {
        this.x = nome;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String toString(){
        return x;
    }
}
