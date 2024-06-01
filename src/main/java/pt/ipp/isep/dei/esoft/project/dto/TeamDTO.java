package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;

import java.util.List;

public class TeamDTO {

  private List<String> collaboratorNames;

    public TeamDTO(List<String> collaborators) {
        this.collaboratorNames = collaborators;

    }
        public ArrayList<String> getCollaborators() {
            return (ArrayList<String>) collaboratorNames;
        }

        public void setCollaborators(List<String> collaborators) {
            this.collaboratorNames = collaborators;
        }

    @Override
    public String toString() {
        return collaboratorNames.toString().replace("[", "").replace("]", "");
    }
}





