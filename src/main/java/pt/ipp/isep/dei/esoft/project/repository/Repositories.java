package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;

import java.io.Serializable;

public class Repositories implements Serializable {

    private static Repositories instance;
    private final CollaboratorRepository collaboratorRepository;


    private Repositories(){
        collaboratorRepository = new CollaboratorRepository();

    }

    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }


}