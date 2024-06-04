package pt.ipp.isep.dei.esoft.project.mapper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.mappers.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.repository.enums.GreenSpaceTypeENUM;

import java.util.Arrays;
import java.util.List;

public class GreenSpaceMapperTest {

    private final GreenSpaceMapper mapper = new GreenSpaceMapper();

    @Test
    public void testToEntity() {
        GreenSpaceDTO dto = new GreenSpaceDTO("Central Park", "NYC", 340, GreenSpaceTypeENUM.LARGE_SIZED_PARK, "gsm@gsm.app");
        GreenSpace entity = mapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.name, entity.getName());
        assertEquals(dto.address, entity.getAddress());
        assertEquals(dto.area, entity.getArea());
        assertEquals(dto.type, entity.getType());
        assertEquals(dto.emailGSM, entity.getEmailGSM());
    }

    @Test
    public void testToDTO() {
        GreenSpace entity = new GreenSpace(GreenSpaceTypeENUM.MEDIUM_SIZED_PARK, "Central Park", "NYC", 34, "gsm@gsm.app");
        GreenSpaceDTO dto = mapper.toDTO(entity);

        assertNotNull(dto);
        assertEquals(entity.getName(), dto.name);
        assertEquals(entity.getAddress(), dto.address);
        assertEquals(entity.getArea(), dto.area);
        assertEquals(entity.getType(), dto.type);
        assertEquals(entity.getEmailGSM(), dto.emailGSM);
    }

    @Test
    public void testToDTOList() {
        GreenSpace entity1 = new GreenSpace(GreenSpaceTypeENUM.LARGE_SIZED_PARK, "Central Park", "NYC", 340, "gsm@gsm.app");
        GreenSpace entity2 = new GreenSpace(GreenSpaceTypeENUM.MEDIUM_SIZED_PARK, "Brooklyn Botanic Garden", "NYC", 52, "gsm@gsm.app");
        List<GreenSpace> entityList = Arrays.asList(entity1, entity2);

        List<GreenSpaceDTO> dtoList = mapper.toDTOList(entityList);

        assertNotNull(dtoList);
        assertEquals(2, dtoList.size());

        GreenSpaceDTO dto1 = dtoList.get(0);
        GreenSpaceDTO dto2 = dtoList.get(1);

        assertEquals(entity1.getName(), dto1.name);
        assertEquals(entity1.getAddress(), dto1.address);
        assertEquals(entity1.getArea(), dto1.area);
        assertEquals(entity1.getType(), dto1.type);
        assertEquals(entity1.getEmailGSM(), dto1.emailGSM);

        assertEquals(entity2.getName(), dto2.name);
        assertEquals(entity2.getAddress(), dto2.address);
        assertEquals(entity2.getArea(), dto2.area);
        assertEquals(entity2.getType(), dto2.type);
        assertEquals(entity2.getEmailGSM(), dto2.emailGSM);
    }
}
