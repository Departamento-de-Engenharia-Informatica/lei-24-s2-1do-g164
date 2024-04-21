package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.io.Serializable;
import java.util.ArrayList;

public class JobRepository implements Serializable {

    private final ArrayList<Job> jobList = new ArrayList<>();

    public ArrayList<Job> getJobList(){
        return jobList;
    }

    public boolean registerJob(String jobName){

        Job j = new Job(jobName);

        if (!jobAlreadyExists(j)){

            jobList.add(j);

            return true;
        }else{
            return false;
        }
    }


    private boolean jobAlreadyExists(Job j) {
        for (Job job : jobList){
            if (job.equals(j)){
                return true;
            }
        }
        return false;
    }

    public int size(){
        return this.jobList.size();
    }

}
