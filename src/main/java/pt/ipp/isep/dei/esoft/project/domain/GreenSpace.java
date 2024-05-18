package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.ENUM.GreenSpaceType;

public class GreenSpace {

    private GreenSpaceType type;
    private String name;
    private String address;
    private int area;

    public GreenSpace(GreenSpaceType type,String name, String address, int area){
        this.type = type;
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

    public GreenSpaceType getType() {
        return type;
    }

    public void setType(GreenSpaceType type) {
        this.type = type;
    }
}
