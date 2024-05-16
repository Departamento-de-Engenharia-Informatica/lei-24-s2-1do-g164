package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.io.Serializable;
import java.util.ArrayList;

public class GreenSpaceRepository implements Serializable {
    private final ArrayList<GreenSpace> greenSpaceList = new ArrayList<>();

    public boolean registerGreenSpace(GreenSpaceType type, String name, String address, int area){
        GreenSpace greenSpace = new GreenSpace(type, name, address, area);
        if(greenSpaceIsUnique(greenSpace)){
            greenSpaceList.add(greenSpace);
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
}
