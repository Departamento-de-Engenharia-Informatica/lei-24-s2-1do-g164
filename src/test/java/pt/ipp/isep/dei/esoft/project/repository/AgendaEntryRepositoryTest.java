package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AgendaEntryRepository;
import pt.ipp.isep.dei.esoft.project.repository.enums.*;

import java.time.LocalDate;
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

        c1 = new Collaborator("John Doe", 123456789, "01-01-1990", "01-01-2020", "123 Main St", 987699321, job, docType, CollaboratorStatusENUM.DEACTIVATED, 451238965, "siii@euro.lol");
        c2 = new Collaborator("Alice Vieira", 928456689, "15-07-2006", "01-01-2020", "123 Main St", 983554321, job, docType, CollaboratorStatusENUM.DEACTIVATED, 451238965, "siii@euro.lol");
        c3 = new Collaborator("Ambrosio Leite", 928945689, "19-09-1999", "01-01-2020", "123 Main St", 987554321, job, docType, CollaboratorStatusENUM.DEACTIVATED, 451238965, "siii@euro.lol");
        c4 = new Collaborator("Elsa Freites", 988955779, "10-06-2005", "01-01-2020", "123 Main St", 987640321, job, docType, CollaboratorStatusENUM.DEACTIVATED, 451238965, "siii@euro.lol");

        collaborators1.add(c1);
        collaborators1.add(c2);
        collaborators2.add(c3);
        collaborators2.add(c4);

        t1 = new Team(collaborators1, skills1, TeamStatusENUM.PENDING);
        t2 = new Team(collaborators2, skills2, TeamStatusENUM.PENDING);
        t3 = new Team(collaborators1, skills1,  TeamStatusENUM.PENDING);

        agendaEntry = new AgendaEntry("regar", 2, greenSpace, UrgencyDegreeENUM.HIGH, EntryStatusENUM.PLANNED, LocalDate.now(), t1, vehicles);
        agendaEntryOther = new AgendaEntry("plantar", 3, greenSpaceOther, UrgencyDegreeENUM.HIGH, EntryStatusENUM.PLANNED, LocalDate.now(), vehicles);
    }

    @Test
    void testAddEntryToAgenda() {
        boolean result = repository.addEntryToAgenda(agendaEntry);
        assertTrue(result);
        assertEquals(1, repository.getAgendaEntryWithoutDoneList(greenSpace.getEmailGSM()).size());
    }

    @Test
    void testAddEntryToAgenda_Duplicate() {
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
        LocalDate newDate = LocalDate.now().plusDays(1);
        boolean result = repository.updateDate(agendaEntry, newDate);
        assertTrue(result);
        assertEquals(newDate, agendaEntry.getDate());
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
    void testAssignTeamAlreadyAssigned() {
        repository.addEntryToAgenda(agendaEntry);
        agendaEntry.setAssociatedTeam(t1);
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
}
