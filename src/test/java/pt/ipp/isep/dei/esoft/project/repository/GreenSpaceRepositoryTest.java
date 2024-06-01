package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.enums.GreenSpaceTypeENUM;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GreenSpaceRepositoryTest {

    private GreenSpaceRepository greenSpaceRepository;
    private GreenSpace greenSpace;

    @BeforeEach
    public void setUp() {
        greenSpaceRepository = new GreenSpaceRepository();
        greenSpace = new GreenSpace(GreenSpaceTypeENUM.LARGE_SIZED_PARK, "Central Park", "New York, NY", 843, "centralpark@greenspace.com");
    }

    @Test
    public void testRegisterGreenSpaceUnique() {
        assertTrue(greenSpaceRepository.registerGreenSpace(greenSpace));
        assertEquals(1, greenSpaceRepository.getGreenSpaceList(greenSpace.getEmailGSM()).size());
    }

    @Test
    public void testRegisterGreenSpaceDuplicate() {
        greenSpaceRepository.registerGreenSpace(greenSpace);
        GreenSpace duplicateGreenSpace = new GreenSpace(GreenSpaceTypeENUM.LARGE_SIZED_PARK, "Central Park", "New York, NY", 843, "centralpark@greenspace.com");
        assertFalse(greenSpaceRepository.registerGreenSpace(duplicateGreenSpace));
    }

    @Test
    public void testGreenSpaceIsUnique() {
        greenSpaceRepository.registerGreenSpace(greenSpace);
        GreenSpace uniqueGreenSpace = new GreenSpace(GreenSpaceTypeENUM.GARDEN, "Botanical Garden", "Brooklyn, NY", 52, "botanical@greenspace.com");
        assertTrue(greenSpaceRepository.greenSpaceIsUnique(uniqueGreenSpace));
    }

    @Test
    public void testGreenSpaceIsNotUnique() {
        greenSpaceRepository.registerGreenSpace(greenSpace);
        GreenSpace duplicateGreenSpace = new GreenSpace(GreenSpaceTypeENUM.LARGE_SIZED_PARK, "Central Park", "New York, NY", 843, "centralpark@greenspace.com");
        assertFalse(greenSpaceRepository.greenSpaceIsUnique(duplicateGreenSpace));
    }

    @Test
    public void testGetGreenSpaceList() {
        greenSpaceRepository.registerGreenSpace(greenSpace);
        ArrayList<GreenSpace> list = greenSpaceRepository.getGreenSpaceList(greenSpace.getEmailGSM());
        assertEquals(1, list.size());
        assertEquals(greenSpace, list.get(0));
    }

    @Test
    public void testGetGreenSpaceListEmpty() {
        ArrayList<GreenSpace> list = greenSpaceRepository.getGreenSpaceList("nonexistent@greenspace.com");
        assertTrue(list.isEmpty());
    }
}
