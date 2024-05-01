package pt.ipp.isep.dei.esoft.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorStatus;
import pt.ipp.isep.dei.esoft.project.repository.DocumentTypeRepository;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TeamRepositoryTest {

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
    private DocumentTypeRepository docType;
    private TeamRepository repo;
    @BeforeEach
    public void setUp() {
        repo = new TeamRepository();

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
        docType = DocumentTypeRepository.CITIZEN_CARD;

        c1 = new Collaborator("John Doe", 123456789, "01-01-1990", "01-01-2020", "123 Main St", 987699321, job, docType, CollaboratorStatus.DEACTIVATED, 451238965, "siii@euro.lol");
        c2 = new Collaborator("Alice Vieira", 928456689, "15-07-2006", "01-01-2020", "123 Main St", 983554321, job, docType, CollaboratorStatus.DEACTIVATED, 451238965, "siii@euro.lol");
        c3 = new Collaborator("Ambrosio Leite", 928945689, "19-09-1999", "01-01-2020", "123 Main St", 987554321, job, docType, CollaboratorStatus.DEACTIVATED, 451238965, "siii@euro.lol");
        c4 = new Collaborator("Elsa Freites", 988955779, "10-06-2005", "01-01-2020", "123 Main St", 987640321, job, docType, CollaboratorStatus.DEACTIVATED, 451238965, "siii@euro.lol");

        collaborators1.add(c1);
        collaborators1.add(c2);
        collaborators2.add(c3);
        collaborators2.add(c4);

        t1 = new Team(collaborators1, skills1);
        t2 = new Team(collaborators2, skills2);
        t3 = new Team(collaborators1, skills1);
    }

    @Test
    void testTeamAlreadyExists() {
        repo.registerTeam(t1);
        assertFalse(repo.registerTeam(t3));
    }

    @Test
    void testTeamDoesntExist() {
        repo.registerTeam(t1);
        assertTrue(repo.registerTeam(t2));
    }

    @Test
    void testTeamIsAdded(){
        repo.registerTeam(t1);
        assertEquals(1, repo.size());
    }


}