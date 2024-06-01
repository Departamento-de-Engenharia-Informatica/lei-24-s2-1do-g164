package pt.ipp.isep.dei.esoft.project.mappers;

import pt.ipp.isep.dei.esoft.project.domain.ToDoEntry;
import pt.ipp.isep.dei.esoft.project.dto.ToDoEntryDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * The type To do entry mapper.
 */
public class ToDoEntryMapper {

    /**
     * To entity to do entry.
     *
     * @param toDoEntryDTO the to do entry dto
     * @return the to do entry
     */
    public ToDoEntry toEntity(ToDoEntryDTO toDoEntryDTO){
        return new ToDoEntry(toDoEntryDTO.description, toDoEntryDTO.expectedDuration, toDoEntryDTO.greenSpace, toDoEntryDTO.urgencyDegree);
    }

    /**
     * To dto to do entry dto.
     *
     * @param toDoEntry the to do entry
     * @return the to do entry dto
     */
    public ToDoEntryDTO toDTO(ToDoEntry toDoEntry){
        return new ToDoEntryDTO(toDoEntry.getDescription(), toDoEntry.getExpectedDuration(), toDoEntry.getGreenSpace(), toDoEntry.getUrgencyDegree(), toDoEntry.getEntryStatus());
    }

    /**
     * To dto list array list.
     *
     * @param toDoEntryList the to do entry list
     * @return the array list
     */
    public ArrayList<ToDoEntryDTO> toDTOList(List<ToDoEntry> toDoEntryList){
        ArrayList<ToDoEntryDTO> toDoEntryDTOList = new ArrayList<>();
        for(ToDoEntry toDoEntry : toDoEntryList){
            toDoEntryDTOList.add(toDTO(toDoEntry));
        }
        return toDoEntryDTOList;
    }
}
