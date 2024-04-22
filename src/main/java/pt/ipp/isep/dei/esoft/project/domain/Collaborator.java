package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.DocumentTypeRepository;

import java.util.ArrayList;

/**
 * Represents a collaborator in the project domain.
 */
public class Collaborator {
    private String name;
    private int phone;
    private String birthdate;
    private String admissionDate;
    private String address;
    private Job job;
    private DocumentTypeRepository idDocumentType;
    private int idDocumentNumber;
    private ArrayList<Skill> skills;

    /**
     * Constructs a new Collaborator with the specified details.
     *
     * @param name             The name of the collaborator.
     * @param phone            The phone number of the collaborator.
     * @param birthdate        The birthdate of the collaborator (in "DD-MM-YYYY" format).
     * @param admissionDate    The admission date of the collaborator (in "DD-MM-YYYY" format).
     * @param address          The address of the collaborator.
     * @param idDocumentNumber The ID document number of the collaborator.
     * @param job              The job of the collaborator.
     * @param idDocumentType   The ID document type repository of the collaborator.
     */
    public Collaborator(String name, int phone, String birthdate, String admissionDate, String address, int idDocumentNumber, Job job, DocumentTypeRepository idDocumentType) {
        this.name = name;
        this.phone = phone;
        this.birthdate = birthdate;
        this.admissionDate = admissionDate;
        this.address = address;
        this.idDocumentNumber = idDocumentNumber;
        this.job = job;
        this.idDocumentType = idDocumentType;
        this.skills = new ArrayList<>();
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
     * Retrieves the list of skills associated with the collaborator.
     *
     * @return The list of skills.
     */
    public ArrayList<Skill> getSkills() {
        return skills;
    }

    /**
     * Checks if the collaborator already possesses a specific skill.
     *
     * @param skill The skill to check.
     * @return {@code true} if the collaborator has the skill, {@code false} otherwise.
     */
    public boolean alreadyHasSkill(Skill skill) {
        for (Skill s : this.skills) {
            if (s.equals(skill)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Compares this collaborator with another object for equality.
     *
     * @param obj The object to compare with.
     * @return {@code true} if the objects are equal (based on phone number or name), {@code false} otherwise.
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Collaborator collaborator = (Collaborator) obj;
        return phone == collaborator.phone || name.equals(collaborator.name);
    }

    /**
     * Adds a skill to the list of skills associated with the collaborator.
     *
     * @param skill The skill to add.
     */
    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }
}
