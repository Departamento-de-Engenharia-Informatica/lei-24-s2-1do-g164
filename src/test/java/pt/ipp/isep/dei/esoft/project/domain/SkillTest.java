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
    public void testGetSkillName() {
        Skill s1 = new Skill("name");
        assertEquals("name", s1.getSkillName());
    }

    @Test
    public void testEquals_SkillObject_SameName() {
        Skill s1 = new Skill("name");
        Skill s2 = new Skill("name");

        assertTrue(s1.equals(s2));
    }

    @Test
    public void testEquals_SkillObject_DifferentName() {
       Skill skill1 = new Skill("Design");
        Skill skill2 = new Skill("Communication");
        assertFalse(skill1.equals(skill2));
    }

    @Test
    public void testEquals_SkillName_SameName() {

        Skill s1 = new Skill("name");
        assertTrue(s1.equals("name"));
    }

    @Test
    public void testEquals_SkillName_DifferentName() {

        Skill s1 = new Skill("Design");
        Skill s2 = new Skill("Communication");
        assertFalse(s1.equals("Communication"));
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
    @Test
    public void testToString() {
        Skill s1 = new Skill("aaa");
        assertEquals("aaa", s1.toString());
    }
}
