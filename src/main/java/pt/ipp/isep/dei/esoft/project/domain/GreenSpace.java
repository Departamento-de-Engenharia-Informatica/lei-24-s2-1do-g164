package pt.ipp.isep.dei.esoft.project.domain;

public class GreenSpace {
    private String name;
    private String address;
    private int area;

    public GreenSpace(String name, String address, int area){
        this.name = name;
        this.address = address;
        this.area = area;
    }

    public boolean equals(GreenSpace g){
        return this.name.equals(g.getName());
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }
}
