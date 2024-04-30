package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class SkillRepositoryTest {

    @Test
    void ensureNewSkillSuccessfullyAdded() {
        SkillRepository skillRepository = new SkillRepository();
        String skillName = "newSkill";
        skillRepository.registerSkill(skillName);
    }

    @Test
    void ensureSkillNameIsValidated() {
        SkillRepository skillRepository = new SkillRepository();
        String sName1 = "newSkill";
        String sName2 = "New Skill";
        String sName3 = "newSkill 2";
        String sName4 = "NewSkill!";
        String sName5 = "New Skill !";
        String sName6 = "New Skill2!";
        assertTrue(skillRepository.registerSkill(sName1));
        assertTrue(skillRepository.registerSkill(sName2));
        assertFalse(skillRepository.registerSkill(sName3));
        assertFalse(skillRepository.registerSkill(sName4));
        assertFalse(skillRepository.registerSkill(sName5));
        assertFalse(skillRepository.registerSkill(sName6));
    }

    @Test
    void ensureRegisteringDuplicateSkillsFails() {
        SkillRepository skillRepository = new SkillRepository();
        String skillName = "newSkill";
        String skillName2 = "newSkill";
        skillRepository.registerSkill(skillName);
        assertFalse(skillRepository.registerSkill(skillName2));
    }

    @Test
    void ensureAddingDifferentSkillsSuccess() {
        //Arrange
        SkillRepository skillRepository = new SkillRepository();
        String skill1 = "Skill a";
        String skill2 = "Skill b";
        //Add the first task
        skillRepository.registerSkill(skill1);

        //Act
        boolean result = skillRepository.registerSkill(skill2);

        //Assert
        assertTrue(result);
    }


    @Test
    void ensureGetSkillListReturnsTheCorrectList() {
        //Arrange
        SkillRepository skillRepository = new SkillRepository();
        String skillName = "SkillName";
        Skill skill = new Skill(skillName);
        skillRepository.registerSkill(skillName);
        int expectedSize = 1;

        //Act
        int size = skillRepository.getSkillList().size();

        //Assert
        assertEquals(expectedSize, size);
        assertEquals(skill, skillRepository.getSkillList().get(size - 1));
    }

}
