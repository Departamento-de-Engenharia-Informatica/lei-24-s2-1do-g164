package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.ToDoEntry;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoEntryDTO;
import pt.ipp.isep.dei.esoft.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.esoft.project.mappers.ToDoEntryMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaEntryRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ToDoEntryRepository;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;

import java.util.ArrayList;

/**
 * Controller class responsible for registering agenda entries and managing to-do entries.
 */
public class RegisterAgendaEntryController {

    private AgendaEntryRepository agendaEntryRepository;
    private ToDoEntryRepository toDoEntryRepository;
    private final AgendaEntryMapper agendaEntryMapper;
    private final ToDoEntryMapper toDoEntryMapper;
    AuthenticationController authenticationController = new AuthenticationController();


    public RegisterAgendaEntryController() {
        getAgendaEntryRepository();
        getToDoEntryRepository();
        toDoEntryMapper = new ToDoEntryMapper();
        agendaEntryMapper = new AgendaEntryMapper();
    }

    /**
     * Registers an agenda entry.
     *
     * @param agendaEntryDTO The agenda entry to register.
     * @return True if the agenda entry was registered successfully, false otherwise.
     */
    public boolean registerAgendaEntry(AgendaEntryDTO agendaEntryDTO) {
        return agendaEntryRepository.addEntryToAgenda(agendaEntryMapper.toEntity(agendaEntryDTO));
    }

    /**
     * Updates a to-do entry to 'PLANNED' status.
     *
     * @param toDoEntryDTO The to-do entry to update.
     * @return True if the to-do entry was updated successfully, false otherwise.
     */
    public boolean updateToDoEntry(ToDoEntryDTO toDoEntryDTO) {
        return toDoEntryRepository.updateStatus(toDoEntryMapper.toEntity(toDoEntryDTO), EntryStatusENUM.PLANNED);
    }

    /**
     * Retrieves a list of agenda entries in DTO format.
     *
     * @return ArrayList of AgendaEntryDTO representing agenda entries.
     */
    public ArrayList<AgendaEntryDTO> getAgendaEntryListDTO() {
        var entries = agendaEntryRepository.getAgendaEntryWithoutDoneList(authenticationController.getCurrentUserEmail());
        return agendaEntryMapper.toDtoList(entries);
    }

    /**
     * Retrieves a list of to-do entries in DTO format.
     *
     * @return ArrayList of ToDoEntryDTO representing to-do entries.
     */
    public ArrayList<ToDoEntryDTO> getToDoEntryDTOsList() {
        return toDoEntryMapper.toDTOList(getToDoEntryList());
    }

    private ArrayList<ToDoEntry> getToDoEntryList() {
        return toDoEntryRepository.getToDoEntryListByStatus(authenticationController.getCurrentUserEmail(), EntryStatusENUM.PLANNED);
    }

    private void getToDoEntryRepository() {
        if (toDoEntryRepository == null) {
            Repositories repositories = Repositories.getInstance();
            toDoEntryRepository = repositories.getToDoEntryRepository();
        }
    }

    private void getAgendaEntryRepository() {
        if (agendaEntryRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agendaEntryRepository = repositories.getAgendaEntryRepository();
        }
    }
}
