package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.repository.AgendaEntryRepository;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;
import pt.ipp.isep.dei.esoft.project.repository.enums.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AgendaEntryTest {
    private AgendaEntryRepository repository;
    private GreenSpace greenSpace;
    private AgendaEntry agendaEntry;

    private GreenSpace greenSpaceOther;

    private AgendaEntry agendaEntryOther;
    private ArrayList<Vehicle> vehicles;
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

        c1 = new Collaborator("John Doe", 123456789, "01-01-1990", "01-01-2020", "123 Main St", 987699321, job, docType,  451238965, "siii@euro.lol");
        c2 = new Collaborator("Alice Vieira", 928456689, "15-07-2006", "01-01-2020", "123 Main St", 983554321, job, docType,  451238965, "siii@euro.lol");
        c3 = new Collaborator("Ambrosio Leite", 928945689, "19-09-1999", "01-01-2020", "123 Main St", 987554321, job, docType,  451238965, "siii@euro.lol");
        c4 = new Collaborator("Elsa Freites", 988955779, "10-06-2005", "01-01-2020", "123 Main St", 987640321, job, docType, 451238965, "siii@euro.lol");

        collaborators1.add(c1);
        collaborators1.add(c2);
        collaborators2.add(c3);
        collaborators2.add(c4);

        t1 = new Team(collaborators1, skills1, TeamStatusENUM.PENDING);
        t2 = new Team(collaborators2, skills2, TeamStatusENUM.PENDING);
        t3 = new Team(collaborators1, skills1, TeamStatusENUM.PENDING);

        agendaEntry = new AgendaEntry("regar", 2, greenSpace, UrgencyDegreeENUM.HIGH, EntryStatusENUM.PLANNED, LocalDate.now(), t1, vehicles);
        agendaEntryOther = new AgendaEntry("plantar", 3, greenSpaceOther, UrgencyDegreeENUM.HIGH, EntryStatusENUM.PLANNED, LocalDate.now(), vehicles);

    }

        @Test
        public void testGetAssociatedTeam() {
            assertEquals(t1, agendaEntry.getAssociatedTeam());
        }

        @Test
        public void testSetAssociatedTeam() {
            agendaEntry.setAssociatedTeam(t2);
            assertEquals(t2, agendaEntry.getAssociatedTeam());
        }

        @Test
        public void testAgendaEntryConstructorWithTeam() {
            assertNotNull(agendaEntry);
            assertEquals("regar", agendaEntry.getDescription());
            assertEquals(2, agendaEntry.getExpectedDuration());
            assertEquals(greenSpace, agendaEntry.getGreenSpace());
            assertEquals(UrgencyDegreeENUM.HIGH, agendaEntry.getUrgencyDegree());
            assertEquals(EntryStatusENUM.PLANNED, agendaEntry.getEntryStatus());
            assertEquals(LocalDate.now(), agendaEntry.getDate());
            assertEquals(t1, agendaEntry.getAssociatedTeam());
            assertEquals(vehicles, agendaEntry.getAssociatedVehicles());
        }

        @Test
        public void testAgendaEntryConstructorWithoutTeam() {
            assertNotNull(agendaEntryOther);
            assertEquals("plantar", agendaEntryOther.getDescription());
            assertEquals(3, agendaEntryOther.getExpectedDuration());
            assertEquals(greenSpaceOther, agendaEntryOther.getGreenSpace());
            assertEquals(UrgencyDegreeENUM.HIGH, agendaEntryOther.getUrgencyDegree());
            assertEquals(EntryStatusENUM.PLANNED, agendaEntryOther.getEntryStatus());
            assertEquals(LocalDate.now(), agendaEntryOther.getDate());
            assertEquals(vehicles, agendaEntryOther.getAssociatedVehicles());
        }

    @Test
    public void testRepositoryAdd() {
        repository.addEntryToAgenda(agendaEntry);
        assertTrue(repository.getAgendaEntryList().contains(agendaEntry));
    }


    @Test
        public void testRepositoryGetAll() {
            repository.addEntryToAgenda(agendaEntry);
            repository.addEntryToAgenda(agendaEntryOther);
            assertEquals(2, repository.getAgendaEntryList().size());
            assertTrue(repository.getAgendaEntryList().contains(agendaEntry));
            assertTrue(repository.getAgendaEntryList().contains(agendaEntryOther));
        }

    @Test
        public void testGetDate() {
        LocalDate expectedDate = LocalDate.of(2022, 6, 7);
        agendaEntry.setDate(expectedDate);
        assertEquals(expectedDate, agendaEntry.getDate());
    }

    @Test
    public void testSetDate() {
        LocalDate newDate = LocalDate.of(2024, 12, 25);
        agendaEntry.setDate(newDate);
        assertEquals(newDate, agendaEntry.getDate());
    }

    @Test
    public void testGetAssociatedVehicles() {
        assertEquals(vehicles, agendaEntry.getAssociatedVehicles());
    }

    @Test
    public void testSetAssociatedVehicles() {
        ArrayList<Vehicle> newVehicles = new ArrayList<>();
        newVehicles.add(new Vehicle("Mercedes", "Class A", "87-UI-28", VehicleTypeENUM.LIGHT_VEHICLE,
                1415, 1200, 25000, "02-07-2020", "12-09-2018", 20000));
        agendaEntry.setAssociatedVehicles(newVehicles);
        assertEquals(newVehicles, agendaEntry.getAssociatedVehicles());
    }



}
