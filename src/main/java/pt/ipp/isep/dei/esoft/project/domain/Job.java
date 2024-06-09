package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Job - Represents a job in the system.
 */
public class Job implements Serializable {

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
     * Checks if this job is equal to the specified job name.
     *
     * @param jobName the job name to compare
     * @return true if the job names are equal, false otherwise
     */

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Job job)) {
            return false;
        }
        return this.jobName.equals(job.getJobName());
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(jobName);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return jobName;
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
