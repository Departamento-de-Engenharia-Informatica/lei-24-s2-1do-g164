package pt.ipp.isep.dei.esoft.project.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.ToDoEntry;
import pt.ipp.isep.dei.esoft.project.dto.ToDoEntryDTO;
import pt.ipp.isep.dei.esoft.project.mappers.ToDoEntryMapper;
import pt.ipp.isep.dei.esoft.project.repository.enums.GreenSpaceTypeENUM;
import pt.ipp.isep.dei.esoft.project.repository.enums.UrgencyDegreeENUM;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoEntryMapperTest {

    private ToDoEntryMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ToDoEntryMapper();
    }

    @Test
    void testToEntity() {
        // Create a sample ToDoEntryDTO
        ToDoEntryDTO dto = new ToDoEntryDTO("Sample Task", 2, new GreenSpace(GreenSpaceTypeENUM.MEDIUM_SIZED_PARK, "Central Park", "NYC", 34, "gsm@gsm.app"), UrgencyDegreeENUM.MEDIUM);

        // Convert DTO to entity
        ToDoEntry entity = mapper.toEntity(dto);

        // Check if the entity attributes match the DTO attributes
        assertEquals(entity.getDescription(), dto.description);
        assertEquals(entity.getExpectedDuration(), dto.expectedDuration);
        assertEquals(entity.getGreenSpace(), dto.greenSpace);
        assertEquals(entity.getUrgencyDegree(), dto.urgencyDegree);
    }

    @Test
    void testToDTO() {
        // Create a sample ToDoEntry
        ToDoEntry entry = new ToDoEntry("Sample Task", 2, new GreenSpace(GreenSpaceTypeENUM.MEDIUM_SIZED_PARK, "Central Park", "NYC", 34, "gsm@gsm.app"), UrgencyDegreeENUM.MEDIUM  );

        // Convert entity to DTO
        ToDoEntryDTO dto = mapper.toDTO(entry);

        // Check if the DTO attributes match the entity attributes
        assertEquals(dto.description, entry.getDescription());
        assertEquals(dto.expectedDuration, entry.getExpectedDuration());
        assertEquals(dto.greenSpace, entry.getGreenSpace());
        assertEquals(dto.urgencyDegree, entry.getUrgencyDegree());
        assertEquals(dto.entryStatus, entry.getEntryStatus());
    }

    @Test
    void testToDTOList() {
        // Create a list of sample ToDoEntry objects
        ToDoEntry entry1 = new ToDoEntry("Task 1", 2, new GreenSpace(GreenSpaceTypeENUM.MEDIUM_SIZED_PARK, "Central Park", "NYC", 34, "gsm@gsm.app"), UrgencyDegreeENUM.MEDIUM);
        ToDoEntry entry2 = new ToDoEntry("Task 2", 3, new GreenSpace(GreenSpaceTypeENUM.MEDIUM_SIZED_PARK, "Central Park", "NYC", 34, "gsm@gsm.app"), UrgencyDegreeENUM.MEDIUM);
        List<ToDoEntry> entryList = List.of(entry1, entry2);

        // Convert the list of entities to a list of DTOs
        ArrayList<ToDoEntryDTO> dtoList = mapper.toDTOList(entryList);

        // Check if the length of the DTO list matches the length of the entity list
        assertEquals(dtoList.size(), entryList.size());

        // Check if the attributes of each DTO match the corresponding entity attributes
        for (int i = 0; i < entryList.size(); i++) {
            assertEquals(dtoList.get(i).description, entryList.get(i).getDescription());
            assertEquals(dtoList.get(i).expectedDuration, entryList.get(i).getExpectedDuration());
            assertEquals(dtoList.get(i).greenSpace, entryList.get(i).getGreenSpace());
            assertEquals(dtoList.get(i).urgencyDegree, entryList.get(i).getUrgencyDegree());
            assertEquals(dtoList.get(i).entryStatus, entryList.get(i).getEntryStatus());
        }
    }
}
