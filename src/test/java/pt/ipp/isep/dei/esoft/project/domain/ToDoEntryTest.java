package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;
import pt.ipp.isep.dei.esoft.project.repository.enums.GreenSpaceTypeENUM;
import pt.ipp.isep.dei.esoft.project.repository.enums.UrgencyDegreeENUM;
import pt.ipp.isep.dei.esoft.project.repository.enums.VehicleTypeENUM;

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
    public void testSetEntryStatus() {
        toDoEntry.setEntryStatus(EntryStatusENUM.COMPLETED);
        assertEquals(EntryStatusENUM.COMPLETED, toDoEntry.getEntryStatus());
    }
    @Test
    public void testToStringFormat() {
        String expected = "Plant trees - Expected Duration: 5 - Status: PENDING - Green Space: Central Park - Urgency Degree: High";
        assertEquals(expected, toDoEntry.toString());
    }

    @Test
    public void testHashCode() {
        ToDoEntry sameToDoEntry = new ToDoEntry("Plant trees", 5, greenSpace, UrgencyDegreeENUM.HIGH);
        assertEquals(toDoEntry.hashCode(), sameToDoEntry.hashCode());
    }

    @Test
    public void testSetDescription() {
        String newDescription = "Clean area";
        toDoEntry.setDescription(newDescription);
        assertEquals(newDescription, toDoEntry.getDescription());
    }

    @Test
    public void testSetExpectedDuration() {
        int newExpectedDuration = 10;
        toDoEntry.setExpectedDuration(newExpectedDuration);
        assertEquals(newExpectedDuration, toDoEntry.getExpectedDuration());
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

    @Test
    public void testAddVehicles() {
        Vehicle vehicle1 = new Vehicle("Mercedes", "Class A", "87-UI-28", VehicleTypeENUM.LIGHT_VEHICLE,
                1415, 1200, 25000, "02-07-2020", "12-09-2018", 20000);
        Vehicle vehicle2 = new Vehicle("B,W", "Class A", "87-UI-29", VehicleTypeENUM.LIGHT_VEHICLE,
                1415, 1200, 25000, "02-07-2020", "12-09-2018", 20000);

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);

        assertTrue(toDoEntry.addVehicles(vehicles));
        assertEquals(vehicles, toDoEntry.getAssignedVehicles());
    }

    @Test
    public void testAddVehiclesWithNullList() {
        assertFalse(toDoEntry.addVehicles(null));
        assertTrue(toDoEntry.getAssignedVehicles().isEmpty());
    }

    @Test
    public void testGetAssignedVehicles() {
        Vehicle vehicle1 = new Vehicle("Mercedes", "Class A", "87-UI-28", VehicleTypeENUM.LIGHT_VEHICLE,
                1415, 1200, 25000, "02-07-2020", "12-09-2018", 20000);
        Vehicle vehicle2 = new Vehicle("B,W", "Class A", "87-UI-29", VehicleTypeENUM.LIGHT_VEHICLE,
                1415, 1200, 25000, "02-07-2020", "12-09-2018", 20000);

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);

        toDoEntry.addVehicles(vehicles);

        assertEquals(vehicles, toDoEntry.getAssignedVehicles());
    }
}



