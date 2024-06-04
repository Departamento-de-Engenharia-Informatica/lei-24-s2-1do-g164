package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;

/**
 * Controller class responsible for handling job registration operations.
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
     * @param jobName The name of the job to register.
     * @return {@code true} if the job is successfully registered, {@code false} otherwise.
     */
    public boolean registerJob(String jobName) {
        return jobRepository.registerJob(jobName);
    }

    public ArrayList<Job> getJobList() {
        return jobRepository.getJobList();
    }
}
