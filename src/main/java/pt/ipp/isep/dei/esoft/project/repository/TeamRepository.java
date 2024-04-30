package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.io.Serializable;
import java.util.ArrayList;

public class TeamRepository implements Serializable {
    ArrayList<Team> teams = new ArrayList<>();



    public boolean registerTeam(Team team){
        if (!teamAlreadyExists(team)){
            teams.add(team);
            return true;
        } else {
            return false;
        }
    }

    private boolean teamAlreadyExists(Team team) {
        for (var t : this.teams){
            if (t.getCollaborators().containsAll(team.getCollaborators()) &&
                    t.getSkills().containsAll(team.getSkills())) {
                return true;
            }
        }
        return false;
    }
    public int size() {
        return this.teams.size();
    }
}