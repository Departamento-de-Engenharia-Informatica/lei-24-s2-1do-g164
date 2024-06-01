package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Green space repository.
 */
public class GreenSpaceRepository implements Serializable {
    private final ArrayList<GreenSpace> greenSpaceList = new ArrayList<>();

    /**
     * Register green space boolean.
     *
     * @param gs the gs
     * @return the boolean
     */
    public boolean registerGreenSpace(GreenSpace gs){
        if(greenSpaceIsUnique(gs)){
            greenSpaceList.add(gs);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Green space is unique boolean.
     *
     * @param greenSpace the green space
     * @return the boolean
     */
    public boolean greenSpaceIsUnique(GreenSpace greenSpace) {
        for (GreenSpace g : greenSpaceList) {
            if (g.equals(greenSpace)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets green space list.
     *
     * @param email the email
     * @return the green space list
     */
    public ArrayList<GreenSpace> getGreenSpaceList(String email) {
        ArrayList<GreenSpace> greenSpaceListGSM = new ArrayList<>();
        for (GreenSpace gs : this.greenSpaceList) {
            if(gs.getEmailGSM().equals(email)){
                greenSpaceListGSM.add(gs);
            }
        }
        return greenSpaceListGSM;
    }
}
