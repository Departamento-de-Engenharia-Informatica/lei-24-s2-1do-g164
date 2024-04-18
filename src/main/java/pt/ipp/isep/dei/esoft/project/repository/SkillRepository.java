package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.io.Serializable;
import java.util.ArrayList;

public class SkillRepository implements Serializable {

    private final ArrayList<Skill> skillList = new ArrayList<>();

    public ArrayList<Skill> getSkillList(){
        return skillList;
    }

    public boolean registerSkill(Skill skill){
        if (validateSkill(skill)){

            skillList.add(skill);

            return true;
        }else{
            return false;
        }
    }


    private boolean validateSkill(Skill skill) {
        for (Skill s : skillList){
            if (s.equals(skill)){
                return true;
            }
        }
        return false;
    }

    public int size(){
        return this.skillList.size();
    }
}
