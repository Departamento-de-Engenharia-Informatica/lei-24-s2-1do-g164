package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * Represents a skill.
 */
public class Skill {

    private final String skillName;

    /**
     * Constructs a new Skill with the specified skill name.
     *
     * @param skillName the name of the skill
     */
    public Skill(String skillName) {
        this.skillName = skillName;
    }

    /**
     * Checks if this skill is equal to the specified skill name.
     *
     * @param skillName the skill name to compare
     * @return true if the skill names are equal, false otherwise
     */
    public boolean equals(String skillName) {
        return this.skillName.equals(skillName);
    }

    /**
     * Checks if this skill is equal to another object.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Skill skill)) {
            return false;
        }
        return skillName.equals(skill.getSkillName());
    }

    /**
     * Checks if this skill is equal to another skill object.
     *
     * @param skill the skill object to compare
     * @return true if the skill objects are equal, false otherwise
     */
    public boolean equals(Skill skill) {
        return this.skillName.equals(skill.getSkillName());
    }

    /**
     * Gets the name of the skill.
     *
     * @return the name of the skill
     */
    public String getSkillName() {
        return skillName;
    }

    /**
     * Returns a string representation of the skill.
     *
     * @return the skill name
     */
    @Override
    public String toString() {
        return skillName;
    }

    /**
     * Returns a hash code value for the object. This method is typically used
     * in conjunction with the {@code equals} method to compare objects for equality.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(skillName);
    }

}
