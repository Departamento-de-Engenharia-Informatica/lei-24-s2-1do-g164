package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.dto.AgendaDescriptionAndGreenspaceDTO;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.mappers.AgendaDescriptionAndGreenspaceMapper;
import pt.ipp.isep.dei.esoft.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaEntryRepository;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatus;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.InputMismatchException;

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

    public boolean cancelAgendaEntry(AgendaDescriptionAndGreenspaceDTO dto) {
        if (dto.status == EntryStatus.CANCELLED) {
            return false;
        }

        var entry = agendaEntryRepository.getAgendaEntryByDescriptionAndGreenspace(dto.description, dto.greenSpace);
        if (entry == null){
            throw new InputMismatchException("Agenda Entry not found!");
    }
        return agendaEntryRepository.updateStatus(entry, EntryStatus.CANCELLED);
    }
//    public void ui(AgendaEntryDTO agendaDTO) {
//        AgendaDescriptionAndGreenspaceMapper mapper = new AgendaDescriptionAndGreenspaceMapper();
//        var agendaDescriptionAndGreendspaceDTO = mapper.toDTO(agendaDTO.description, agendaDTO.greenSpace, agendaDTO.entryStatus);
//        cancelAgendaEntry(agendaDescriptionAndGreendspaceDTO);
//    }
}

