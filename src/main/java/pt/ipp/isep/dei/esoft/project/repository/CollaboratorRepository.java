package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class CollaboratorRepository implements Serializable {
    private final ArrayList<Collaborator> collaboratorList = new ArrayList<>();
    public ArrayList<Collaborator> getCollaboratorList() {
        return collaboratorList;
    }

    public boolean createCollaborator(Collaborator collaborator){
        if(!collaboratorAlreadyExist(collaborator)) {
            collaboratorList.add(collaborator);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean collaboratorAlreadyExist(Collaborator collaborator){
        for(Collaborator c : collaboratorList) {
            if (c.equals(collaborator)) {
                return true;
            }
        }
        return false;
    }

    public Collaborator getCollaboratorByPhone(String name){
        for(Collaborator c : collaboratorList){
            if(c.getName())
        }
    }

    public int size(){
        return this.collaboratorList.size();
    }

}
