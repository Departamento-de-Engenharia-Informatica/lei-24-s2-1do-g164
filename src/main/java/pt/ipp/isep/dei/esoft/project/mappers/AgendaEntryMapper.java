package pt.ipp.isep.dei.esoft.project.mappers;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.ToDoEntry;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Agenda entry mapper.
 */
public class AgendaEntryMapper {

    /**
     * To entity agenda entry.
     *
     * @param dto the dto
     * @return the agenda entry
     */
    public AgendaEntry toEntity(AgendaEntryDTO dto){
        return new AgendaEntry(dto.description, dto.expectedDuration, dto.greenSpace, dto.urgencyDegree, dto.entryStatus, dto.date, dto.team, dto.vehicles);
    }

    /**
     * To dto agenda entry dto.
     *
     * @param agendaEntry the agenda entry
     * @return the agenda entry dto
     */
    public AgendaEntryDTO toDTO(AgendaEntry agendaEntry){
        return new AgendaEntryDTO(agendaEntry.getDescription(), agendaEntry.getExpectedDuration(), agendaEntry.getGreenSpace(), agendaEntry.getUrgencyDegree(), agendaEntry.getEntryStatus(), agendaEntry.getDate(), agendaEntry.getAssociatedTeam(), agendaEntry.getAssociatedVehicles());
    }

    /**
     * To dto list array list.
     *
     * @param toDoEntryList the to do entry list
     * @return the array list
     */
    public ArrayList<AgendaEntryDTO> toDtoList(List<AgendaEntry> toDoEntryList){
        ArrayList<AgendaEntryDTO> agendaEntryDTOList = new ArrayList<>();
        for(AgendaEntry agendaEntry : toDoEntryList){
            agendaEntryDTOList.add(toDTO(agendaEntry));
        }
        return agendaEntryDTOList;
    }
}
