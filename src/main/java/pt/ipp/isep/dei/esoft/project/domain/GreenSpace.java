package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.enums.GreenSpaceTypeENUM;

import java.io.Serializable;

/**
 * The type Green space.
 */
public class GreenSpace implements Serializable {

    private GreenSpaceTypeENUM type;
    private String name;
    private String address;
    private int area;
    private String emailGSM;

    /**
     * Instantiates a new Green space.
     *
     * @param type     the type
     * @param name     the name
     * @param address  the address
     * @param area     the area
     * @param emailGSM the email gsm
     */
    public GreenSpace(GreenSpaceTypeENUM type, String name, String address, int area, String emailGSM){
        this.type = type;
        this.name = name;
        this.address = address;
        this.area = area;
        this.emailGSM = emailGSM;
    }

    /**
     * Equals boolean.
     *
     * @param g the g
     * @return the boolean
     */
    public boolean equals(GreenSpace g){
        return this.name.equals(g.getName());
    }

    public String toString() {
        return String.format("Name: %s - Address: %s - Area: %d hectares - Type: %s", name, address, area, type.toString());
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets area.
     *
     * @return the area
     */
    public int getArea() {
        return area;
    }

    /**
     * Sets area.
     *
     * @param area the area
     */
    public void setArea(int area) {
        this.area = area;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public GreenSpaceTypeENUM getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(GreenSpaceTypeENUM type) {
        this.type = type;
    }

    /**
     * Gets email gsm.
     *
     * @return the email gsm
     */
    public String getEmailGSM() {
        return emailGSM;
    }

    /**
     * Sets email gsm.
     *
     * @param emailGSM the email gsm
     */
    public void setEmailGSM(String emailGSM) {
        this.emailGSM = emailGSM;
    }
}
