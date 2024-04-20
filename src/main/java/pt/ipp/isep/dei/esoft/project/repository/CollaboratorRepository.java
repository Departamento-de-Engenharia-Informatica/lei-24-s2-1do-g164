package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


public class CollaboratorRepository implements Serializable {
    private final ArrayList<Collaborator> collaboratorList = new ArrayList<>();
    public ArrayList<Collaborator> getCollaboratorList() {
        return collaboratorList;
    }

    public boolean registerCollaborator(String name, int phone, String birthdate, String admissionDate, String address, int idDocumentNumber, String job, int idDocumentType){
        Collaborator collaborator = new Collaborator(name, phone, birthdate, admissionDate, address, idDocumentNumber, new Job(job), getDocTypesList().get(idDocumentType));
        if(!collaboratorAlreadyExists(collaborator)) {
            collaboratorList.add(collaborator);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean collaboratorAlreadyExists(Collaborator collaborator){
        for(Collaborator c : collaboratorList) {
            if (c.equals(collaborator)) {
                return true;
            }
        }
        return false;
    }


    //public Collaborator getCollaboratorByName(String name){
      //  for(Collaborator c : collaboratorList){
        //    if(c.getName().equals(name)){
          //      return c;
            //}
        //}
        //return null;
    //}

    public int size(){
        return this.collaboratorList.size();
    }

    public boolean assignSKill(Collaborator c, Skill s){
        if(c.alreadyHasSkill(s)){
            return false;
        }
        else{
            c.addSkill(s);
            return true;
        }
    }
  public ArrayList<Collaborator> getCollaboratorsBySkills(Collaborator collaborator, Skill skill) {
      ArrayList<Collaborator> collaboratorsWithSkill = new ArrayList<>();
      for (Collaborator c : collaboratorList) {
            if (collaborator.alreadyHasSkill(skill)) {
                collaboratorsWithSkill.add(collaborator);
            }
        }
        return collaboratorsWithSkill;
  }

    public ArrayList<DocumentTypeRepository> getDocTypesList(){
        return new ArrayList<>(Arrays.asList(DocumentTypeRepository.values()));
    }
}
