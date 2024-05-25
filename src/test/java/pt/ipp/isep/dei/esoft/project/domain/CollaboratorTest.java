package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.repository.enums.CollaboratorStatusENUM;
import pt.ipp.isep.dei.esoft.project.repository.enums.DocumentTypeENUM;

import static org.junit.jupiter.api.Assertions.*;

public class CollaboratorTest {

    private Collaborator collaborator;
    private Skill skill1;
    private Skill skill2;
    private Job job;
    private DocumentTypeENUM docType;

    @BeforeEach
    public void setUp() {
        skill1 = new Skill("Design");
        skill2 = new Skill("Communication");
        job = new Job("Electrician");
        docType = DocumentTypeENUM.CITIZEN_CARD;

        collaborator = new Collaborator("John Doe", 123456789, "01-01-1990", "01-01-2020", "123 Main St", 987654321, job, docType, CollaboratorStatusENUM.DEACTIVATED, 451238965, "siii@euro.lol");
    }

    @Test
    public void testAlreadyHasSkill_WhenCollaboratorHasSkill() {
        collaborator.addSkill(skill1);
        assertTrue(collaborator.alreadyHasSkill(skill1));
    }

    @Test
    public void testAlreadyHasSkill_WhenCollaboratorDoesNotHaveSkill() {
        assertFalse(collaborator.alreadyHasSkill(skill2));
    }

    @Test
    public void testEquals_SameTaxpayerNumber() {
        Collaborator anotherCollaborator = new Collaborator("Jane Doe", 123456789, "02-02-1995", "01-01-2022", "456 Oak St", 123456789, job, docType, CollaboratorStatusENUM.DEACTIVATED, 451238965, "justinbuieber@popo.com");
        assertTrue(collaborator.equals(anotherCollaborator));
    }

    @Test
    public void testAddSkill() {
        collaborator.addSkill(skill1);
        collaborator.addSkill(skill2);
        assertEquals(2, collaborator.getSkills().size());
    }

    @Test
    public void testActivateCollaborator() {
        assertEquals(CollaboratorStatusENUM.DEACTIVATED, collaborator.getStatus());
        collaborator.activateCollaborator();
        assertEquals(CollaboratorStatusENUM.ACTIVATED, collaborator.getStatus());
    }

    @Test
    public void testToString() {
        String expected = "John Doe []";
        assertEquals(expected, collaborator.toString());
    }
}
