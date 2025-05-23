package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AgendaEntryRepository;
import pt.ipp.isep.dei.esoft.project.repository.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AgendaEntryRepositoryTest {

    private AgendaEntryRepository repository;
    private GreenSpace greenSpace;
    private AgendaEntry agendaEntry;

    private GreenSpace greenSpaceOther;

    private AgendaEntry agendaEntryOther;
    private Team team;
    private ArrayList<Vehicle> vehicles;
    private  CollaboratorRepository collaboratorRepository;
    private ArrayList<Collaborator> collaborators1;
    private ArrayList<Collaborator> collaborators2;

    private Collaborator c1;
    private Collaborator c2;
    private Collaborator c3;
    private Collaborator c4;
    private ArrayList<Skill> skills1;
    private Skill s1;
    private Skill s2;
    private Skill s3;
    private Skill s4;
    private ArrayList<Skill> skills2;
    private Team t1;
    private Team t2;
    private Team t3;
    private Job job;
    private DocumentTypeENUM docType;
    private TeamRepository repo;

    @BeforeEach
    void setUp() {
        repository = new AgendaEntryRepository();
        greenSpace = new GreenSpace(GreenSpaceTypeENUM.LARGE_SIZED_PARK, "Central Park", "Av. Gonçalo Farinha", 33, "gsm@gsm.app");
        vehicles = new ArrayList<>();
        greenSpaceOther = new GreenSpace(GreenSpaceTypeENUM.LARGE_SIZED_PARK, "Parque feliz", "Av. Gonçalo Farinha", 73, "gsm@gsm.app");

        collaborators1 = new ArrayList<>();
        collaborators2 = new ArrayList<>();
        skills1 = new ArrayList<>();
        skills2 = new ArrayList<>();

        s1 = new Skill("Communication");
        s2 = new Skill("Electrician");
        skills1.add(s1);
        skills1.add(s2);

        s3 = new Skill("Landscaping");
        s4 = new Skill("Propagation");
        skills2.add(s3);
        skills2.add(s4);

        job = new Job("Designer");
        docType = DocumentTypeENUM.CITIZEN_CARD;

        c1 = new Collaborator("John Doe", 123456789, "01-01-1990", "01-01-2020", "123 Main St", 987699321, job, docType, 451238965, "siii@euro.lol");
        c2 = new Collaborator("Alice Vieira", 928456689, "15-07-2006", "01-01-2020", "123 Main St", 983554321, job, docType,451238965, "siii@euro.lol");
        c3 = new Collaborator("Ambrosio Leite", 928945689, "19-09-1999", "01-01-2020", "123 Main St", 987554321, job, docType,  451238965, "siii@euro.lol");
        c4 = new Collaborator("Elsa Freites", 988955779, "10-06-2005", "01-01-2020", "123 Main St", 987640321, job, docType,  451238965, "siii@euro.lol");

        collaborators1.add(c1);
        collaborators1.add(c2);
        collaborators2.add(c3);
        collaborators2.add(c4);

        t1 = new Team(collaborators1, skills1, TeamStatusENUM.PENDING);
        t2 = new Team(collaborators2, skills2, TeamStatusENUM.PENDING);
        t3 = new Team(collaborators1, skills1,  TeamStatusENUM.PENDING);

        agendaEntry = new AgendaEntry("regar", 2, greenSpace, UrgencyDegreeENUM.HIGH, EntryStatusENUM.PLANNED, LocalDateTime.now(), t1, vehicles);
        agendaEntryOther = new AgendaEntry("plantar", 3, greenSpaceOther, UrgencyDegreeENUM.HIGH, EntryStatusENUM.PLANNED, LocalDateTime.now(), vehicles);
    }

    @Test
    void testAddEntryToAgenda() {
        boolean result = repository.addEntryToAgenda(agendaEntry);
        assertTrue(result);
        assertEquals(1, repository.getAgendaEntryWithoutDoneList(greenSpace.getEmailGSM()).size());
    }

    @Test
    void testAddEntryToAgendaDuplicate() {
        repository.addEntryToAgenda(agendaEntry);
        boolean result = repository.addEntryToAgenda(agendaEntry);
        assertFalse(result);
    }

    @Test
    void testGetAgendaEntryWithoutDoneList() {
        repository.addEntryToAgenda(agendaEntry);
        ArrayList<AgendaEntry> result = repository.getAgendaEntryWithoutDoneList(greenSpace.getEmailGSM());
        assertEquals(1, result.size());
    }

    @Test
    void testGetAgendaEntryListWithoutTeam() {

        var collaborators2= new ArrayList<Collaborator>();
        var skills = new ArrayList<Skill>();
        Team team2= new Team(collaborators2, skills, TeamStatusENUM.PENDING);

        repository.addEntryToAgenda(agendaEntry);

        agendaEntry.setAssociatedTeam(team2);
        ArrayList<AgendaEntry> result = repository.getAgendaEntryListWithoutTeam(greenSpace.getEmailGSM());
        assertEquals(1, result.size());
    }

    @Test
    void testGetAgendaEntryListWithoutCancelled() {
        repository.addEntryToAgenda(agendaEntry);
        ArrayList<AgendaEntry> result = repository.getAgendaEntryListWithoutCancelled(greenSpace.getEmailGSM());
        assertEquals(1, result.size());
    }

    @Test
    void testUpdateDate() {
        repository.addEntryToAgenda(agendaEntry);
        LocalDateTime newDate = LocalDateTime.now().plusDays(1);
        boolean result = repository.updateDate(agendaEntry, newDate);
        assertTrue(result);
        assertEquals(newDate, agendaEntry.getDate());
    }

    @Test
    void testUpdateDateEntryNotFound() {
        AgendaEntry nonExistentEntry = new AgendaEntry("Nonexistent Description", 2, greenSpace, UrgencyDegreeENUM.HIGH, EntryStatusENUM.PLANNED, LocalDateTime.now(), t1, vehicles);
        LocalDateTime newDate = LocalDateTime.now().plusDays(1);
        boolean result = repository.updateDate(nonExistentEntry, newDate);
        assertFalse(result);
    }

    @Test
    void testUpdateStatusEntryNotFound() {
        AgendaEntry nonExistentEntry = new AgendaEntry("Nonexistent Description", 2, greenSpace, UrgencyDegreeENUM.HIGH, EntryStatusENUM.PLANNED, LocalDateTime.now(), t1, vehicles);
        boolean result = repository.updateStatus(nonExistentEntry, EntryStatusENUM.DONE);
        assertFalse(result);
    }

    @Test
    void testUpdateStatus() {
        repository.addEntryToAgenda(agendaEntry);
        boolean result = repository.updateStatus(agendaEntry, EntryStatusENUM.DONE);
        assertTrue(result);
        assertEquals(EntryStatusENUM.DONE, agendaEntry.getEntryStatus());
    }

    @Test
    void testAssignTeam() {

        repository.addEntryToAgenda(agendaEntryOther);
        boolean result = repository.assignTeam(agendaEntryOther, t1);
        assertTrue(result);
        assertEquals(t1.getCollaboratorsNames(), agendaEntry.getAssociatedTeam().getCollaboratorsNames());
    }
    @Test
    void testAssignTeamEntryNotFound() {
        AgendaEntry nonExistentEntry = new AgendaEntry("Nonexistent Description", 2, greenSpace, UrgencyDegreeENUM.HIGH, EntryStatusENUM.PLANNED, LocalDateTime.now(), t1, vehicles);
        boolean result = repository.assignTeam(nonExistentEntry, t1);
        assertFalse(result);
    }

    @Test
    void testAssignTeamAlreadyAssigned() {
        repository.addEntryToAgenda(agendaEntry);
        boolean result = repository.assignTeam(agendaEntry, t1);
        assertFalse(result);
    }

    @Test
    void testGetAgendaEntryByDescriptionAndGreenspace() {
        repository.addEntryToAgenda(agendaEntry);
        AgendaEntry result = repository.getAgendaEntryByDescriptionAndGreenspace(agendaEntry.getDescription(), agendaEntry.getGreenSpace());
        assertNotNull(result);
        assertEquals(agendaEntry, result);
    }

    @Test
    void testGetAgendaEntryByDescriptionAndGreenspaceNotFound() {
        AgendaEntry result = repository.getAgendaEntryByDescriptionAndGreenspace("Nonexistent Description", greenSpace);
        assertNull(result);
    }

    @Test
    void testGetAgendaEntry() {
        repository.addEntryToAgenda(agendaEntry);
        AgendaEntry result = repository.getAgendaEntry("regar", greenSpace);
        assertNotNull(result);
        assertEquals(agendaEntry, result);
    }

    @Test
    void testGetAgendaEntryNotFound() {
        AgendaEntry result = repository.getAgendaEntry("Nonexistent Description", greenSpace);
        assertNull(result);
    }

    @Test
    void testGetAgendaEntryListWithoutTeamPlanned() {
        var collaborators2= new ArrayList<Collaborator>();
        var skills = new ArrayList<Skill>();
        Team team3= new Team(collaborators2, skills, TeamStatusENUM.PENDING);
        var plannedEntry = new AgendaEntry("Planned Description", 2, greenSpace, UrgencyDegreeENUM.HIGH, EntryStatusENUM.PLANNED, LocalDateTime.now(), team3, vehicles);
        repository.addEntryToAgenda(plannedEntry);
        ArrayList<AgendaEntry> result = repository.getAgendaEntryListWithoutTeam(greenSpace.getEmailGSM());
        assertTrue(result.contains(plannedEntry));
    }

    @Test
    void testGetAgendaEntryListWithoutTeamPostponed() {
        var collaborators2= new ArrayList<Collaborator>();
        var skills = new ArrayList<Skill>();
        Team team3= new Team(collaborators2, skills, TeamStatusENUM.PENDING);
        var doneEntry = new AgendaEntry("Postponed Description", 2, greenSpace, UrgencyDegreeENUM.HIGH, EntryStatusENUM.DONE, LocalDateTime.now(), team3, vehicles);
        var postponedEntry = new AgendaEntry("Postponed Description", 2, greenSpace, UrgencyDegreeENUM.HIGH, EntryStatusENUM.POSTPONED, LocalDateTime.now(), team3, vehicles);
        repository.addEntryToAgenda(postponedEntry);
        repository.addEntryToAgenda(doneEntry);
        ArrayList<AgendaEntry> result = repository.getAgendaEntryListWithoutTeam(greenSpace.getEmailGSM());
        assertFalse(result.contains(doneEntry));
        assertTrue(result.contains(postponedEntry));
    }

    @Test
    void testAssignVehicles() {
        repository.addEntryToAgenda(agendaEntry);
        ArrayList<Vehicle> newVehicles = new ArrayList<>();
        newVehicles.add(new Vehicle("Mercedes", "Class A", "87-UI-28", VehicleTypeENUM.LIGHT_VEHICLE,
                1415, 1200, 25000, "02-07-2020", "12-09-2018", 20000));
        boolean result = repository.assignVehicles(agendaEntry, newVehicles);
        assertTrue(result);
        assertEquals(newVehicles, agendaEntry.getAssociatedVehicles());
    }

    @Test
    void testAssignVehiclesEntryNotFound() {
        AgendaEntry nonExistentEntry = new AgendaEntry("Nonexistent Description", 2, greenSpace, UrgencyDegreeENUM.HIGH, EntryStatusENUM.PLANNED, LocalDateTime.now(), t1, vehicles);
        ArrayList<Vehicle> newVehicles = new ArrayList<>();
        newVehicles.add(new Vehicle("Mercedes", "Class A", "87-UI-28", VehicleTypeENUM.LIGHT_VEHICLE,
                1415, 1200, 25000, "02-07-2020", "12-09-2018", 20000));
        boolean result = repository.assignVehicles(nonExistentEntry, newVehicles);
        assertFalse(result);
    }

    @Test
    void testGetEntriesByCollaborator() {
        repository.addEntryToAgenda(agendaEntry);
        agendaEntry.setEntryStatus(EntryStatusENUM.PLANNED);
        agendaEntry.setAssociatedTeam(t1);
        String currentUserEmail = c1.getEmail();
        ArrayList<AgendaEntry> entries = repository.getEntriesByCollaborator(currentUserEmail);
        assertTrue(entries.contains(agendaEntry));
    }
    @Test
    void testGetEntriesByCollaboratorEntryStatusCancelled() {
        repository.addEntryToAgenda(agendaEntry);
        agendaEntry.setEntryStatus(EntryStatusENUM.CANCELLED);
        agendaEntry.setAssociatedTeam(t1);
        String currentUserEmail = c1.getEmail();
        ArrayList<AgendaEntry> entries = repository.getEntriesByCollaborator(currentUserEmail);
        assertFalse(entries.contains(agendaEntry));
    }


    @Test
    void testGetEntriesByCollaboratorEntryStatusDone() {
        repository.addEntryToAgenda(agendaEntry);

        agendaEntry.setEntryStatus(EntryStatusENUM.DONE);
        agendaEntry.setAssociatedTeam(t1);
        String currentUserEmail = c1.getEmail();
        ArrayList<AgendaEntry> entries = repository.getEntriesByCollaborator(currentUserEmail);
        assertFalse(entries.contains(agendaEntry));
    }

    @Test
    void testGetAgendaEntryList() {
        repository.addEntryToAgenda(agendaEntry);
        ArrayList<AgendaEntry> agendaEntryList = repository.getAgendaEntryList();
        assertNotNull(agendaEntryList);
        assertTrue(agendaEntryList.contains(agendaEntry));
    }


}
