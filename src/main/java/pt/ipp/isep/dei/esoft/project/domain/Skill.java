package pt.ipp.isep.dei.esoft.project.domain;

public class Skill {

    private final String skillName;

    public Skill(String skillName) {
        this.skillName = skillName;
    }

    public boolean equals(String skillName) {
        return this.skillName.equals(skillName);
    }

    public String getSkillName() {
        return skillName;
    }
}
