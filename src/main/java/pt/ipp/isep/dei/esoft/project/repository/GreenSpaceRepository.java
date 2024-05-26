package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.io.Serializable;
import java.util.ArrayList;

public class GreenSpaceRepository implements Serializable {
    private final ArrayList<GreenSpace> greenSpaceList = new ArrayList<>();

    public boolean registerGreenSpace(GreenSpace gs){
        if(greenSpaceIsUnique(gs)){
            greenSpaceList.add(gs);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean greenSpaceIsUnique(GreenSpace greenSpace) {
        for (GreenSpace g : greenSpaceList) {
            if (g.equals(greenSpace)) {
                return false;
            }
        }
        return true;
    }

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
