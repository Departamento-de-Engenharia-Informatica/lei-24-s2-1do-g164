package pt.ipp.isep.dei.esoft.project.mappers;

import pt.ipp.isep.dei.esoft.project.domain.ToDoEntry;
import pt.ipp.isep.dei.esoft.project.dto.ToDoEntryDTO;

public class ToDoEntryMapper {

    public ToDoEntry toEntity(ToDoEntryDTO registerToDoEntryDTO){
        return new ToDoEntry(registerToDoEntryDTO.description, registerToDoEntryDTO.expectedDuration, registerToDoEntryDTO.greenSpace, registerToDoEntryDTO.urgencyDegree);
    }

    public ToDoEntryDTO toDto(ToDoEntry toDoEntry){
        return new ToDoEntryDTO(toDoEntry.getDescription(), toDoEntry.getExpectedDuration(), toDoEntry.getGreenSpace(), toDoEntry.getUrgencyDegree(), toDoEntry.getEntryStatus());
    }
}
