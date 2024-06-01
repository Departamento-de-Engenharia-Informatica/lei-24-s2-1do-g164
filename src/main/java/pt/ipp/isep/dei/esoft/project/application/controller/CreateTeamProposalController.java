package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.domain.service.CreateTeamProposalService;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.repository.enums.TeamStatusENUM;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Controller class for managing the creation and handling of team proposals.
 */
public class CreateTeamProposalController {
    private SkillRepository skillRepository;
    private TeamRepository teamRepository;
    private CreateTeamProposalService teamProposalService;


    /**
     * Constructs a CreateTeamProposalController object with initialized repositories and services.
     */
    public CreateTeamProposalController() {
        this.skillRepository = Repositories.getInstance().getSkillRepository();
        this.teamRepository = Repositories.getInstance().getTeamRepository();
        this.teamProposalService = new CreateTeamProposalService();
    }

    /**
     * Retrieves a list of skills available in the system.
     *
     * @return An ArrayList of skills available in the system.
     */
    public ArrayList<Skill> getSkillsList() {
        return skillRepository.getSkillList();
    }


    /**
     * Accepts a team proposal by setting its status to 'ACCEPTED'.
     *
     * @param team The team to accept.
     * @return True if the team proposal was accepted successfully, false otherwise.
     */
    public boolean acceptTeamProposal(Team team) {
        try {
            teamProposalService.acceptTeamProposal(team);
            teamRepository.registerTeam(team);
            for (var collaborator : team.getCollaborators()) {
                collaborator.activateCollaborator();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Refuses a team proposal by setting its status to 'REFUSED'.
     *
     * @param team The team to refuse.
     * @return True if the team proposal was refused successfully, false otherwise.
     */
    public boolean refuseTeamProposal(Team team) {
        try {
            teamProposalService.refuseTeamProposal(team);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Creates a team proposal based on the specified requirements and skills.
     *
     * @param max    The maximum number of collaborators allowed in the team.
     * @param min    The minimum number of collaborators required in the team.
     * @param skills The list of skills required for the team.
     * @return The created team proposal.
     * @throws InputMismatchException If the team proposal could not be created.
     */
    public Team createTeamProposal(int max, int min, ArrayList<Skill> skills) {
        var collaborators = teamProposalService.arrangeCollaboratorsBySkill(skills);
        Team team = new Team(teamProposalService.arrangeTeam(max, min, skills, collaborators), skills, TeamStatusENUM.PENDING);
        return team;
    }
}
