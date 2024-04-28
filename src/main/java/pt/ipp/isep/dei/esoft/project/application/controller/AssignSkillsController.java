package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;

public class AssignSkillsController {
    private SkillRepository skillRepository;
    private CollaboratorRepository collaboratorRepository;

    public AssignSkillsController() {
        getCollaboratorRepository();
        getSkillRepository();
    }


    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();
            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }

    public ArrayList<Collaborator> getCollaboratorList() {
        return collaboratorRepository.getCollaboratorList();
    }

    public ArrayList<Skill> getSkillsList() {
        return skillRepository.getSkillList();
    }

    public boolean assignSkills(Collaborator collaborator, ArrayList<Skill> skillsList) {

        return collaboratorRepository.assignSkills(collaborator,skillsList);
    }
}




