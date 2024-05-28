package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;

import java.util.List;

public class TeamDTO {

  private List<String> collaboratorNames;
  private Team team;

    public TeamDTO(List<String> collaborators) {
        this.collaboratorNames = collaborators;

    }
        public List<String> getCollaborators() {
            return collaboratorNames;
        }

        public void setCollaborators(List<String> collaborators) {
            this.collaboratorNames = collaborators;
        }

    @Override
    public String toString() {
        return collaboratorNames.toString().replace("[", "").replace("]", "");
    }
}





