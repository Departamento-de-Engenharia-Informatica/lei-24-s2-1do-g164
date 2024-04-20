package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.DocumentTypeRepository;

import java.util.ArrayList;

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
    public Collaborator(String name, int phone, String birthdate, String admissionDate, String address, int idDocumentNumber, Job job, DocumentTypeRepository idDocumentType) {
        this.name = name;
        this.phone = phone;
        this.birthdate = birthdate;
        this.admissionDate = admissionDate;
        this.address = address;
        this.idDocumentNumber = idDocumentNumber;
        this.job = job;
        this.skills = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public String getAddress() {
        return address;
    }

    public int getIdDocumentNumber() {
        return idDocumentNumber;
    }

    public Job getJob() {
        return job;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public boolean alreadyHasSkill(Skill skill){
        for(Skill s : this.skills){
            if(s.equals(skill)){
                return true;
            }
        }
        return false;
    }
    public boolean equals(Collaborator c){
        return c.getPhone() == this.phone || c.getName().equals(this.name);
    }

    public void addSkill(Skill s){
        this.skills.add(s);
    }
}

