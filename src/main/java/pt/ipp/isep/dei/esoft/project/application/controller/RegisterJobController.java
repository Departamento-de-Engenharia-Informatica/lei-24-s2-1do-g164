package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

/**
 * RegisterJobController - Controller class for registering jobs.
 */
public class RegisterJobController {

    private final JobRepository jobRepository;

    /**
     * Constructs a new RegisterJobController.
     */
    public RegisterJobController() {
        this.jobRepository = Repositories.getInstance().getJobRepository();
    }

    /**
     * Registers a job with the specified name.
     *
     * @param jobName the name of the job to register
     * @return true if the job is successfully registered, false otherwise
     */
    public boolean registerJob(String jobName) {
        return jobRepository.registerJob(jobName);
    }
}
