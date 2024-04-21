package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.io.Serializable;
import java.util.ArrayList;

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
