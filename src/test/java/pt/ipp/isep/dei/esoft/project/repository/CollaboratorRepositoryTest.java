package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.sql.SQLOutput;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CollaboratorRepositoryTest {
    private CollaboratorRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new CollaboratorRepository();
    }

    @Test
    public void testRegisterCollaborator() {
        assertTrue(repository.registerCollaborator("John Doe", 123456789, "01-01-1990", "01-01-2020", "123 Main St", 123456789, new Job("gardener"), DocumentTypeRepository.ID_CARD, CollaboratorStatus.DEACTIVATED));
        assertEquals(1, repository.size());
    }

    @Test
    public void testRegisterDuplicateCollaborator() {
        repository.registerCollaborator("John Doe", 123456789, "01-01-1990", "01-01-2020", "123 Main St", 123456789, new Job("gardener"), DocumentTypeRepository.ID_CARD, CollaboratorStatus.DEACTIVATED);
        assertFalse(repository.registerCollaborator("John Doe", 987654321, "01-01-1995", "01-01-2022", "456 Oak St", 987654321, new Job("gardener"), DocumentTypeRepository.PASSPORT, CollaboratorStatus.DEACTIVATED));
        assertEquals(1, repository.size());
    }

    @Test
    public void testAssignSkills() {
        Collaborator collaborator = new Collaborator("John Doe", 123456789, "01-01-1990", "01-01-2020", "123 Main St", 123456789, new Job("gardener"), DocumentTypeRepository.ID_CARD, CollaboratorStatus.ACTIVATED);
        Skill skill1 = new Skill("Java");
        Skill skill2 = new Skill("Python");
        ArrayList<Skill> skillsList = new ArrayList<>();
        skillsList.add(skill1);
        skillsList.add(skill2);

        assertTrue(repository.assignSkills(collaborator, skillsList));
        assertEquals(2, collaborator.getSkills().size());
    }



    @Test
    public void testGetDeactivatedCollaboratorsBySkill(){
        repository.registerCollaborator("John Doe", 123456789, "01-01-1990", "01-01-2020", "123 Main St", 123456789, new Job("gardener"), DocumentTypeRepository.ID_CARD, CollaboratorStatus.ACTIVATED);
        repository.registerCollaborator("Marcus Doe", 123123123, "01-01-1980", "01-02-2024", "183 Main St", 1212214789, new Job("designer"), DocumentTypeRepository.ID_CARD, CollaboratorStatus.DEACTIVATED);
        Skill skill1 = new Skill("Java");
        Skill skill2 = new Skill("Python");
        ArrayList<Skill> skillsList = new ArrayList<>();
        skillsList.add(skill1);
        skillsList.add(skill2);
        repository.assignSkills(repository.getCollaboratorList().get(0), skillsList);
        repository.assignSkills(repository.getCollaboratorList().get(1), skillsList);
        assertEquals(1, repository.getDeactivatedCollaboratorsBySkill(skillsList).size());

    }
}