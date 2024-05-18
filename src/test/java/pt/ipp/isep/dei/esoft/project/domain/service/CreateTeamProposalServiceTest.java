package pt.ipp.isep.dei.esoft.project.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.repository.ENUM.CollaboratorStatus;
import pt.ipp.isep.dei.esoft.project.repository.ENUM.DocumentTypeRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

class CreateTeamProposalServiceTest {
    private CollaboratorRepository repo;
    private SkillRepository skillRepo;
    @BeforeEach
    public void setUp() {
        repo = Repositories.getInstance().getCollaboratorRepository();

        skillRepo = Repositories.getInstance().getSkillRepository();
        skillRepo.registerSkill("Plant Identification");
        skillRepo.registerSkill("Soil Management");
        skillRepo.registerSkill("Pruning and Trimming");
        skillRepo.registerSkill("Watering Techniques");

        skillRepo.registerSkill("Teste Um");
        skillRepo.registerSkill("Teste Dois");
        skillRepo.registerSkill("Teste Tres");
        skillRepo.registerSkill("Teste Quatro");

        skillRepo.registerSkill("Teste Throws");
        var skills = skillRepo.getSkillList();

        Job job = new Job("Designer");
        DocumentTypeRepository docType = DocumentTypeRepository.CITIZEN_CARD;

        repo.registerCollaborator("John Doe", 123456789, "01-01-1990", "01-01-2020", "123 Main St", 987699321, job, docType, CollaboratorStatus.DEACTIVATED, 451238965, "siii@euro.lol");
        repo.registerCollaborator("Alice Vieira", 928456689, "15-07-2006", "01-01-2020", "123 Main St", 983554321, job, docType, CollaboratorStatus.DEACTIVATED, 451238966, "siii@euro.lol");
        repo.registerCollaborator("Ambrosio Leite", 928945689, "19-09-1999", "01-01-2020", "123 Main St", 987554321, job, docType, CollaboratorStatus.DEACTIVATED, 451238967, "siii@euro.lol");
        repo.registerCollaborator("Elsa Freites", 988955779, "10-06-2005", "01-01-2020", "123 Main St", 987640321, job, docType, CollaboratorStatus.DEACTIVATED, 451238968, "siii@euro.lol");
        repo.registerCollaborator("Jacinto Miguel", 789789789, "10-06-2005", "01-01-2020", "123 Main St", 987622321, job, docType, CollaboratorStatus.DEACTIVATED, 451233968, "siii@euro.lol");

        repo.assignSkills(repo.getCollaboratorList().get(0), new ArrayList<>(Arrays.asList(skills.get(0), skills.get(1))));
        repo.assignSkills(repo.getCollaboratorList().get(1), new ArrayList<>(Arrays.asList(skills.get(1), skills.get(2))));
        repo.assignSkills(repo.getCollaboratorList().get(2), new ArrayList<>(Arrays.asList(skills.get(1), skills.get(2))));
        repo.assignSkills(repo.getCollaboratorList().get(3), new ArrayList<>(Arrays.asList(skills.get(2), skills.get(3))));
        repo.assignSkills(repo.getCollaboratorList().get(4), new ArrayList<>(Arrays.asList(skills.get(4), skills.get(5), skills.get(6), skills.get(7))));
    }

    @Test
    void testArrangeCollaboratorsBySkillReturnsCollaboratorsWithSkills() {
        CreateTeamProposalService svc = new CreateTeamProposalService();
        var skills = new ArrayList<Skill>();
        skills.add(skillRepo.getSkillList().get(0));
        skills.add(skillRepo.getSkillList().get(1));

        var expected = new ArrayList<Collaborator>();
        expected.add(repo.getCollaboratorList().get(0));
        expected.add(repo.getCollaboratorList().get(1));
        expected.add(repo.getCollaboratorList().get(2));

        var result = svc.arrangeCollaboratorsBySkill(skills);
        assertEquals(expected, result);
    }

    @Test
    void testArrangeCollaboratorsBySkillReturnsSortedCollaboratorsBySkills() {
        CreateTeamProposalService svc = new CreateTeamProposalService();
        var skills = new ArrayList<Skill>();
        skills.add(skillRepo.getSkillList().get(0));
        skills.add(skillRepo.getSkillList().get(1));

        skills.add(skillRepo.getSkillList().get(4));
        skills.add(skillRepo.getSkillList().get(5));
        skills.add(skillRepo.getSkillList().get(6));
        skills.add(skillRepo.getSkillList().get(7));

        var expected = new ArrayList<Collaborator>();
        expected.add(repo.getCollaboratorList().get(4));
        expected.add(repo.getCollaboratorList().get(0));
        expected.add(repo.getCollaboratorList().get(1));
        expected.add(repo.getCollaboratorList().get(2));

        var result = svc.arrangeCollaboratorsBySkill(skills);
        assertEquals(expected, result);
    }

    @Test
    void testArrangeCollaboratorsThrowsWhenNoCollaboratorsFoundForSkill() {
        CreateTeamProposalService svc = new CreateTeamProposalService();
        var skills = new ArrayList<Skill>();
        skills.add(skillRepo.getSkillList().get(8));

        assertThrows(InputMismatchException.class,
                () -> svc.arrangeCollaboratorsBySkill(skills));
    }

    @Test
    void testArrangeTeamReturnsTeamMembers() {
        CreateTeamProposalService svc = new CreateTeamProposalService();

        {
            var skills = new ArrayList<Skill>();
            skills.add(skillRepo.getSkillList().get(0));
            skills.add(skillRepo.getSkillList().get(1));

            var expected = new ArrayList<Collaborator>();
            expected.add(repo.getCollaboratorList().get(0));
            expected.add(repo.getCollaboratorList().get(1));
            expected.add(repo.getCollaboratorList().get(2));

            assertEquals(expected, svc.arrangeTeam(3,3, skills, svc.arrangeCollaboratorsBySkill(skills)));
        }

        {
            var skills = new ArrayList<Skill>();
            skills.add(skillRepo.getSkillList().get(0));
            skills.add(skillRepo.getSkillList().get(1));


            var expected = new ArrayList<Collaborator>();
            expected.add(repo.getCollaboratorList().get(0));

            assertEquals(expected, svc.arrangeTeam(4,1, skills, svc.arrangeCollaboratorsBySkill(skills)));
        }

        {
            var skills = new ArrayList<Skill>();
            skills.add(skillRepo.getSkillList().get(0));
            skills.add(skillRepo.getSkillList().get(1));
            skills.add(skillRepo.getSkillList().get(2));
            skills.add(skillRepo.getSkillList().get(3));


            var expected = new ArrayList<Collaborator>();
            expected.add(repo.getCollaboratorList().get(0));
            expected.add(repo.getCollaboratorList().get(1));
            expected.add(repo.getCollaboratorList().get(3));

            assertEquals(expected, svc.arrangeTeam(5,1, skills, svc.arrangeCollaboratorsBySkill(skills)));
        }
    }

    @Test
    void testArrangeTeamThrowsWhenNotEnoughCollaborators() {
        CreateTeamProposalService svc = new CreateTeamProposalService();
        var skills = new ArrayList<Skill>();
        skills.add(skillRepo.getSkillList().get(0));
        skills.add(skillRepo.getSkillList().get(1));

        assertThrows(InputMismatchException.class,
                () -> svc.arrangeTeam(10,5, skills, svc.arrangeCollaboratorsBySkill(skills)));
    }
}