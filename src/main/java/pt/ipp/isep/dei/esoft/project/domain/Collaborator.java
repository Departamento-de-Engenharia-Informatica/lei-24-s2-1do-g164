package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.CollaboratorStatus;
import pt.ipp.isep.dei.esoft.project.repository.DocumentTypeRepository;

import java.util.ArrayList;

/**
 * Represents a collaborator in the system.
 */
public class Collaborator {

    private String name;
    private int phone;
    private String email;
    private String birthdate;
    private String admissionDate;
    private String address;
    private Job job;
    private DocumentTypeRepository idDocumentType;
    private int idDocumentNumber;
    private int taxpayerNumber;
    private ArrayList<Skill> skills;
    private CollaboratorStatus status;

    /**
     * Constructs a new Collaborator with specified details.
     *
     * @param name             The name of the collaborator.
     * @param phone            The phone number of the collaborator.
     * @param birthdate        The birthdate of the collaborator (in "DD-MM-YYYY" format).
     * @param admissionDate    The admission date of the collaborator (in "DD-MM-YYYY" format).
     * @param address          The address of the collaborator.
     * @param idDocumentNumber The ID document number of the collaborator.
     * @param job              The job of the collaborator.
     * @param idDocumentType   The ID document type of the collaborator.
     * @param status           The status of the collaborator.
     * @param taxpayerNumber   The taxpayer number of the collaborator.
     * @param email            The email of the collaborator.
     */
    public Collaborator(String name, int phone, String birthdate, String admissionDate, String address, int idDocumentNumber, Job job, DocumentTypeRepository idDocumentType, CollaboratorStatus status, int taxpayerNumber, String email) {
        this.name = name;
        this.phone = phone;
        this.birthdate = birthdate;
        this.admissionDate = admissionDate;
        this.address = address;
        this.idDocumentNumber = idDocumentNumber;
        this.job = job;
        this.skills = new ArrayList<>();
        this.status = status;
        this.taxpayerNumber = taxpayerNumber;
        this.email = email;
    }

    /**
     * Retrieves the name of the collaborator.
     *
     * @return The name of the collaborator.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the phone number of the collaborator.
     *
     * @return The phone number of the collaborator.
     */
    public int getPhone() {
        return phone;
    }

    /**
     * Retrieves the birthdate of the collaborator.
     *
     * @return The birthdate of the collaborator.
     */
    public String getBirthdate() {
        return birthdate;
    }

    /**
     * Retrieves the admission date of the collaborator.
     *
     * @return The admission date of the collaborator.
     */
    public String getAdmissionDate() {
        return admissionDate;
    }

    /**
     * Retrieves the address of the collaborator.
     *
     * @return The address of the collaborator.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Retrieves the ID document number of the collaborator.
     *
     * @return The ID document number of the collaborator.
     */
    public int getIdDocumentNumber() {
        return idDocumentNumber;
    }

    /**
     * Retrieves the job of the collaborator.
     *
     * @return The job of the collaborator.
     */
    public Job getJob() {
        return job;
    }

    /**
     * Retrieves the skills of the collaborator.
     *
     * @return The list of skills of the collaborator.
     */
    public ArrayList<Skill> getSkills() {
        return skills;
    }

    /**
     * Checks if the collaborator already has a specific skill.
     *
     * @param skill The skill to check.
     * @return {@code true} if the collaborator has the skill, {@code false} otherwise.
     */
    public boolean alreadyHasSkill(Skill skill) {
        return skills.contains(skill);
    }

    /**
     * Compares this collaborator with another object for equality.
     *
     * @param c The object to compare with.
     * @return {@code true} if the collaborators have the same taxpayer number, {@code false} otherwise.
     */
    public boolean equals(Collaborator c) {
        return c.getTaxpayerNumber() == this.taxpayerNumber;
    }

    /**
     * Adds a new skill to the collaborator.
     *
     * @param s The skill to add.
     */
    public void addSkill(Skill s) {
        this.skills.add(s);
    }

    /**
     * Activates the collaborator.
     */
    public void activateCollaborator() {
        this.status = CollaboratorStatus.ACTIVATED;
    }

    /**
     * Retrieves the email of the collaborator.
     *
     * @return The email of the collaborator.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the ID document type of the collaborator.
     *
     * @return The ID document type of the collaborator.
     */
    public DocumentTypeRepository getIdDocumentType() {
        return idDocumentType;
    }

    /**
     * Retrieves the taxpayer number of the collaborator.
     *
     * @return The taxpayer number of the collaborator.
     */
    public int getTaxpayerNumber() {
        return taxpayerNumber;
    }

    /**
     * Retrieves the status of the collaborator.
     *
     * @return The status of the collaborator.
     */
    public CollaboratorStatus getStatus() {
        return status;
    }

    /**
     * Generates a string representation of the collaborator.
     *
     * @return A string containing the name of the collaborator and their skills.
     */
    @Override
    public String toString() {
        return name + " " + skills;
    }
}
