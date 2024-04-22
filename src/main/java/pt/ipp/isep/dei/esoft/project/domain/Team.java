package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;

public class Team {
    private ArrayList<Skill> skills;
    private ArrayList<Collaborator> collaborators;

    public Team(ArrayList<Collaborator> collaborators, ArrayList<Skill> skills){
        this.collaborators = collaborators;
        this.skills = skills;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public ArrayList<Collaborator> getCollaborators(){
        return collaborators;
    }

    public void setCollaborators(ArrayList<Collaborator> collaborators){
        this.collaborators = collaborators;
    }
}
