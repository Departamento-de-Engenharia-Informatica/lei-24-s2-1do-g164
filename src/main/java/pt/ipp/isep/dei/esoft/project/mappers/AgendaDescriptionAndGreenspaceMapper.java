package pt.ipp.isep.dei.esoft.project.mappers;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.dto.AgendaDescriptionAndGreenspaceDTO;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatus;

public class AgendaDescriptionAndGreenspaceMapper {
    public AgendaDescriptionAndGreenspaceDTO toDTO(String description, GreenSpace greenSpace, EntryStatus status){
        return new AgendaDescriptionAndGreenspaceDTO(description, greenSpace, status);
    }
}
