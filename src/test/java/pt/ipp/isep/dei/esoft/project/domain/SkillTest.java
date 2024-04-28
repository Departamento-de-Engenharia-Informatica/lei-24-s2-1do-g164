package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SkillTest {

    @Test
    void ensureTwoSkillsWithSameNameEquals() {
        Skill s1 = new Skill("name");
        Skill s2 = new Skill("name");
        assertEquals(s1, s2);
    }
    @Test
    void ensureSameSkillEquals() {
        Skill s1 = new Skill("name");
        assertEquals(s1, s1);
    }

    @Test
    void ensureTwoSkillsWithDiferentNameNotEquals() {
        Skill s1 = new Skill("name");
        Skill s2 = new Skill("differentName");
        assertNotEquals(s1, s2);
    }

    @Test
    void ensureSkillDoesNotEqualNull() {
        Skill s1 = new Skill("name");
        assertNotEquals(s1, null);
    }

    @Test
    void ensureSkillDoesNotEqualOtherObject() {
        Skill s1 = new Skill("prunner");
        assertNotEquals(s1, new Object());
    }

    @Test
    void ensureHashCodeIsEqualForEqualObjects() {
        String skillName = "new skill";
        Skill s1 = new Skill(skillName);
        Skill s2 = new Skill(skillName);
        assertEquals(s1.hashCode(), s2.hashCode());
    }

    @Test
    void ensureHashCodeIsNotEqualForDifferentObjects() {

        Skill s1 = new Skill("aaa");
        Skill s2 = new Skill("bbb");
        assertNotEquals(s1.hashCode(), s2.hashCode());
    }

}
