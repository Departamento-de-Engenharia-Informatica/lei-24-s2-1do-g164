package pt.ipp.isep.dei.esoft.project.mappers;

import pt.ipp.isep.dei.esoft.project.domain.ToDoEntry;
import pt.ipp.isep.dei.esoft.project.dto.ToDoEntryDTO;

import java.util.ArrayList;
import java.util.List;

public class ToDoEntryMapper {

    public ToDoEntry toEntity(ToDoEntryDTO toDoEntryDTO){
        return new ToDoEntry(toDoEntryDTO.description, toDoEntryDTO.expectedDuration, toDoEntryDTO.greenSpace, toDoEntryDTO.urgencyDegree);
    }

    public ToDoEntryDTO toDTO(ToDoEntry toDoEntry){
        return new ToDoEntryDTO(toDoEntry.getDescription(), toDoEntry.getExpectedDuration(), toDoEntry.getGreenSpace(), toDoEntry.getUrgencyDegree(), toDoEntry.getEntryStatus());
    }

    public ArrayList<ToDoEntryDTO> toDTOList(List<ToDoEntry> toDoEntryList){
        ArrayList<ToDoEntryDTO> toDoEntryDTOList = new ArrayList<>();
        for(ToDoEntry toDoEntry : toDoEntryList){
            toDoEntryDTOList.add(toDTO(toDoEntry));
        }
        return toDoEntryDTOList;
    }
}
