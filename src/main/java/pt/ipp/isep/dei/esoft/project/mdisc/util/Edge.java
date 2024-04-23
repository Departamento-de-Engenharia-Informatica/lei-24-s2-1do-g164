package pt.ipp.isep.dei.esoft.project.mdisc.util;

public class Edge {
        private Vertice p1;
        private Vertice p2;
        private Double comp;
        private static final Vertice p1omissao =new Vertice("0");
        private static final Vertice p2omissao = new Vertice("");
        private static final Double compomissao =0.0;

        public Edge (){
            p1 = p1omissao;
            p2 = p2omissao;
            comp = compomissao;

        }

        public Edge(Vertice p1, Vertice p2, double comp) {
            this.p1 = p1;
            this.p2=p2;
            this.comp=comp;
        }

    @Override
    public String toString() {
        return (p1.toString() + "-" + p2.toString() + "-" + this.comp);
    }

    public Vertice getP1() {
            return p1;
        }

        public void setP1(Vertice p1) {
            this.p1 = p1;
        }

        public Vertice getP2() {
            return p2;
        }

        public void setP2(Vertice p2) {
            this.p2 = p2;
        }

        public Double getComp() {
            return comp;
        }

        public void setComp(Double comp) {
            this.comp = comp;
        }
    }



