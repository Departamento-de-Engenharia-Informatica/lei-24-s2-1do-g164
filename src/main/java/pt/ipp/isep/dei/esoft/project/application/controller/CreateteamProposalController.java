package pt.ipp.isep.dei.esoft.project.application.controller;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;

public class CreateteamProposalController {
    private SkillRepository skillRepository;
    private TeamRepository teamRepository;
    private CollaboratorRepository collaboratorRepository;


    public CreateteamProposalController(){
        skillRepository = Repositories.getInstance().getSkillRepository();
        collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        teamRepository = Repositories.getInstance().getTeamRepository();

    }

    public Team createTeamProposal(int max, int min, ArrayList<Skill> skills){
        // arraylist containing collaborators with at least one of the required skills
        var collaborators = new ArrayList<Collaborator>();
        for (var s : skills) {
            var collaboratorsBySkill = this.collaboratorRepository.getDeactivatedCollaboratorsBySkill(s);

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

        Team team = new Team(teamMembers, skills);
        if(teamRepository.registerTeam(team)){
            for (var c : teamMembers) {
                c.activateCollaborator();
            }

            return team;
        } else {
            throw new InputMismatchException("Could not create team");
        }
    }
}
