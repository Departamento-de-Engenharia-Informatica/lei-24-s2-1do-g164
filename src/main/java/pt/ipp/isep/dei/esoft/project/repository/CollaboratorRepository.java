package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
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
     * @param status           The status of the collaborator.
     * @param taxpayerNumber   The taxpayer number of the collaborator.
     * @param email            The email address of the collaborator.
     * @return {@code true} if the collaborator is successfully registered, {@code false} otherwise.
     */
    public boolean registerCollaborator(String name, int phone, String birthdate, String admissionDate, String address, int idDocumentNumber, Job job, DocumentTypeRepository idDocumentType, CollaboratorStatus status, int taxpayerNumber, String email) {
        Collaborator collaborator = new Collaborator(name, phone, birthdate, admissionDate, address, idDocumentNumber, job, idDocumentType, status, taxpayerNumber, email);
        if (collaboratorIsUnique(collaborator)) {
            collaboratorList.add(collaborator);
            return true;
        } else {
            return false;
        }
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
     * Retrieves deactivated collaborators who possess specific skills.
     *
     * @param skills The list of skills to filter collaborators.
     * @return List of deactivated collaborators with specified skills.
     */
    public ArrayList<Collaborator> getDeactivatedCollaboratorsBySkill(ArrayList<Skill> skills){
        ArrayList<Collaborator> selectedCollaborators = new ArrayList<>();
        for(Collaborator c : this.collaboratorList) {
            for(Skill s : skills) {
                if (c.getSkills().contains(s) && c.getStatus().equals(CollaboratorStatus.DEACTIVATED)) {
                    if (!selectedCollaborators.contains(c)) {
                        selectedCollaborators.add(c);
                    }
                }
            }
        }
        return selectedCollaborators;
    }
}
