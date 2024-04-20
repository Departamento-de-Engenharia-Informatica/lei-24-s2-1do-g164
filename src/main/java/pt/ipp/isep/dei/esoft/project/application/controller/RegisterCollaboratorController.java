package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.DocumentTypeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TaskCategoryRepository;

import java.util.ArrayList;
import java.util.Arrays;

public class RegisterCollaboratorController {
    private CollaboratorRepository collaboratorRepository;
    private ArrayList<DocumentTypeRepository> documentTypeList;
    public RegisterCollaboratorController(){
        getCollaboratorRepository();

    }

    public boolean registerCollaborator(String name, int phone, String birthdate, String admissionDate, String address, int idDocumentNumber, String job, int idDocumentType){
        return collaboratorRepository.registerCollaborator(name, phone, birthdate, admissionDate, address, idDocumentNumber, job, idDocumentType);
    }

    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    private ArrayList<DocumentTypeRepository> getDocTypesList(){
        documentTypeList = new ArrayList<>(Arrays.asList(DocumentTypeRepository.values()));
        return documentTypeList;
    }
}
