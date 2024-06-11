package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaEntryRepository;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Controller class responsible for managing the cancellation of agenda entries.
 */
public class CancelAgendaEntryController {

    private AgendaEntryRepository agendaEntryRepository;
    private AgendaEntryMapper agendaEntryMapper;
    AuthenticationController authenticationController = new AuthenticationController();


    public CancelAgendaEntryController() {
        this.agendaEntryRepository = Repositories.getInstance().getAgendaEntryRepository();
        this.agendaEntryMapper = new AgendaEntryMapper();

    }

    /**
     * Retrieves the list of agenda entries without cancelled entries.
     *
     * @return The list of agenda entries without cancelled entries.
     */
    public ArrayList<AgendaEntryDTO> getAgendaEntryWithoutCancelledDTOList() {
        ArrayList<AgendaEntry> agendaEntryListGSM = agendaEntryRepository.getAgendaEntryListWithoutCancelled(authenticationController.getCurrentUserEmail());
        return agendaEntryMapper.toDtoList(agendaEntryListGSM);
    }

    /**
     * Cancels an agenda entry.
     *
     * @param dto The agenda entry to cancel.
     * @return True if the agenda entry was successfully cancelled, false otherwise.
     */
    public boolean cancelAgendaEntry(AgendaEntryDTO dto) {
        if (dto.entryStatus == EntryStatusENUM.CANCELLED) {
            return false;
        }
        var entry = agendaEntryRepository.getAgendaEntryByDescriptionAndGreenspace(dto.description, dto.greenSpace);
        if (entry == null) {
            throw new InputMismatchException("Agenda Entry not found!");
        }

        return agendaEntryRepository.updateStatus(entry, EntryStatusENUM.CANCELLED);
    }
}
