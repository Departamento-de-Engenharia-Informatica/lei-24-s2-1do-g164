package pt.ipp.isep.dei.esoft.project.domain;

/**
 * Skill - Represents a skill in the system.
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
     * Checks if this Skill is equal to another skill name.
     *
     * @param skillName the skill name to compare
     * @return true if the skill names are equal, false otherwise
     */
    public boolean equals(String skillName) {
        return this.skillName.equals(skillName);
    }

    /**
     * Gets the name of the skill.
     *
     * @return the skill name
     */
    public String getSkillName() {
        return skillName;
    }
}
