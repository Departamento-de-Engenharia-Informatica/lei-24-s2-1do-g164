package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaEntryRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Controller class responsible for postponing agenda entries.
 */
public class PostponeAgendaEntryController {

    private AgendaEntryRepository agendaEntryRepository;
    private AgendaEntryMapper agendaEntryMapper;
    AuthenticationController authenticationController = new AuthenticationController();

    public PostponeAgendaEntryController() {
        getAgendaEntryRepository();
        agendaEntryMapper = new AgendaEntryMapper();
    }

    /**
     * Postpones an agenda entry to the specified date.
     *
     * @param dto  The agenda entry to postpone.
     * @param date The date to which the agenda entry will be postponed.
     * @return True if the agenda entry was postponed successfully, false otherwise.
     */
    public boolean postponeAgendaEntry(AgendaEntryDTO dto, LocalDate date) {
        if (dto.entryStatus == EntryStatusENUM.CANCELLED) {
            return false;
        }
        var entry = agendaEntryRepository.getAgendaEntryByDescriptionAndGreenspace(dto.description, dto.greenSpace);
        if (entry == null) {
            throw new InputMismatchException("Agenda Entry not found!");
        }

        agendaEntryRepository.updateDate(entry, date);
        return agendaEntryRepository.updateStatus(entry, EntryStatusENUM.POSTPONED);
    }

    private ArrayList<AgendaEntry> getAvailableAgendaEntryList() {
        return agendaEntryRepository.getAgendaEntryWithoutDoneList(authenticationController.getCurrentUserEmail());
    }

    /**
     * Retrieves a list of agenda entries in DTO format.
     *
     * @return ArrayList of AgendaEntryDTO representing agenda entries.
     */
    public ArrayList<AgendaEntryDTO> getAgendaEntryDTOsList() {
        return agendaEntryMapper.toDtoList(getAvailableAgendaEntryList());
    }

    private void getAgendaEntryRepository() {
        if (agendaEntryRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agendaEntryRepository = repositories.getAgendaEntryRepository();
        }
    }
}
