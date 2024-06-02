package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import java.util.ArrayList;

public class ShowListOfCollaboratorsUI implements Runnable {
    private final CollaboratorRepository collaboratorRepository;
    public ShowListOfCollaboratorsUI() {
        Repositories repositories = Repositories.getInstance();
        collaboratorRepository = repositories.getCollaboratorRepository();
    }
    @Override
    public void run() {
        System.out.println("\n\n----------- Existing Collaborators and Their Skills -----------");
        ArrayList<Collaborator> collaboratorsList = collaboratorRepository.getCollaboratorList();
        if (collaboratorsList.isEmpty()) {
            System.out.println("No collaborators found.");
        } else {
            for (Collaborator collaborator : collaboratorsList) {
                System.out.println(collaborator.toString());
            }
        }
    }
}
