package pt.ipp.isep.dei.esoft.project.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterSkillController;
import static org.junit.jupiter.api.Assertions.*;
public class RegisterSkillControllerTest {

    @Test
    void registerRegularSkillTest(){
        RegisterSkillController controller = new RegisterSkillController();

        String skillName = "newSkill";
        String skillName2 = "New Skill";
        assertTrue(controller.registerSkill(skillName));
        assertTrue(controller.registerSkill(skillName2));
    }

    @Test
    void registerInvalidSkillTest() {
        RegisterSkillController controller = new RegisterSkillController();

        String skillName = "newSkill!";
        String skillName2 = "Skill 2";
        String skillName3 = "New_Skill";
        assertFalse(controller.registerSkill(skillName));
        assertFalse(controller.registerSkill(skillName2));
        assertFalse(controller.registerSkill(skillName3));
    }

    @Test
    void registerRepeatedSkillTest(){
        RegisterSkillController controller = new RegisterSkillController();

        String skillName = "newSkill";
        controller.registerSkill(skillName);
        assertFalse(controller.registerSkill(skillName));
    }

    @Test
    void registerEmptySkillTest(){
        RegisterSkillController controller = new RegisterSkillController();

        String skillName = "";
        assertFalse(controller.registerSkill(skillName));
    }
}
