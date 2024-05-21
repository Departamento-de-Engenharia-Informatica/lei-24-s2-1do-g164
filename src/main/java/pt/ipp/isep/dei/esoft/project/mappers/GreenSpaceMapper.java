package pt.ipp.isep.dei.esoft.project.mappers;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoEntryDTO;

public class GreenSpaceMapper {

    public GreenSpace toEntity(GreenSpaceDTO greenSpaceDTO){
        return new GreenSpace(greenSpaceDTO.type, greenSpaceDTO.name, greenSpaceDTO.address, greenSpaceDTO.area);
    }

    public GreenSpaceDTO toDTO(GreenSpace greenSpace){
        return new GreenSpaceDTO(greenSpace.getName());
    }
}
