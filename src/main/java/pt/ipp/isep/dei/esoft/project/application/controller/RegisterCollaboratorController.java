package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Arrays;

public class RegisterCollaboratorController {
    private CollaboratorRepository collaboratorRepository;
    private JobRepository jobRepository;
    public RegisterCollaboratorController(){
        getCollaboratorRepository();
        getJobRepository();
    }

    public boolean registerCollaborator(String name, int phone, String birthdate, String admissionDate, String address, int idDocumentNumber, Job job, DocumentTypeRepository idDocumentType){
        return collaboratorRepository.registerCollaborator(name, phone, birthdate, admissionDate, address, idDocumentNumber, job , idDocumentType);
    }

    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    private JobRepository getJobRepository() {
        if (jobRepository == null) {
            Repositories repositories = Repositories.getInstance();
            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }

    public ArrayList<Job> getJobList(){
        return jobRepository.getJobList();
    }

    public ArrayList<DocumentTypeRepository> getDocTypesList(){
        return new ArrayList<>(Arrays.asList(DocumentTypeRepository.values()));
    }
}
