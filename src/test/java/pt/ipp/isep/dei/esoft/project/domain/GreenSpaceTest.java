package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.repository.enums.GreenSpaceTypeENUM;

import static org.junit.jupiter.api.Assertions.*;

public class GreenSpaceTest {

    private GreenSpace greenSpace;

    @BeforeEach
    public void setUp() {
        greenSpace = new GreenSpace(GreenSpaceTypeENUM.LARGE_SIZED_PARK, "Central Park", "New York, NY", 843, "centralpark@greenspace.com");
    }

    @Test
    public void testConstructor() {
        assertEquals(GreenSpaceTypeENUM.LARGE_SIZED_PARK, greenSpace.getType());
        assertEquals("Central Park", greenSpace.getName());
        assertEquals("New York, NY", greenSpace.getAddress());
        assertEquals(843, greenSpace.getArea());
        assertEquals("centralpark@greenspace.com", greenSpace.getEmailGSM());
    }

    @Test
    public void testToString() {
        String expected = "Name: Central Park - Address: New York, NY - Area: 843 hectares - Type: Large Sized Park";
        assertEquals(expected, greenSpace.toString());
    }

    @Test
    public void testEqualsSame() {
        GreenSpace sameGreenSpace = new GreenSpace(GreenSpaceTypeENUM.LARGE_SIZED_PARK, "Central Park", "New York, NY", 843, "centralpark@greenspace.com");
        assertTrue(greenSpace.equals(sameGreenSpace));
    }

    @Test
    public void testEqualsDifferent() {
        GreenSpace differentGreenSpace = new GreenSpace(GreenSpaceTypeENUM.LARGE_SIZED_PARK, "Botanical Garden", "Brooklyn, NY", 52, "botanical@greenspace.com");
        assertFalse(greenSpace.equals(differentGreenSpace));
    }

    @Test
    public void testSetName() {
        greenSpace.setName("Prospect Park");
        assertEquals("Prospect Park", greenSpace.getName());
    }

    @Test
    public void testSetAddress() {
        greenSpace.setAddress("Brooklyn, NY");
        assertEquals("Brooklyn, NY", greenSpace.getAddress());
    }

    @Test
    public void testSetArea() {
        greenSpace.setArea(585);
        assertEquals(585, greenSpace.getArea());
    }

    @Test
    public void testSetType() {
        greenSpace.setType(GreenSpaceTypeENUM.GARDEN);
        assertEquals(GreenSpaceTypeENUM.GARDEN, greenSpace.getType());
    }

    @Test
    public void testSetEmailGSM() {
        greenSpace.setEmailGSM("prospectpark@greenspace.com");
        assertEquals("prospectpark@greenspace.com", greenSpace.getEmailGSM());
    }
}
