package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SkillRepository implements Serializable {

    private final ArrayList<Skill> skillList = new ArrayList<>();

    public ArrayList<Skill> getSkillList(){
        return skillList;
    }

    public boolean registerSkill(String skillName){


        if (skillisValid(skillName)){

            Skill skill = new Skill(skillName);

            skillList.add(skill);

            return true;
        }else{
            return false;
        }
    }

    private  boolean skillisValid(String skillName) {
        System.out.println(isValidSkillName(skillName));
        System.out.println(skillNameIsUnique(skillName));
        return isValidSkillName(skillName) && skillNameIsUnique(skillName);
    }


    private static boolean isValidSkillName(String sName) {

        // Regex to check valid username.
        String regex = "^[a-zA-Z ]+$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);


        if (sName == null) {
            return false;
        }


        Matcher m = p.matcher(sName);

        return m.matches();
    }

    private boolean skillNameIsUnique(String sName) {
        for (Skill s : skillList){
            if (s.equals(sName)){
                return false;
            }
        }
        return true;
    }

    public int size(){
        return this.skillList.size();
    }
}
