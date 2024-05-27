package pt.ipp.isep.dei.esoft.project.repository;

import com.kitfox.svg.A;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;

import java.io.*;

/**
 * Represents a collection of repositories.
 */
public class Repositories implements Serializable {
    private final String REPOSITORIES_SERIALIZATION_FILE_PATH = "src/main/java/pt/ipp/isep/dei/esoft/project/serialization/repositories.ser";
    private static Repositories instance;
    private  CollaboratorRepository collaboratorRepository;
    private  TeamRepository teamRepository;
    private  VehicleRepository vehicleRepository;
    private  SkillRepository skillRepository;
    private  TaskCategoryRepository taskCategoryRepository;
    private transient final AuthenticationRepository authenticationRepository;
    private  JobRepository jobRepository;
    private  OrganizationRepository organizationRepository;
    private  GreenSpaceRepository greenSpaceRepository;
    private  ToDoEntryRepository toDoEntryRepository;
    private  AgendaEntryRepository agendaEntryRepository;


    /**
     * Initializes a new instance of Repositories.
     */
    private Repositories(){
        agendaEntryRepository = new AgendaEntryRepository();
        teamRepository = new TeamRepository();
        jobRepository = new JobRepository();
        collaboratorRepository = new CollaboratorRepository();
        vehicleRepository = new VehicleRepository();
        skillRepository = new SkillRepository();
        taskCategoryRepository = new TaskCategoryRepository();
        authenticationRepository = new AuthenticationRepository();
        organizationRepository = new OrganizationRepository();
        toDoEntryRepository = new ToDoEntryRepository();
        greenSpaceRepository = new GreenSpaceRepository();
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

    public void serializeRepository() {
        File file = new File(REPOSITORIES_SERIALIZATION_FILE_PATH);
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(instance);
            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean deserializeRepository() {
        try {
            FileInputStream fileIn = new FileInputStream(REPOSITORIES_SERIALIZATION_FILE_PATH);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Repositories repositories = (Repositories) objectIn.readObject();

            this.agendaEntryRepository = repositories.getAgendaEntryRepository();
            this.teamRepository = repositories.getTeamRepository();
            this.jobRepository = repositories.getJobRepository();
            this.collaboratorRepository = repositories.getCollaboratorRepository();
            this.vehicleRepository = repositories.getVehicleRepository();
            this.skillRepository = repositories.getSkillRepository();
            this.taskCategoryRepository = repositories.getTaskCategoryRepository();
            this.greenSpaceRepository = repositories.getGreenSpaceRepository();
            this.organizationRepository = repositories.getOrganizationRepository();
            this.toDoEntryRepository = repositories.getToDoEntryRepository();

            objectIn.close();
            fileIn.close();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
    }

    public AgendaEntryRepository getAgendaEntryRepository() {
        return agendaEntryRepository;
    }

    public GreenSpaceRepository getGreenSpaceRepository() {
        return greenSpaceRepository;
    }

    public ToDoEntryRepository getToDoEntryRepository() {
        return toDoEntryRepository;
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
