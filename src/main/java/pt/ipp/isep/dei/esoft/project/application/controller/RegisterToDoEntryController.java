package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.ToDoEntry;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoEntryDTO;
import pt.ipp.isep.dei.esoft.project.mappers.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.mappers.ToDoEntryMapper;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ToDoEntryRepository;

import java.util.ArrayList;

/**
 * Controller class responsible for handling to-do entry registration operations.
 */
public class RegisterToDoEntryController {
    private ToDoEntryRepository toDoEntryRepository;
    private GreenSpaceRepository greenSpaceRepository;
    private ToDoEntryMapper toDoEntryMapper;
    private GreenSpaceMapper greenSpaceMapper;
    AuthenticationController authenticationController = new AuthenticationController();


    public RegisterToDoEntryController() {
        getToDoEntryRepository();
        getGreenSpaceRepository();
        toDoEntryMapper = new ToDoEntryMapper();
        greenSpaceMapper = new GreenSpaceMapper();
    }

    /**
     * Registers a to-do entry.
     *
     * @param toDoEntryDTO The to-do entry DTO.
     * @return {@code true} if the to-do entry is successfully registered, {@code false} otherwise.
     */
    public boolean registerToDoEntry(ToDoEntryDTO toDoEntryDTO) {
        return toDoEntryRepository.registerToDoEntry(toDoEntryMapper.toEntity(toDoEntryDTO));
    }

    /**
     * Retrieves the list of green spaces.
     *
     * @return The list of green space DTOs.
     */
    public ArrayList<GreenSpaceDTO> getGreenSpaceDTOsList() {
        return greenSpaceMapper.toDTOList(getGreenSpaceList());
    }

    private ArrayList<GreenSpace> getGreenSpaceList() {
        return greenSpaceRepository.getGreenSpaceList(authenticationController.getCurrentUserEmail());
    }

    /**
     * Retrieves the list of to-do entry DTOs.
     *
     * @return The list of to-do entry DTOs.
     */
    public ArrayList<ToDoEntryDTO> getToDoEntryDTOsList() {
        return toDoEntryMapper.toDTOList(getToDoEntryList());
    }

    private ArrayList<ToDoEntry> getToDoEntryList() {
        return toDoEntryRepository.getToDoEntryList(authenticationController.getCurrentUserEmail());
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
