package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.ToDoEntry;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ToDoEntryRepository;

public class RegisterToDoEntryController {
    private ToDoEntryRepository toDoEntryRepository;
    private GreenSpaceRepository greenSpaceRepository;

    public RegisterToDoEntryController() {
        getToDoEntryRepository();
        getGreenSpaceRepository();
    }

    private ToDoEntryRepository getToDoEntryRepository() {
        if (toDoEntryRepository == null) {
            Repositories repositories = Repositories.getInstance();
            toDoEntryRepository = repositories.getToDoEntryRepository();
        }
        return toDoEntryRepository;
    }

    private GreenSpaceRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpaceRepository = repositories.getGreenSpaceRepository();
        }
        return greenSpaceRepository;
    }
    
}
