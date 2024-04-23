package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.CollaboratorStatus;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Repository class for managing collaborators.
 */
public class CollaboratorRepository implements Serializable {

    private final ArrayList<Collaborator> collaboratorList = new ArrayList<>();

    /**
     * Retrieves the list of collaborators in the repository.
     *
     * @return The list of collaborators.
     */
    public ArrayList<Collaborator> getCollaboratorList() {
        return collaboratorList;
    }

    /**
     * Registers a new collaborator with the provided details.
     *
     * @param name             The name of the collaborator.
     * @param phone            The phone number of the collaborator.
     * @param birthdate        The birthdate of the collaborator (in "DD-MM-YYYY" format).
     * @param admissionDate    The admission date of the collaborator (in "DD-MM-YYYY" format).
     * @param address          The address of the collaborator.
     * @param idDocumentNumber The ID document number of the collaborator.
     * @param job              The job of the collaborator.
     * @param idDocumentType   The ID document type repository of the collaborator.
     * @return {@code true} if the collaborator is successfully registered, {@code false} otherwise.
     */
    public boolean registerCollaborator(String name, int phone, String birthdate, String admissionDate, String address, int idDocumentNumber, Job job, DocumentTypeRepository idDocumentType) {
        Collaborator collaborator = new Collaborator(name, phone, birthdate, admissionDate, address, idDocumentNumber, job, idDocumentType);
        if (collaboratorIsUnique(collaborator) && isValidName(name) && isValidPhone(phone) && isValidDateFormat(admissionDate) && isValidDateFormat(birthdate)) {
            collaboratorList.add(collaborator);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Validates if the provided name is valid (contains only alphabetic characters and spaces).
     *
     * @param name The name to validate.
     * @return {@code true} if the name is valid, {@code false} otherwise.
     */
    private static boolean isValidName(String name) {
        String regex = "^[a-zA-Z ]+$";
        Pattern p = Pattern.compile(regex);

        if (name == null) {
            return false;
        }

        Matcher m = p.matcher(name);

        return m.matches();
    }

    /**
     * Validates if the provided phone number is valid (exactly 9 digits).
     *
     * @param phone The phone number to validate.
     * @return {@code true} if the phone number is valid, {@code false} otherwise.
     */
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

    /**
     * Validates if the provided date string has a valid "DD-MM-YYYY" format.
     *
     * @param dateString The date string to validate.
     * @return {@code true} if the date format is valid, {@code false} otherwise.
     */
    public static boolean isValidDateFormat(String dateString) {
        // Regex to check valid skill name (letters and spaces only).
        String regex = "^([0-2][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}$\n";
        Pattern p = Pattern.compile(regex);
        if (dateString == null) {
            return false;
        }
        Matcher m = p.matcher(dateString);
        return m.matches();
    }

    /**
     * Checks if the given collaborator is unique (not already registered).
     *
     * @param collaborator The collaborator to check.
     * @return {@code true} if the collaborator is unique, {@code false} if it already exists.
     */
    public boolean collaboratorIsUnique(Collaborator collaborator) {
        for (Collaborator c : collaboratorList) {
            if (c.equals(collaborator)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retrieves the number of collaborators in the repository.
     *
     * @return The number of collaborators.
     */
    public int size() {
        return this.collaboratorList.size();
    }

    /**
     * Assigns skills to a collaborator.
     *
     * @param c          The collaborator to assign skills to.
     * @param skillsList The list of skills to assign.
     * @return {@code true} if at least one skill was assigned successfully, {@code false} otherwise.
     */
    public boolean assignSkills(Collaborator c, ArrayList<Skill> skillsList) {
        boolean success = false;
        for (Skill s : skillsList) {
            if (!c.alreadyHasSkill(s)) {
                c.addSkill(s);
                success = true;
            }
        }
        return success;
    }

    /**
     * Retrieves collaborators with a specific skill.
     *
     * @param collaborator The collaborator to check for the skill.
     * @param skill        The skill to search for.
     * @return The list of collaborators who have the specified skill.
     */
    public ArrayList<Collaborator> getCollaboratorsBySkills(Collaborator collaborator, Skill skill) {
        ArrayList<Collaborator> collaboratorsWithSkill = new ArrayList<>();
        for (Collaborator c : collaboratorList) {
            if (c.alreadyHasSkill(skill)) {
                collaboratorsWithSkill.add(collaborator);
            }
        }
        return collaboratorsWithSkill;
    }

    public ArrayList<Collaborator> getDeactivatedCollaboratorsBySkill(ArrayList<Skill> skills){
        ArrayList<Collaborator> selectedCollaborators = new ArrayList<>();
        for(Collaborator c : this.collaboratorList) {
            for(Skill s : skills) {
                if (c.getSkills().contains(s) && c.getStatus() == CollaboratorStatus.DEACTIVATED) {
                    selectedCollaborators.add(c);
                }
            }
        }

        return selectedCollaborators;
    }
}
