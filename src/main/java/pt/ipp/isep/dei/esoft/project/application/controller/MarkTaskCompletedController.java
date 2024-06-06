package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.ToDoEntry;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.AgendaEntryRepository;
import pt.ipp.isep.dei.esoft.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.esoft.project.repository.ToDoEntryRepository;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Controller class responsible for marking tasks as completed.
 */
public class MarkTaskCompletedController {
    private AgendaEntryRepository agendaEntryRepository;
    private AuthenticationController authenticationController;
    private AgendaEntryMapper agendaEntryMapper;
    private ToDoEntryRepository toDoEntryRepository;


    public MarkTaskCompletedController() {
        this.agendaEntryRepository = getAgendaEntryRepository();
        this.authenticationController = new AuthenticationController();
        this.agendaEntryMapper = new AgendaEntryMapper();
        this.toDoEntryRepository = getToDoEntryRepository();
    }

    private AgendaEntryRepository getAgendaEntryRepository() {
        if (agendaEntryRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agendaEntryRepository = repositories.getAgendaEntryRepository();
        }
        return agendaEntryRepository;
    }

    private ToDoEntryRepository getToDoEntryRepository() {
        if (toDoEntryRepository == null) {
            Repositories repositories = Repositories.getInstance();
            toDoEntryRepository = repositories.getToDoEntryRepository();
        }
        return toDoEntryRepository;
    }

    /**
     * Retrieves a list of agenda entries in DTO format.
     *
     * @return ArrayList of AgendaEntryDTO representing agenda entries.
     */
    public ArrayList<AgendaEntryDTO> getAgendaEntryWithoutDoneDTOList() {
        String currentUserEmail = authenticationController.getCurrentUserEmail();
        ArrayList<AgendaEntry> agendaEntryList = agendaEntryRepository.getEntriesByCollaborator(currentUserEmail);
        return agendaEntryMapper.toDtoList(agendaEntryList);
    }

    /**
     * Marks an agenda entry as completed.
     *
     * @param dto The agenda entry to mark as completed.
     * @return True if the agenda entry was marked as completed successfully, false otherwise.
     */
    public boolean completedAgendaEntry(AgendaEntryDTO dto) {
        if (dto.entryStatus == EntryStatusENUM.DONE) {
            return false;
        }
        var entry = agendaEntryRepository.getAgendaEntryByDescriptionAndGreenspace(dto.description, dto.greenSpace);
        if (entry == null) {
            throw new InputMismatchException("Agenda Entry not found!");
        }
        boolean success = agendaEntryRepository.updateStatus(entry, EntryStatusENUM.DONE);
        if(success){
            toDoEntryRepository.updateStatus((ToDoEntry) entry, EntryStatusENUM.PENDING);
        }
        return success;
    }

    public ArrayList<AgendaEntryDTO> getAgendaEntryWithoutCancelledDTOList() {
        ArrayList<AgendaEntry> agendaEntryListGSM = agendaEntryRepository.getAgendaEntryListWithoutCancelled(authenticationController.getCurrentUserEmail());
        System.out.println("lista sem cancelados: " + agendaEntryListGSM);
        return agendaEntryMapper.toDtoList(agendaEntryListGSM);
    }

}
