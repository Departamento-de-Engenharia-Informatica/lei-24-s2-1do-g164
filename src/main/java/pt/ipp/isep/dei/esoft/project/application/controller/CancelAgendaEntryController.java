package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaEntryRepository;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class CancelAgendaEntryController {

    private AgendaEntryRepository agendaEntryRepository;
    private AgendaEntryMapper agendaEntryMapper;
    AuthenticationController authenticationController = new AuthenticationController();


    public CancelAgendaEntryController() {
        this.agendaEntryRepository = Repositories.getInstance().getAgendaEntryRepository();
        this.agendaEntryMapper = new AgendaEntryMapper();

    }

    private AbstractList<AgendaEntry> getAgendaEntryList(){
        return agendaEntryRepository.getAgendaEntryList(authenticationController.getCurrentUserEmail());
    }

    private AbstractList<AgendaEntry> getAgendaEntryListByStatus(EntryStatusENUM status){
        return agendaEntryRepository.getAgendaEntryListByStatus(authenticationController.getCurrentUserEmail(), status);
    }

    public ArrayList<AgendaEntryDTO> getAgendaEntryDTOList() {
        return agendaEntryMapper.toDtoList(getAgendaEntryList());
    }

    public ArrayList<AgendaEntryDTO> getAgendaEntryDTOListByStatus(EntryStatusENUM status){
        return agendaEntryMapper.toDtoList(getAgendaEntryListByStatus(status));
    }


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

