package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.ToDoEntry;
import pt.ipp.isep.dei.esoft.project.dto.ToDoEntryDTO;
import pt.ipp.isep.dei.esoft.project.mappers.ToDoEntryMapper;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ToDoEntryRepository;

import java.util.ArrayList;

public class RegisterToDoEntryController {
    private ToDoEntryRepository toDoEntryRepository;
    private GreenSpaceRepository greenSpaceRepository;
    private ToDoEntryMapper toDoEntryMapper;


    public RegisterToDoEntryController() {
        getToDoEntryRepository();
        getGreenSpaceRepository();
        toDoEntryMapper = new ToDoEntryMapper();
    }

    public boolean registerToDoEntry(ToDoEntryDTO toDoEntryDTO) {
        return toDoEntryRepository.registerToDoEntry(toDoEntryMapper.toEntity(toDoEntryDTO));
    }

    private ArrayList<ToDoEntry> getToDoEntryList(){
        return toDoEntryRepository.getToDoEntryList();
    }

    public ArrayList<ToDoEntryDTO> getToDoEntryDTOsList() {
        return toDoEntryMapper.toDtoList(getToDoEntryList());
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
