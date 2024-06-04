package pt.ipp.isep.dei.esoft.project.domain;

import com.kitfox.svg.A;
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
     * @param team_status   the team status
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
        return this.collaborators;
    }

    /**
     * Get collaborators names array list.
     *
     * @return the array list
     */
    public ArrayList<String> getCollaboratorsNames(){
        var collaboratorsNames = new ArrayList<String>();
        for (Collaborator c : this.collaborators){
            collaboratorsNames.add(c.getName());
        }
        return collaboratorsNames;
    }

    /**
     * Sets the list of collaborators in the team.
     *
     * @param collaborators The list of collaborators to set.
     */
    public void setCollaborators(ArrayList<Collaborator> collaborators){
        this.collaborators = collaborators;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public TeamStatusENUM getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(TeamStatusENUM status) {
        this.status = status;
    }

    /**
     * Generates a string representation of the Team object.
     * @return A string containing information about the team, including its collaborators and skills.
     */
    @Override
    public String toString() {
        if(this.collaborators.size() == 0){
            return "No team";
        }
            return "Team: " + collaborators;
        }

    public int hashCode() {
        return Objects.hash(collaborators,skills);
    }

}
