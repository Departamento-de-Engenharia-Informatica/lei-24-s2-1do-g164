package pt.ipp.isep.dei.esoft.project.application.controller;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;


/**
 * Controller class for creating team proposals.
 */
public class CreateTeamProposalController {
    private SkillRepository skillRepository;
    private TeamRepository teamRepository;
    private CollaboratorRepository collaboratorRepository;



    public CreateTeamProposalController() {
        this.skillRepository = Repositories.getInstance().getSkillRepository();
        this.collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        this.teamRepository = Repositories.getInstance().getTeamRepository();

    }

    public ArrayList<Skill> getSkillsList() {
        return skillRepository.getSkillList();
    }

    public Team createTeamProposal(int max, int min, ArrayList<Skill> skills) {
       return teamRepository.createTeamProposal(max, min, skills);
    }
}

