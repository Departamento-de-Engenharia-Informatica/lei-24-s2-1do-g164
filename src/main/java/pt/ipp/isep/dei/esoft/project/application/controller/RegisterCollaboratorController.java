package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class RegisterCollaboratorController {
    private final CollaboratorRepository collaboratorRepository;

    public RegisterCollaboratorController(){
        this.collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
    }

    public boolean registerCollaborator(String name, int phone, String birthdate, String admissionDate, String address, int idDocumentNumber, Job job){
        Collaborator collaborator = new Collaborator(name, phone, birthdate, admissionDate, address, idDocumentNumber, job);
        return collaboratorRepository.registerCollaborator(collaborator);
    }

}
