package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CollaboratorRepository implements Serializable {
    private final ArrayList<Collaborator> collaboratorList = new ArrayList<>();

    public ArrayList<Collaborator> getCollaboratorList() {
        return collaboratorList;
    }

    public boolean registerCollaborator(String name, int phone, String birthdate, String admissionDate, String address, int idDocumentNumber, Job job, DocumentTypeRepository idDocumentType) {
        Collaborator collaborator = new Collaborator(name, phone, birthdate, admissionDate, address, idDocumentNumber, job, idDocumentType);
        if (!collaboratorAlreadyExists(collaborator)) {
            collaboratorList.add(collaborator);
            return true;
        } else {
            return false;
        }
    }

    public boolean registerCollaborator(String name, int phone, String birthdate, String admissionDate, String address,
                                        int idDocumentNumber, Job job, DocumentTypeRepository idDocumentType,
                                        ArrayList<Skill> skills) {
        Collaborator collaborator = new Collaborator(name, phone, birthdate, admissionDate, address, idDocumentNumber,
                job, idDocumentType, skills);

        if (!collaboratorAlreadyExists(collaborator)) {
            collaboratorList.add(collaborator);
            return true;
        } else {
            return false;
        }
    }

    public boolean collaboratorAlreadyExists(Collaborator collaborator) {
        for (Collaborator c : collaboratorList) {
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

    public int size() {
        return this.collaboratorList.size();
    }

    public boolean assignSkill(Collaborator c, ArrayList<Skill> skillsList) {
        boolean success = false;
        for (Skill s : skillsList) {
            if (!c.alreadyHasSkill(s)) {
                c.addSkill(s);
                success = true;
            }
        }
        return success;
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

//  public ArrayList<Collaborator> getCollaboratorsBySkills(List<Skill> skills){
//      ArrayList<Collaborator> selectedCollaborators = new ArrayList<>();
//      for(Collaborator c : collaboratorList) {
//          for (Skill s : skills) {
//              if (c.getSkills().contains(s) && !selectedCollaborators.contains(c)) {
//                  selectedCollaborators.add(c);
//              }
//          }
//      }
//  }

    public ArrayList<Collaborator> getDeactivatedCollaboratorsBySkill(Skill skill){
        ArrayList<Collaborator> selectedCollaborators = new ArrayList<>();
        for(Collaborator c : this.collaboratorList) {
            if (c.getSkills().contains(skill) && c.getStatus() == CollaboratorStatus.DEACTIVATED){
                selectedCollaborators.add(c);
            }
        }

        return selectedCollaborators;
    }


}
