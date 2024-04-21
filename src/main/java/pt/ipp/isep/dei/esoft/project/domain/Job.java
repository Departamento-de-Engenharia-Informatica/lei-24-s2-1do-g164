package pt.ipp.isep.dei.esoft.project.domain;

public class Job {
    private final String jobName;

    public Job(String jobName){
        this.jobName = jobName;
    }

    public boolean equals(Job j) {
        return jobName.equals(j.getJobName());
    }

    public String getJobName(){
        return this.jobName;
    }
}
