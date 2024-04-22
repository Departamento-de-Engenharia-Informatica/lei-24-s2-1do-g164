package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CollaboratorRepository implements Serializable {
    private final ArrayList<Collaborator> collaboratorList = new ArrayList<>();

    public ArrayList<Collaborator> getCollaboratorList() {
        return collaboratorList;
    }

    public boolean registerCollaborator(String name, int phone, String birthdate, String admissionDate, String address, int idDocumentNumber, Job job, DocumentTypeRepository idDocumentType) {
        Collaborator collaborator = new Collaborator(name, phone, birthdate, admissionDate, address, idDocumentNumber, job, idDocumentType);
        if (collaboratorIsUnique(collaborator) && isValidName(name) && isValidPhone(phone) && isValidDateFormat(admissionDate) && isValidDateFormat(birthdate)) {
            collaboratorList.add(collaborator);
            return true;
        } else {
            return false;
        }
    }

    private static boolean isValidName(String name) {
        String regex = "^[a-zA-Z ]+$";
        Pattern p = Pattern.compile(regex);

        if (name == null) {
            return false;
        }

        Matcher m = p.matcher(name);

        return m.matches();
    }

    public static boolean isValidPhone(int phone) {
        String phoneStr = Integer.toString(phone);
        if (phoneStr.length() == 9) {
            return isNumeric(phoneStr);
        } else {
            return false;
        }
    }

    private static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidDateFormat(String dateString) {
        if (dateString.length() != 10) {
            return false;
        }

        String dayStr = dateString.substring(0, 2);
        String monthStr = dateString.substring(3, 5);
        String yearStr = dateString.substring(6);

        if (!isNumeric(dayStr) || !isNumeric(monthStr) || !isNumeric(yearStr)) {
            return false;
        }

        int day = Integer.parseInt(dayStr);
        int month = Integer.parseInt(monthStr);
        int year = Integer.parseInt(yearStr);

        if (month < 1 || month > 12 || day < 1 || day > 31) {
            return false;
        }

        return dateString.charAt(2) == '-' && dateString.charAt(5) == '-';
    }

    public boolean collaboratorIsUnique(Collaborator collaborator) {
        for (Collaborator c : collaboratorList) {
            if (c.equals(collaborator)) {
                return false;
            }
        }
        return true;
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
}
