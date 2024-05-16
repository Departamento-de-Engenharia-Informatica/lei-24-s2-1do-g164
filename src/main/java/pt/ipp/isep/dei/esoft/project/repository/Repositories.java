package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;

import java.io.Serializable;

/**
 * Represents a collection of repositories.
 */
public class Repositories implements Serializable {

    private static Repositories instance;
    private final CollaboratorRepository collaboratorRepository;
    private final TeamRepository teamRepository;
    private final VehicleRepository vehicleRepository;
    private final SkillRepository skillRepository;
    private final TaskCategoryRepository taskCategoryRepository;
    private final AuthenticationRepository authenticationRepository;
    private final JobRepository jobRepository;
    private final OrganizationRepository organizationRepository;

    /**
     * Initializes a new instance of Repositories.
     */
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

    /**
     * Retrieves the singleton instance of Repositories.
     *
     * @return The singleton instance of Repositories.
     */
    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    /**
     * Retrieves the collaborator repository.
     *
     * @return The collaborator repository.
     */
    public CollaboratorRepository getCollaboratorRepository() {
        return collaboratorRepository;
    }

    /**
     * Retrieves the vehicle repository.
     *
     * @return The vehicle repository.
     */
    public VehicleRepository getVehicleRepository() {
        return vehicleRepository;
    }

    /**
     * Retrieves the skill repository.
     *
     * @return The skill repository.
     */
    public SkillRepository getSkillRepository() {
        return skillRepository;
    }

    /**
     * Retrieves the job repository.
     *
     * @return The job repository.
     */
    public JobRepository getJobRepository() {
        return jobRepository;
    }

    /**
     * Retrieves the authentication repository.
     *
     * @return The authentication repository.
     */
    public AuthenticationRepository getAuthenticationRepository(){
        return authenticationRepository;
    }

    /**
     * Retrieves the task category repository.
     *
     * @return The task category repository.
     */
    public TaskCategoryRepository getTaskCategoryRepository() {
        return taskCategoryRepository;
    }

    /**
     * Retrieves the organization repository.
     *
     * @return The organization repository.
     */
    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
    }

    /**
     * Retrieves the team repository.
     *
     * @return The team repository.
     */
    public TeamRepository getTeamRepository() {
        return teamRepository;
    }
}
