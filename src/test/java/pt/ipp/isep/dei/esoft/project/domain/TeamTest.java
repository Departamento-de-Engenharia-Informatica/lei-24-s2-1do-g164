package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.repository.enums.DocumentTypeENUM;
import pt.ipp.isep.dei.esoft.project.repository.enums.TeamStatusENUM;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

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
    private int max;
    private int min;
    private Team t1;
    private Team t2;
    private Team t3;
    private Job job;
    private DocumentTypeENUM docType;


    @BeforeEach
    public void setUp() {
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
        c2 = new Collaborator("Alice Vieira", 928456689, "15-07-2006", "01-01-2020", "123 Main St", 983554321, job, docType, 451238965, "siii@euro.lol");
        c3 = new Collaborator("Ambrosio Leite", 928945689, "19-09-1999", "01-01-2020", "123 Main St", 987554321, job, docType,  451238965, "siii@euro.lol");
        c4 = new Collaborator("Elsa Freites", 988955779, "10-06-2005", "01-01-2020", "123 Main St", 987640321, job, docType,  451238965, "siii@euro.lol");

        collaborators1.add(c1);
        collaborators1.add(c2);
        collaborators2.add(c3);
        collaborators2.add(c4);

        t1 = new Team(collaborators1, skills1, TeamStatusENUM.PENDING);
        t2 = new Team(collaborators2, skills2,  TeamStatusENUM.PENDING);
        t3=new Team(collaborators1, skills1, TeamStatusENUM.PENDING);
    }


    @Test
    void ensureTeamsEquals() {
        assertEquals(t1, t1);
    }

    @Test
    void ensureTwoTeamsWithDiferentCollaboratorsNotEqual() {
        assertNotEquals(t1, t2);
    }
    @Test
    void ensureTwoTeamsWithDiferentSkillsNotEqual() {
        assertNotEquals(t1, t2);
    }
    @Test
    void ensureTeamDoesNotEqualNull() {
        assertNotEquals(t1, null);
    }

    @Test
    void ensureHashCodeIsEqualForEqualObjects() {
        assertEquals(t1.hashCode(), t1.hashCode());
    }
    @Test
    void ensureHashCodeIsNotEqualForDifferentObjects() {
        assertNotEquals(t1.hashCode(), t2.hashCode());
    }

    @Test
    void ensureGetSkillsReturnsCorrectSkills() {
        ArrayList<Skill> result = t1.getSkills();
        assertEquals(skills1, result);
    }
    @Test
    void ensureGetCollaboratorsReturnsCorrectCollaborators() {
        ArrayList<Collaborator> result = t1.getCollaborators();
        assertEquals(collaborators1, result);
    }

    @Test
    void ensureSetSkillsUpdatesSkillsCorrectly() {
        ArrayList<Skill> newSkills = new ArrayList<>(Arrays.asList(new Skill("NewSkill1"), new Skill("NewSkill2")));
        t1.setSkills(newSkills);
        assertEquals(newSkills, t1.getSkills());
    }
    @Test
    void ensureGetStatusReturnsCorrectStatus() {
        TeamStatusENUM result = t1.getStatus();
        assertEquals(TeamStatusENUM.PENDING, result);
    }

    @Test
    void ensureSetStatusUpdatesStatusCorrectly() {
        t1.setStatus(TeamStatusENUM.ACCEPTED);
        assertEquals(TeamStatusENUM.ACCEPTED, t1.getStatus());
    }

    @Test
    void ensureToStringReturnsCorrectStringForNonEmptyTeam() {
        String result = t1.toString();
        assertTrue(result.contains("Team: "));
        assertTrue(result.contains("John Doe"));
        assertTrue(result.contains("Alice Vieira"));
    }

    @Test
    void ensureToStringReturnsCorrectStringForEmptyTeam() {
        Team emptyTeam = new Team(new ArrayList<>(), new ArrayList<>(), TeamStatusENUM.PENDING);
        String result = emptyTeam.toString();
        assertEquals("No team", result);
    }

}