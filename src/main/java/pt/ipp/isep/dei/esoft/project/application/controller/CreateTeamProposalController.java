package pt.ipp.isep.dei.esoft.project.application.controller;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.domain.service.CreateTeamProposalService;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Controller class for creating team proposals.
 */
public class CreateTeamProposalController {
    private SkillRepository skillRepository;
    private TeamRepository teamRepository;
    private CreateTeamProposalService teamProposalService;

    public CreateTeamProposalController() {
        this.skillRepository = Repositories.getInstance().getSkillRepository();
        this.teamRepository = Repositories.getInstance().getTeamRepository();
        this.teamProposalService = new CreateTeamProposalService();
    }

    public ArrayList<Skill> getSkillsList() {
        return skillRepository.getSkillList();
    }

    public Team createTeamProposal(int max, int min, ArrayList<Skill> skills) {
        var collaborators = teamProposalService.arrangeCollaborattorsBySkill(skills);

        Team team = new Team(teamProposalService.arrangeTeam(max, min, skills, collaborators), skills);
        if(teamRepository.registerTeam(team)){
            for (var c : team.getCollaborators()) {
                c.activateCollaborator();
            }

            return team;
        } else {
            throw new InputMismatchException("Could not create team");
        }
    }
}

