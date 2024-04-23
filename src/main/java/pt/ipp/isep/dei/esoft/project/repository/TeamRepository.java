package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;


public class TeamRepository implements Serializable {

    ArrayList<Team> teams = new ArrayList<>();
    private CollaboratorRepository collaboratorRepository;
    private TeamRepository teamRepository;
    private SkillRepository skillRepository;

    public Team createTeamProposal(int max, int min, ArrayList<Skill> skills){

        collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        var collaborators = new ArrayList<Collaborator>();

        for (var s : skills) {
            var collaboratorsBySkill = collaboratorRepository.getDeactivatedCollaboratorsBySkill(skills);

            if (collaboratorsBySkill.size() == 0){
                var errorMessage = new StringBuilder();
                errorMessage.append("No collaborators found for skill ");
                errorMessage.append(s.getSkillName());

                throw new InputMismatchException(errorMessage.toString());
            }

            for (var c : collaboratorsBySkill){
                if (!collaborators.contains(c))
                    collaborators.add(c);
            }
        }

        Collections.sort(collaborators, new Comparator<Collaborator>() {
            @Override
            public int compare(Collaborator c1, Collaborator c2) {
                return Integer.compare(c1.getSkills().size(), c2.getSkills().size());
            }
        });

        var teamMembers = new ArrayList<Collaborator>();
        var skillsCopy = skills;


        for(var c : collaborators){
            if (teamMembers.size() < max){
                for(var s : c.getSkills()){
                    if(skillsCopy.contains(s)){
                        skillsCopy.remove(s);
                        if(!teamMembers.contains(c)) {
                            teamMembers.add(c);
                        }
                    }
                }
            }
        }

        for (var c : collaborators){
            if (teamMembers.size() < min && teamMembers.size() < max){
                for(var s : c.getSkills()){
                    if(skills.contains(s)){
                        if(!teamMembers.contains(c)) {
                            teamMembers.add(c);
                        }
                    }
                }
            }
        }

        if (teamMembers.size() < min) {
            var errorMessage = new StringBuilder();
            errorMessage.append("Could not form a team with a minimum of ");
            errorMessage.append(min);

            throw new InputMismatchException(errorMessage.toString());
        }

        // Create team proposal and register it
        Team team = new Team(teamMembers, skills);

        if(teamRepository.registerTeam(team)){
            // Activate collaborators in the team
            for (var c : teamMembers) {
                c.activateCollaborator();
            }

            return team;
        } else {
            throw new InputMismatchException("Could not create team");
        }
    }

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
}