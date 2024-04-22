package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class RegisterJobController {

    private final JobRepository jobRepository;

    public RegisterJobController(){
        this.jobRepository = Repositories.getInstance().getJobRepository();
    }

    public boolean registerJob(String jobName){

        return jobRepository.registerJob(jobName);
    }
}
