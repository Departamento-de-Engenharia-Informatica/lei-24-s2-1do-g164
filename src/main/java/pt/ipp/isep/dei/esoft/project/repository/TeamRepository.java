package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Repository class for managing teams.
 */

public class TeamRepository implements Serializable {


    /**
     * ArrayList to store the teams
     */

    ArrayList<Team> teams = new ArrayList<>();


    /**
     * Registers a new team if it doesn't already exist.
     *
     * @param team The team to be registered.
     * @return True if the team is successfully registered, false otherwise.
     */
    public boolean registerTeam(Team team){
        if (!teamAlreadyExists(team)){
            teams.add(team);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if a team with the same collaborators and skills already exists.
     *
     * @param team The team to be checked for existence.
     * @return True if a similar team already exists, false otherwise.
     */

    private boolean teamAlreadyExists(Team team) {
        for (var t : this.teams){
            if (t.getCollaborators().containsAll(team.getCollaborators()) &&
                    t.getSkills().containsAll(team.getSkills())) {
                return true;
            }
        }
        return false;
    }
}