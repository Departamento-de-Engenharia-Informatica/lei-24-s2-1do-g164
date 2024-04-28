package pt.ipp.isep.dei.esoft.project.domain.service;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;

public class CreateTeamProposalService {
    private CollaboratorRepository collaboratorRepository;

    public CreateTeamProposalService() {
        this.collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
    }

    public ArrayList<Collaborator> arrangeCollaboratorsBySkill(ArrayList<Skill> skills){
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
                return Integer.compare(c2.getSkills().size(), c1.getSkills().size());
            }
        });

        return collaborators;
    }

    public ArrayList<Collaborator> arrangeTeam(int max, int min, ArrayList<Skill> skills, ArrayList<Collaborator> collaborators){
        var teamMembers = new ArrayList<Collaborator>();
        var skillsCopy = (ArrayList) skills.clone();
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

        if (teamMembers.size() < min){
            for (var c : collaborators){
                if (teamMembers.size() < max && !teamMembers.contains(c)){
                    for (var s : c.getSkills()){
                        if (skills.contains(s)){
                            if (!teamMembers.contains(c)) {
                                teamMembers.add(c);
                            }
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

        return teamMembers;
    }
}
