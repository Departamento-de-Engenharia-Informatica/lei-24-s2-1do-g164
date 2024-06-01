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

public class PostponeAgendaEntryController {

    private AgendaEntryRepository agendaEntryRepository;
    private AgendaEntryMapper agendaEntryMapper;
    AuthenticationController authenticationController = new AuthenticationController();

    public PostponeAgendaEntryController(){
        getAgendaEntryRepository();
        agendaEntryMapper = new AgendaEntryMapper();
    }

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

    private ArrayList<AgendaEntry> getAvailableAgendaEntryList(){
        return agendaEntryRepository.getAgendaEntryList(authenticationController.getCurrentUserEmail());
    }

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
