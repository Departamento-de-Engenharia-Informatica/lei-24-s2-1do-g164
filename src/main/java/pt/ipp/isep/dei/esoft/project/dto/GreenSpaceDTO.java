package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.repository.enums.GreenSpaceTypeENUM;

/**
 * The type Green space dto.
 */
public class GreenSpaceDTO {
    /**
     * The Name.
     */
    public String name;
    /**
     * The Address.
     */
    public String address;
    /**
     * The Area.
     */
    public int area;
    /**
     * The Type.
     */
    public GreenSpaceTypeENUM type;
    /**
     * The Email gsm.
     */
    public String emailGSM;

    /**
     * Instantiates a new Green space dto.
     *
     * @param name     the name
     * @param address  the address
     * @param area     the area
     * @param type     the type
     * @param emailGSM the email gsm
     */
    public GreenSpaceDTO(String name, String address, int area, GreenSpaceTypeENUM type, String emailGSM) {
        this.name = name;
        this.address = address;
        this.area = area;
        this.type = type;
        this.emailGSM = emailGSM;
    }

    public String toString(){
        return name;
    }

    /**
     * To string long string.
     *
     * @return the string
     */
    public String toStringLong() {
        return String.format("Name: %s - Address: %s - Area: %d hectares - Type: %s", name, address, area, type.toString());
    }

}
