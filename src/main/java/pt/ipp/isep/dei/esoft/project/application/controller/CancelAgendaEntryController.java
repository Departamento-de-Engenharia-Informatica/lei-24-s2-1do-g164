package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaEntryRepository;
import pt.ipp.isep.dei.esoft.project.repository.ENUM.EntryStatus;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;

public class CancelAgendaEntryController {

    private AgendaEntryRepository agendaEntryRepository;
    private AgendaEntryMapper agendaEntryMapper;

    public CancelAgendaEntryController(){
        this.agendaEntryRepository = Repositories.getInstance().getAgendaEntryRepository();
        this.agendaEntryMapper= new AgendaEntryMapper();
    }

    public ArrayList<AgendaEntryDTO> getAgendaEntryDTOList() {
        ArrayList<AgendaEntry> agendaEntryList = agendaEntryRepository.getAgendaEntryList();
        return agendaEntryMapper.toDtoList(agendaEntryList);
    }

    public boolean cancelAgendaEntry(AgendaEntryDTO agendaEntryDTO) {
        AgendaEntry agendaEntry = agendaEntryMapper.toEntity(agendaEntryDTO);
        if (agendaEntry.getEntryStatus() == EntryStatus.CANCELLED) {
            return false;
        }
        agendaEntry.cancelEntry();
        agendaEntryRepository.update(agendaEntry);
        return true;
    }
}
