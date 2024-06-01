package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.ToDoEntry;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;
import pt.ipp.isep.dei.esoft.project.repository.enums.GreenSpaceTypeENUM;
import pt.ipp.isep.dei.esoft.project.repository.enums.UrgencyDegreeENUM;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoEntryRepositoryTest {

    private ToDoEntryRepository toDoEntryRepository;
    private ToDoEntry toDoEntry;
    private GreenSpace greenSpace;
    private ArrayList<Vehicle> vehicles;

    @BeforeEach
    public void setUp() {
        toDoEntryRepository = new ToDoEntryRepository();

        greenSpace = new GreenSpace(GreenSpaceTypeENUM.GARDEN, "Central Park", "Av. Triste 23", 32, "gsm@gsm.app");

        toDoEntry = new ToDoEntry("Plant trees", 5, greenSpace, UrgencyDegreeENUM.HIGH);

        vehicles = new ArrayList<>();
    }

    @Test
    public void testRegisterToDoEntryUnique() {
        assertTrue(toDoEntryRepository.registerToDoEntry(toDoEntry));
        assertEquals(1, toDoEntryRepository.getToDoEntryList(greenSpace.getEmailGSM()).size());
    }

    @Test
    public void testRegisterToDoEntryDuplicate() {
        toDoEntryRepository.registerToDoEntry(toDoEntry);
        ToDoEntry duplicateEntry = new ToDoEntry("Plant trees", 5, greenSpace, UrgencyDegreeENUM.HIGH);
        assertFalse(toDoEntryRepository.registerToDoEntry(duplicateEntry));
    }

    @Test
    public void testUpdateStatus() {
        toDoEntryRepository.registerToDoEntry(toDoEntry);
        assertTrue(toDoEntryRepository.updateStatus(toDoEntry, EntryStatusENUM.COMPLETED));
        assertEquals(EntryStatusENUM.COMPLETED, toDoEntry.getEntryStatus());
    }

    @Test
    public void testUpdateStatusNotFound() {
        assertFalse(toDoEntryRepository.updateStatus(toDoEntry, EntryStatusENUM.COMPLETED));
    }

    @Test
    public void testGetToDoEntryList() {
        toDoEntryRepository.registerToDoEntry(toDoEntry);
        ArrayList<ToDoEntry> list = toDoEntryRepository.getToDoEntryList(greenSpace.getEmailGSM());
        assertEquals(1, list.size());
        assertEquals(toDoEntry, list.get(0));
    }

    @Test
    public void testGetToDoEntryListByStatus() {
        toDoEntryRepository.registerToDoEntry(toDoEntry);
        ArrayList<ToDoEntry> list = toDoEntryRepository.getToDoEntryListByStatus(greenSpace.getEmailGSM(), EntryStatusENUM.COMPLETED);
        assertEquals(1, list.size());
        assertEquals(toDoEntry, list.get(0));
    }

}
