package pt.ipp.isep.dei.esoft.project.mappers;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;

public class AgendaEntryMapper {

    public AgendaEntry toEntity(AgendaEntryDTO dto){
        return new AgendaEntry(dto.description, dto.expectedDuration, dto.greenSpace, dto.urgencyDegree, dto.entryStatus, dto.date, dto.team, dto.vehicles);
    }

    public AgendaEntry toDTO(AgendaEntry agendaEntry){
        return new AgendaEntry(agendaEntry.getDescription(), agendaEntry.getExpectedDuration(), agendaEntry.getGreenSpace(), agendaEntry.getUrgencyDegree(), agendaEntry.getEntryStatus(), agendaEntry.getDate(), agendaEntry.getAssociatedTeam(), agendaEntry.getAssociatedVehicles());
    }

}
