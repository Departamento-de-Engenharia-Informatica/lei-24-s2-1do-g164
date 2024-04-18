package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;

import java.io.Serializable;

public class Repositories implements Serializable {

    private static Repositories instance;
    private final CollaboratorRepository collaboratorRepository;
    private final VehicleRepository vehicleRepository;
    private final SkillRepository skillRepository;


    private Repositories(){
        collaboratorRepository = new CollaboratorRepository();
        vehicleRepository = new VehicleRepository();
        skillRepository = new SkillRepository();
    }

    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    public CollaboratorRepository getCollaboratorRepository() {
        return collaboratorRepository;
    }

    public VehicleRepository getVehicleRepository() {
        return vehicleRepository;
    }

    public SkillRepository getSkillRepository() {
        return skillRepository;
    }
}