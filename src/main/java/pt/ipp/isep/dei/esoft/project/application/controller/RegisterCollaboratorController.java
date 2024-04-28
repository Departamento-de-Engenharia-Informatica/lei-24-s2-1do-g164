package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.CollaboratorStatus;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.DocumentTypeRepository;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Controller class responsible for handling collaborator registration operations.
 */
public class RegisterCollaboratorController {

    private CollaboratorRepository collaboratorRepository;
    private JobRepository jobRepository;

    /**
     * Initializes a new instance of RegisterCollaboratorController.
     */
    public RegisterCollaboratorController() {
        getCollaboratorRepository();
        getJobRepository();
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
    public boolean registerCollaborator(String name, int phone, String birthdate, String admissionDate, String address, int idDocumentNumber, Job job, DocumentTypeRepository idDocumentType, int taxpayerNumber, String email) {
        return collaboratorRepository.registerCollaborator(name, phone, birthdate, admissionDate, address, idDocumentNumber, job, idDocumentType, getCollaboratorStatusList().get(1), taxpayerNumber, email);
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

    /**
     * Retrieves the list of available jobs.
     *
     * @return The list of available jobs.
     */
    public ArrayList<Job> getJobList() {
        return jobRepository.getJobList();
    }

    /**
     * Retrieves the list of document types.
     *
     * @return The list of document types.
     */
    public ArrayList<DocumentTypeRepository> getDocTypesList() {
        return new ArrayList<>(Arrays.asList(DocumentTypeRepository.values()));
    }

    public ArrayList<CollaboratorStatus> getCollaboratorStatusList() {
        return new ArrayList<>(Arrays.asList(CollaboratorStatus.values()));
    }

}
