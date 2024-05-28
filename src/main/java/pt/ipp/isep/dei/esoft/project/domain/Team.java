package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.enums.TeamStatusENUM;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a team of collaborators with specific skills.
 */
public class Team implements Serializable {
    /** List of skills possessed by the team
     *
     */
    private ArrayList<Skill> skills;
    /** List of collaborators in the team
     *
     */
    private ArrayList<Collaborator> collaborators;

    private TeamStatusENUM status;

    /**
     * Constructs a team with given collaborators and skills.
     *
     * @param collaborators The list of collaborators in the team.
     * @param skills        The list of skills possessed by the team.
     */
    public Team(ArrayList<Collaborator> collaborators, ArrayList<Skill> skills, TeamStatusENUM team_status){
        this.collaborators = collaborators;
        this.skills = skills;
        this.status = TeamStatusENUM.PENDING;
    }

    /**
     * Retrieves the list of skills possessed by the team.
     *
     * @return The list of skills.
     */
    public ArrayList<Skill> getSkills() {
        return skills;
    }

    /**
     * Sets the list of skills possessed by the team.
     *
     * @param skills The list of skills to set.
     */
    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    /**
     * Retrieves the list of collaborators in the team.
     *
     * @return The list of collaborators.
     */
    public ArrayList<Collaborator> getCollaborators(){
        return collaborators;
    }

    /**
     * Sets the list of collaborators in the team.
     *
     * @param collaborators The list of collaborators to set.
     */
    public void setCollaborators(ArrayList<Collaborator> collaborators){
        this.collaborators = collaborators;
    }

    public TeamStatusENUM getStatus() {
        return status;
    }

    public void setStatus(TeamStatusENUM status) {
        this.status = status;
    }

    /**
     * Generates a string representation of the Team object.
     * @return A string containing information about the team, including its collaborators and skills.
     */
    @Override
    public String toString() {
        String collaboratorsString = "";
        for (Collaborator collaborator : collaborators) {
            collaboratorsString += collaborator.getName();
        }

        return collaboratorsString;
    }
    public int hashCode() {
        return Objects.hash(collaborators,skills);
    }

}
