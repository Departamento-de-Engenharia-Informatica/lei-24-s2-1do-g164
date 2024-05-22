package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.repository.ENUM.GreenSpaceType;

public class GreenSpaceDTO {
    public String name;
    public String address;
    public int area;
    public GreenSpaceType type;

    public GreenSpaceDTO(String name, String address, int area, GreenSpaceType type) {
        this.name = name;
        this.address = address;
        this.area = area;
        this.type = type;
    }

    public String toString(){
        return name;
    }

}
