package pt.ipp.isep.dei.esoft.project.mappers;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;

import java.util.ArrayList;
import java.util.List;

public class AgendaEntryMapper {

    public AgendaEntry toEntity(AgendaEntryDTO dto){
        return new AgendaEntry(dto.description, dto.expectedDuration, dto.greenSpace, dto.urgencyDegree, dto.entryStatus, dto.date, dto.team, dto.vehicles);
    }

    public AgendaEntryDTO toDTO(AgendaEntry agendaEntry){
        return new AgendaEntryDTO(agendaEntry.getDescription(), agendaEntry.getExpectedDuration(), agendaEntry.getGreenSpace(), agendaEntry.getUrgencyDegree(), agendaEntry.getEntryStatus(), agendaEntry.getDate(), agendaEntry.getAssociatedTeam(), agendaEntry.getAssociatedVehicles());
    }

    public ArrayList<AgendaEntryDTO> toDtoList(List<AgendaEntry> toDoEntryList){
        ArrayList<AgendaEntryDTO> agendaEntryDTOList = new ArrayList<>();
        for(AgendaEntry agendaEntry : toDoEntryList){
            agendaEntryDTOList.add(toDTO(agendaEntry));
        }
        return agendaEntryDTOList;
    }
}
