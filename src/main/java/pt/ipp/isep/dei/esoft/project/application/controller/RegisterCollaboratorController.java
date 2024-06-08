package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.enums.CollaboratorStatusENUM;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.enums.DocumentTypeENUM;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/**
 * Controller class responsible for handling collaborator registration operations.
 */
public class RegisterCollaboratorController {

    private CollaboratorRepository collaboratorRepository;
    private JobRepository jobRepository;
    private AuthenticationRepository authenticationRepository;

    /**
     * Initializes a new instance of RegisterCollaboratorController.
     */
    public RegisterCollaboratorController() {
        getCollaboratorRepository();
        getJobRepository();
        getAuthenticationRepository();
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
     * @param taxpayerNumber   The taxpayer number of the collaborator.
     * @param email            The email of the collaborator.
     * @return {@code true} if the collaborator is successfully registered, {@code false} otherwise.
     */
    public boolean registerCollaborator(String name, int phone, String birthdate, String admissionDate, String address, int idDocumentNumber, Job job, DocumentTypeENUM idDocumentType, int taxpayerNumber, String email) {
        if(collaboratorRepository.registerCollaborator(name, phone, birthdate, admissionDate, address, idDocumentNumber, job, idDocumentType, taxpayerNumber, email)){
            authenticationRepository.addUserWithRole(name, email, name.toUpperCase()+"1234", AuthenticationController.ROLE_COL);
            System.out.println(name.toUpperCase()+"1234");
            return true;
        } else {
            System.out.println("falsoo");
            return false;
        }
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

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Retrieves the list of available jobs.
     *
     * @return The list of available jobs.
     */
    public ArrayList<Job> getJobList() {
        return jobRepository.getJobList();
    }

    public ArrayList<Job> getJobListNames() {
        return jobRepository.getJobList();
    }


    /**
     * Retrieves the list of document types.
     *
     * @return The list of document types.
     */
    public ArrayList<DocumentTypeENUM> getDocTypesList() {
        return new ArrayList<>(Arrays.asList(DocumentTypeENUM.values()));
    }

    /**
     * Retrieves a list of all available collaborator statuses.
     *
     * @return an ArrayList containing all CollaboratorStatus values
     */
    public ArrayList<CollaboratorStatusENUM> getCollaboratorStatusList() {
        return new ArrayList<>(Arrays.asList(CollaboratorStatusENUM.values()));
    }

    public ArrayList<Collaborator> getCollaboratorList() {
        return collaboratorRepository.getCollaboratorList();
    }

}
