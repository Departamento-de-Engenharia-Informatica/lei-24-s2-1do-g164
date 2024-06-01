package pt.ipp.isep.dei.esoft.project.mappers;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.ToDoEntry;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoEntryDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Green space mapper.
 */
public class GreenSpaceMapper {

    /**
     * To entity green space.
     *
     * @param greenSpaceDTO the green space dto
     * @return the green space
     */
    public GreenSpace toEntity(GreenSpaceDTO greenSpaceDTO){
        return new GreenSpace(greenSpaceDTO.type, greenSpaceDTO.name, greenSpaceDTO.address, greenSpaceDTO.area, greenSpaceDTO.emailGSM);
    }

    /**
     * To dto green space dto.
     *
     * @param greenSpace the green space
     * @return the green space dto
     */
    public GreenSpaceDTO toDTO(GreenSpace greenSpace){
        return new GreenSpaceDTO(greenSpace.getName(), greenSpace.getAddress(), greenSpace.getArea(), greenSpace.getType(), greenSpace.getEmailGSM());
    }

    /**
     * To dto list array list.
     *
     * @param greenSpaceList the green space list
     * @return the array list
     */
    public ArrayList<GreenSpaceDTO> toDTOList(List<GreenSpace> greenSpaceList){
        ArrayList<GreenSpaceDTO> greenSpaceDTOsList = new ArrayList<>();
        for(GreenSpace greenSpace : greenSpaceList){
            greenSpaceDTOsList.add(toDTO(greenSpace));
        }
        return greenSpaceDTOsList;
    }

}
