package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;

import java.io.Serializable;

public class Repositories implements Serializable {

    private static Repositories instance;
    private final CollaboratorRepository collaboratorRepository;

    private final TeamRepository teamRepository;



    private final VehicleRepository vehicleRepository;
    private final SkillRepository skillRepository;
    private final TaskCategoryRepository taskCategoryRepository;
    private final AuthenticationRepository authenticationRepository;
    private  final JobRepository jobRepository;
    private final OrganizationRepository organizationRepository;



    private Repositories(){
        teamRepository = new TeamRepository();
        jobRepository = new JobRepository();
        collaboratorRepository = new CollaboratorRepository();
        vehicleRepository = new VehicleRepository();
        skillRepository = new SkillRepository();
        taskCategoryRepository = new TaskCategoryRepository();
        authenticationRepository = new AuthenticationRepository();
        organizationRepository = new OrganizationRepository();
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

    public JobRepository getJobRepository() {
        return jobRepository;
    }

    public AuthenticationRepository getAuthenticationRepository(){
        return authenticationRepository;
    }

    public TaskCategoryRepository getTaskCategoryRepository() {
        return taskCategoryRepository;
    }
    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
    }

    public TeamRepository getTeamRepository() { return teamRepository;
    }
}