package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.TeamStatus;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a team of collaborators with specific skills.
 */
public class Team {
    /** List of skills possessed by the team
     *
     */
    private ArrayList<Skill> skills;
    /** List of collaborators in the team
     *
     */
    private ArrayList<Collaborator> collaborators;

    private TeamStatus status;

    /**
     * Constructs a team with given collaborators and skills.
     *
     * @param collaborators The list of collaborators in the team.
     * @param skills        The list of skills possessed by the team.
     */
    public Team(ArrayList<Collaborator> collaborators, ArrayList<Skill> skills, TeamStatus team_status){
        this.collaborators = collaborators;
        this.skills = skills;
        this.status = TeamStatus.PENDING;
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

    public TeamStatus getStatus() {
        return status;
    }

    public void setStatus(TeamStatus status) {
        this.status = status;
    }

    /**
     * Generates a string representation of the Team object.
     * @return A string containing information about the team, including its collaborators and skills.
     */
    @Override
    public String toString() {
        return "Team: " + collaborators;
    }
    public int hashCode() {
        return Objects.hash(collaborators,skills);
    }

}
