package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.enums.GreenSpaceTypeENUM;

import java.io.Serializable;

public class GreenSpace implements Serializable {

    private GreenSpaceTypeENUM type;
    private String name;
    private String address;
    private int area;
    private String emailGSM;

    public GreenSpace(GreenSpaceTypeENUM type, String name, String address, int area, String emailGSM){
        this.type = type;
        this.name = name;
        this.address = address;
        this.area = area;
        this.emailGSM = emailGSM;
    }

    public boolean equals(GreenSpace g){
        return this.name.equals(g.getName());
    }

    public String toString() {
        return String.format("Name: %s - Address: %s - Area: %d hectares - Type: %s", name, address, area, type.toString());
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

    public GreenSpaceTypeENUM getType() {
        return type;
    }

    public void setType(GreenSpaceTypeENUM type) {
        this.type = type;
    }

    public String getEmailGSM() {
        return emailGSM;
    }

    public void setEmailGSM(String emailGSM) {
        this.emailGSM = emailGSM;
    }
}
