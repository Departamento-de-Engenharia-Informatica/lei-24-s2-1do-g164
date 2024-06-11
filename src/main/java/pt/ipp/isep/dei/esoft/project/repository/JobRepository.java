package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JobRepository - Repository for managing jobs.
 */
public class JobRepository implements Serializable {

    private final ArrayList<Job> jobList = new ArrayList<>();

    /**
     * Retrieves the list of jobs.
     *
     * @return the list of jobs
     */
    public ArrayList<Job> getJobList() {
        return jobList;
    }

    /**
     * Registers a new job.
     *
     * @param jobName the name of the job to register
     * @return true if the job is successfully registered, false otherwise
     */
    public boolean registerJob(String jobName) {
        if (jobIsValid(jobName)) {
            Job job = new Job(jobName);
            jobList.add(job);
            return true;
        } else {
            return false;
        }
    }
    /**
     * Checks if a job name is valid.
     *
     * @param jobName the job name to validate
     * @return true if the job name is valid, false otherwise
     */
    private boolean jobIsValid(String jobName) {
        return isValidJobName(jobName) && jobNameIsUnique(jobName);
    }
    /**
     * Checks if a job name is valid based on regex pattern.
     *
     * @param jName the job name to validate
     * @return true if the job name is valid, false otherwise
     */
    private static boolean isValidJobName(String jName) {
        // Regex to check valid job name (letters and spaces only).
        String regex = "^[a-zA-Z ]+$";
        Pattern p = Pattern.compile(regex);
        if (jName == null) {
            return false;
        }
        Matcher m = p.matcher(jName);
        return m.matches();
    }
    /**
     * Checks if a job name is unique within the repository.
     *
     * @param jName the job name to check
     * @return true if the job name is unique, false otherwise
     */
    private boolean jobNameIsUnique(String jName) {
        for (Job j : jobList) {
            if (j.getJobName().equalsIgnoreCase(jName)) {
                return false;
            }
        }
        return true;
    }

}
