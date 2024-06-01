package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;
import pt.ipp.isep.dei.esoft.project.repository.enums.GreenSpaceTypeENUM;
import pt.ipp.isep.dei.esoft.project.repository.enums.UrgencyDegreeENUM;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoEntryTest {

    private ToDoEntry toDoEntry;
    private GreenSpace greenSpace;
    private List<Vehicle> vehicles;

    @BeforeEach
    public void setUp() {
        greenSpace = new GreenSpace(GreenSpaceTypeENUM.LARGE_SIZED_PARK, "Central Park", "Av. Gonçalo Farinha", 33, "gsm@gsm.app");


        toDoEntry = new ToDoEntry("Plant trees", 5, greenSpace, UrgencyDegreeENUM.HIGH);
    }

    @Test
    public void testConstructor() {
        assertEquals("Plant trees", toDoEntry.getDescription());
        assertEquals(5, toDoEntry.getExpectedDuration());
        assertEquals(EntryStatusENUM.PENDING, toDoEntry.getEntryStatus());
        assertEquals(UrgencyDegreeENUM.HIGH, toDoEntry.getUrgencyDegree());
        assertEquals(greenSpace, toDoEntry.getGreenSpace());
        assertTrue(toDoEntry.getAssignedVehicles().isEmpty());
    }

    @Test
    public void testToString() {
        String expected = "Plant trees - Expected Duration: 5 - Status: PENDING - Green Space: Central Park - Urgency Degree: High";
        assertEquals(expected, toDoEntry.toString());
    }

    @Test
    public void testEquals() {
        ToDoEntry sameToDoEntry = new ToDoEntry("Plant trees", 5, greenSpace, UrgencyDegreeENUM.HIGH);
        assertTrue(toDoEntry.equals(sameToDoEntry));
    }

    @Test
    public void testNotEquals() {
        GreenSpace differentGreenSpace = new GreenSpace(GreenSpaceTypeENUM.MEDIUM_SIZED_PARK,"Other Park", "Av.Ola 23", 33, "gsm2@gsm2.app");

        ToDoEntry differentToDoEntry = new ToDoEntry("Clean area", 3, differentGreenSpace, UrgencyDegreeENUM.MEDIUM);
        assertFalse(toDoEntry.equals(differentToDoEntry));
    }

    @Test
    public void testSetDescription() {
        toDoEntry.setDescription("Clean area");
        assertEquals("Clean area", toDoEntry.getDescription());
    }

    @Test
    public void testSetExpectedDuration() {
        toDoEntry.setExpectedDuration(10);
        assertEquals(10, toDoEntry.getExpectedDuration());
    }

    @Test
    public void testSetEntryStatus() {
        toDoEntry.setEntryStatus(EntryStatusENUM.COMPLETED);
        assertEquals(EntryStatusENUM.COMPLETED, toDoEntry.getEntryStatus());
    }

    @Test
    public void testSetGreenSpace() {
        GreenSpace newGreenSpace = new GreenSpace(GreenSpaceTypeENUM.GARDEN,"Other Park", "Rua Pedro Azevedo", 22, "gs@gsm.app");

        toDoEntry.setGreenSpace(newGreenSpace);
        assertEquals(newGreenSpace, toDoEntry.getGreenSpace());
    }

    @Test
    public void testSetUrgencyDegree() {
        toDoEntry.setUrgencyDegree(UrgencyDegreeENUM.LOW);
        assertEquals(UrgencyDegreeENUM.LOW, toDoEntry.getUrgencyDegree());
    }
}

