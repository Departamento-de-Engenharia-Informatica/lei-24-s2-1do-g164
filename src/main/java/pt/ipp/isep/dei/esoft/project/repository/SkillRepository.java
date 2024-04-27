package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SkillRepository - Repository for managing skills.
 */
public class SkillRepository implements Serializable {

    private final ArrayList<Skill> skillList = new ArrayList<>();
    /**
     * Retrieves the list of skills.
     *
     * @return the list of skills
     */
    public ArrayList<Skill> getSkillList() {
        return skillList;
    }
    /**
     * Registers a new skill.
     *
     * @param skillName the name of the skill to register
     * @return true if the skill is successfully registered, false otherwise
     */
    public boolean registerSkill(String skillName) {
        if (skillIsValid(skillName)) {
            Skill skill = new Skill(skillName);
            skillList.add(skill);
            return true;
        } else {
            return false;
        }
    }
    /**
     * Checks if a skill name is valid.
     *
     * @param skillName the skill name to validate
     * @return true if the skill name is valid, false otherwise
     */
    private boolean skillIsValid(String skillName) {
        return isValidSkillName(skillName) && skillNameIsUnique(skillName);
    }
    /**
     * Checks if a skill name is valid based on regex pattern.
     *
     * @param sName the skill name to validate
     * @return true if the skill name is valid, false otherwise
     */
    private static boolean isValidSkillName(String sName) {
        // Regex to check valid skill name (letters and spaces only).
        String regex = "^[a-zA-Z ]+$";
        Pattern p = Pattern.compile(regex);
        if (sName == null) {
            return false;
        }
        Matcher m = p.matcher(sName);
        if (!m.matches()){
            System.out.println("\nError: Inserted name \"" + sName + "\" is not valid");
        }
        return m.matches();
    }
    /**
     * Checks if a skill name is unique within the repository.
     *
     * @param sName the skill name to check
     * @return true if the skill name is unique, false otherwise
     */
    private boolean skillNameIsUnique(String sName) {
        for (Skill s : skillList) {
            if (s.equals(sName)) {
                System.out.println("\nError: Skill already exists \"" + sName + "\"");
                return false;
            }
        }
        return true;
    }}


