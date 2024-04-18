package pt.ipp.isep.dei.esoft.project.domain;

public class Skill {

    private final String skillName;

    public Skill(String skillName) {
        this.skillName = skillName;
    }

    public boolean equals(Skill o) {
        return skillName.equals(o.getSkillName());
    }

    public String getSkillName() {
        return skillName;
    }
}
