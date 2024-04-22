package pt.ipp.isep.dei.esoft.project.domain;

/**
 * Job - Represents a job in the system.
 */
public class Job {

    private final String jobName;

    /**
     * Constructs a new Job with the specified job name.
     *
     * @param jobName the name of the job
     */
    public Job(String jobName) {
        this.jobName = jobName;
    }

    /**
     * Checks if this Job is equal to another job name.
     *
     * @param jobName the job name to compare
     * @return true if the job names are equal, false otherwise
     */
    public boolean equals(String jobName) {
        return this.jobName.equals(jobName);
    }

    /**
     * Gets the name of the job.
     *
     * @return the job name
     */
    public String getJobName() {
        return this.jobName;
    }
}
