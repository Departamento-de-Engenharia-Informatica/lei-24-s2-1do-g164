package pt.ipp.isep.dei.esoft.project.application.controller;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.ui.console.CreateTeamProposalUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;



/**
 * Controller class for creating team proposals.
 */
public class CreateteamProposalController {
    private SkillRepository skillRepository;
    private TeamRepository teamRepository;
    private CollaboratorRepository collaboratorRepository;



    public CreateteamProposalController() {
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

