package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;

import java.util.List;

/**
 * The type Team dto.
 */
public class TeamDTO {

  private List<String> collaboratorNames;

    /**
     * Instantiates a new Team dto.
     *
     * @param collaborators the collaborators
     */
    public TeamDTO(List<String> collaborators) {
        this.collaboratorNames = collaborators;

    }

    /**
     * Gets collaborators.
     *
     * @return the collaborators
     */
    public ArrayList<String> getCollaborators() {
            return (ArrayList<String>) collaboratorNames;
        }

    /**
     * Sets collaborators.
     *
     * @param collaborators the collaborators
     */
    public void setCollaborators(List<String> collaborators) {
            this.collaboratorNames = collaborators;
        }

    @Override
    public String toString() {
        return collaboratorNames.toString().replace("[", "").replace("]", "");
    }
}





