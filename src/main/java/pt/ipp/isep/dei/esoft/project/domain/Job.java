package pt.ipp.isep.dei.esoft.project.domain;

public class Job {
    private final String jobName;

    public Job(String jobName){
        this.jobName = jobName;
    }

    public boolean equals(String jobName) {
        return this.jobName.equals(jobName);
    }

    public String getJobName(){
        return this.jobName;
    }
}
