package pt.ipp.isep.dei.esoft.project.domain.service;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;
import pt.ipp.isep.dei.esoft.project.repository.enums.TeamStatusENUM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;


/**
 * Service class for creating team proposals.
 */

public class CreateTeamProposalService {
    private CollaboratorRepository collaboratorRepository;
    private TeamRepository teamRepository;

    /**
     * Constructor for CreateTeamProposalService.
     * Initializes collaboratorRepository using Repositories singleton.
     */
    public CreateTeamProposalService() {
        this.collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        this.teamRepository = Repositories.getInstance().getTeamRepository();
    }

    /**
     * Accepts a team proposal by setting its status to 'ACCEPTED'.
     *
     * @param team The team to accept.
     */

    public void acceptTeamProposal(Team team) {
        team.setStatus(TeamStatusENUM.ACCEPTED);
    }

    /**
     * Refuses a team proposal by setting its status to 'REFUSED'.
     *
     * @param team The team to refuse.
     */
    public void refuseTeamProposal(Team team) {
        team.setStatus(TeamStatusENUM.REFUSED);
    }

    /**
     * Arranges collaborators by skill, sorting them based on the number of matching skills.
     *
     * @param skills The list of skills to match collaborators against.
     * @return An ArrayList of Collaborator objects arranged by skill match.
     * @throws InputMismatchException if no collaborators are found for any skill.
     */
    public ArrayList<Collaborator> arrangeCollaboratorsBySkill(ArrayList<Skill> skills){
        var collaborators = new ArrayList<Collaborator>();

        for (var s : skills) {
            var collaboratorsBySkill = collaboratorRepository.getDeactivatedCollaboratorsBySkill(skills);

            if (collaboratorsBySkill.isEmpty()){
                String errorMessage = "No collaborators found for skill " +
                        s.getSkillName();
                throw new InputMismatchException(errorMessage);
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

    /**
     * Arranges team members based on specified criteria.
     *
     * @param max           The maximum number of team members.
     * @param min           The minimum number of team members.
     * @param skills        The list of required skills.
     * @param collaborators The list of available collaborators.
     * @return An ArrayList of Collaborator objects representing the arranged team.
     * @throws InputMismatchException if a team cannot be formed meeting the minimum requirements.
     */
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
            errorMessage.append("Could not form a team with a minimum of ").append(min);

            throw new InputMismatchException(errorMessage.toString());
        }

        return teamMembers;
    }
}
