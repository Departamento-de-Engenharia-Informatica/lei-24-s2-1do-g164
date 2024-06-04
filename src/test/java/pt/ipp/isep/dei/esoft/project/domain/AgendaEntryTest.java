package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;
import pt.ipp.isep.dei.esoft.project.repository.enums.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AgendaEntryTest {

    private AgendaEntry agendaEntry;
    private AgendaEntry agendaEntryWithoutTeam;
    private LocalDate date;
    private Team team;
    private ArrayList<Vehicle> vehicles;
    private GreenSpace greenSpace;

    @BeforeEach
    void setUp() {
        date = LocalDate.now();
        TeamRepository teamRepository = Repositories.getInstance().getTeamRepository();
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        Collaborator c1= collaboratorRepository.getCollaboratorList().get(3);
        Collaborator c2 = collaboratorRepository.getCollaboratorList().get(4);
        ArrayList<Collaborator> collaborators1= new ArrayList<>();
        collaborators1.add(c1);
        collaborators1.add(c2);
        Team team = new Team(collaborators1, c1.getSkills(), TeamStatusENUM.PENDING);
        teamRepository.registerTeam(team);

        vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("ford","focus", "AA-00-AA", VehicleTypeENUM.HEAVY_VEHICLE, 200, 229, 200000, "20-00-2222", "22-00-2002", 20000));
        greenSpace = new GreenSpace(GreenSpaceTypeENUM.GARDEN, "garden", "aaaaaaa", 200, "gsm@gsm.app");

        agendaEntry = new AgendaEntry("Description", 120, greenSpace, UrgencyDegreeENUM.HIGH, EntryStatusENUM.PENDING, date, team, vehicles);
        agendaEntryWithoutTeam = new AgendaEntry("Description", 120, greenSpace, UrgencyDegreeENUM.HIGH, EntryStatusENUM.PENDING, date, vehicles);
    }

    @Test
    void testEquals() {
        AgendaEntry anotherEntry = new AgendaEntry("Description", 120, greenSpace, UrgencyDegreeENUM.HIGH, EntryStatusENUM.PENDING, date, team, vehicles);
        assertEquals(agendaEntry, anotherEntry);

        AgendaEntry differentEntry = new AgendaEntry("Different", 60, greenSpace, UrgencyDegreeENUM.LOW, EntryStatusENUM.DONE, date.plusDays(1), team, vehicles);
        assertNotEquals(agendaEntry, differentEntry);
    }

    @Test
    void testHashCode() {
        AgendaEntry anotherEntry = new AgendaEntry("Description", 120, greenSpace, UrgencyDegreeENUM.HIGH, EntryStatusENUM.PENDING, date, team, vehicles);
        assertEquals(agendaEntry.hashCode(), anotherEntry.hashCode());
    }

    @Test
    void getDescription() {
        assertEquals("Description", agendaEntry.getDescription());
    }

    @Test
    void setDescription() {
        agendaEntry.setDescription("New Description");
        assertEquals("New Description", agendaEntry.getDescription());
    }

    @Test
    void getExpectedDuration() {
        assertEquals(120, agendaEntry.getExpectedDuration());
    }

    @Test
    void setExpectedDuration() {
        agendaEntry.setExpectedDuration(60);
        assertEquals(60, agendaEntry.getExpectedDuration());
    }

    @Test
    void getEntryStatus() {
        assertEquals(EntryStatusENUM.PENDING, agendaEntry.getEntryStatus());
    }

    @Test
    void setEntryStatus() {
        agendaEntry.setEntryStatus(EntryStatusENUM.DONE);
        assertEquals(EntryStatusENUM.DONE, agendaEntry.getEntryStatus());
    }

    @Test
    void getGreenSpace() {
        assertEquals(greenSpace, agendaEntry.getGreenSpace());
    }

    @Test
    void setGreenSpace() {
        GreenSpace newGreenSpace = new GreenSpace(GreenSpaceTypeENUM.GARDEN, "aaaaaasdasd", "asdasdsadsadadxcazcas", 112, "gsm@gsm.app");
        agendaEntry.setGreenSpace(newGreenSpace);
        assertEquals(newGreenSpace, agendaEntry.getGreenSpace());
    }

    @Test
    void getUrgencyDegree() {
        assertEquals(UrgencyDegreeENUM.HIGH, agendaEntry.getUrgencyDegree());
    }

    @Test
    void setUrgencyDegree() {
        agendaEntry.setUrgencyDegree(UrgencyDegreeENUM.LOW);
        assertEquals(UrgencyDegreeENUM.LOW, agendaEntry.getUrgencyDegree());
    }

    @Test
    void getDate() {
        assertEquals(date, agendaEntry.getDate());
    }

    @Test
    void setDate() {
        LocalDate newDate = LocalDate.now().plusDays(1);
        agendaEntry.setDate(newDate);
        assertEquals(newDate, agendaEntry.getDate());
    }

    @Test
    void getAssociatedTeam() {
        assertEquals(team, agendaEntry.getAssociatedTeam());
    }

    @Test
    void setAssociatedTeam() {
        TeamRepository teamRepository = Repositories.getInstance().getTeamRepository();
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        Collaborator c3= collaboratorRepository.getCollaboratorList().get(5);
        Collaborator c4= collaboratorRepository.getCollaboratorList().get(6);

        ArrayList<Collaborator> collaborators2= new ArrayList<>();
        collaborators2.add(c3);
        collaborators2.add(c4);
        Team newTeam= new Team(collaborators2, c3.getSkills(), TeamStatusENUM.PENDING);
        teamRepository.registerTeam(newTeam);

        agendaEntry.setAssociatedTeam(newTeam);
        assertEquals(newTeam, agendaEntry.getAssociatedTeam());
    }

    @Test
    void getAssociatedVehicles() {
        assertEquals(vehicles, agendaEntry.getAssociatedVehicles());
    }

    @Test
    void setAssociatedVehicles() {
        ArrayList<Vehicle> newVehicles = new ArrayList<>();
        newVehicles.add(new Vehicle("OPEL","CORSA", "AA-10-AA", VehicleTypeENUM.HEAVY_VEHICLE, 2200, 3329, 250000, "219-00-2202", "25-00-2022", 25000));
        agendaEntry.setAssociatedVehicles(newVehicles);
        assertEquals(newVehicles, agendaEntry.getAssociatedVehicles());
    }

    @Test
    void testAgendaEntryWithoutTeam() {
        assertNull(agendaEntryWithoutTeam.getAssociatedTeam());
    }
}
