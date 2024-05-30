package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Repository class for managing teams.
 */
public class TeamRepository implements Serializable {
    private ArrayList<Team> teams = new ArrayList<>();



    /**
     * Registers a team in the repository.
     *
     * @param team The team to register.
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

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public Team getTeam(ArrayList<String> collaborators) {
        for (Team team: teams){
            if (team.getCollaboratorsNames().equals(collaborators))
                return team;
        }
        return null;
    }

    /**
     * Checks if a team already exists in the repository.
     *
     * @param team The team to check.
     * @return True if the team already exists, false otherwise.
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

    /**
     * Gets the number of teams in the repository.
     *
     * @return The number of teams.
     */
    public int size() {
        return this.teams.size();
    }
}
