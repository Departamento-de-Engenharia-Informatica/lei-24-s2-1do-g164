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


        if (!skillAlreadyExists(skillName)){

            Skill skill = new Skill(skillName);

            skillList.add(skill);

            return true;
        }else{
            return false;
        }
    }


//    private static boolean isValidSkillName(String sName)
//    {
//
//        // Regex to check valid username.
//        String regex = "/[a-zA-Z\\s]+/g";
//
//        // Compile the ReGex
//        Pattern p = Pattern.compile(regex);
//
//        // If the username is empty
//        // return false
//        if (sName == null) {
//            return false;
//        }
//
//        // Pattern class contains matcher() method
//        // to find matching between given username
//        // and regular expression.
//        Matcher m = p.matcher(sName);
//
//        // Return if the username
//        // matched the ReGex
//        return m.matches();
//    }

    private boolean skillAlreadyExists(String sName) {
        for (Skill s : skillList){
            if (s.equals(sName)){
                return true;
            }
        }
        return false;
    }

    public int size(){
        return this.skillList.size();
    }
}
