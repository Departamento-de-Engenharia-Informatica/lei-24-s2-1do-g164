package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;

/**
 * Controller class responsible for managing the assignment of skills to collaborators.
 */
public class AssignSkillsController {
    private SkillRepository skillRepository;
    private CollaboratorRepository collaboratorRepository;

    /**
     * Constructor for AssignSkillsController.
     */
    public AssignSkillsController() {
        getCollaboratorRepository();
        getSkillRepository();
    }

    /**
     * Retrieves the collaborator repository.
     *
     * @return The collaborator repository.
     */
    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    /**
     * Retrieves the skill repository.
     *
     * @return The skill repository.
     */
    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();
            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }

    /**
     * Gets the list of collaborators.
     *
     * @return The list of collaborators.
     */
    public ArrayList<Collaborator> getCollaboratorList() {
        return collaboratorRepository.getCollaboratorList();
    }

    /**
     * Gets the list of skills.
     *
     * @return The list of skills.
     */
    public ArrayList<Skill> getSkillsList() {
        return skillRepository.getSkillList();
    }

    /**
     * Assigns skills to a collaborator.
     *
     * @param collaborator The collaborator to whom skills will be assigned.
     * @param skillsList   The list of skills to be assigned.
     * @return True if the skills were assigned successfully, false otherwise.
     */
    public boolean assignSkills(Collaborator collaborator, ArrayList<Skill> skillsList) {
        return collaboratorRepository.assignSkills(collaborator, skillsList);
    }
}
