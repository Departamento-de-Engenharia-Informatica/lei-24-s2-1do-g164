package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JobRepository implements Serializable {

    private final ArrayList<Job> jobList = new ArrayList<>();

    public ArrayList<Job> getJobList(){
        return jobList;
    }

    public boolean registerJob(String jobName){

        if (jobIsValid(jobName)){

            Job job = new Job(jobName);

            jobList.add(job);

            return true;
        }else{
            return false;
        }
    }

    public int size(){
        return this.jobList.size();
    }

    private  boolean jobIsValid(String jobName) {
        System.out.println(isValidJobName(jobName));
        System.out.println(jobNameIsUnique(jobName));
        return isValidJobName(jobName) && jobNameIsUnique(jobName);
    }


    private static boolean isValidJobName(String jName) {

        // Regex to check valid username.
        String regex = "^[a-zA-Z ]+$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);


        if (jName == null) {
            return false;
        }


        Matcher m = p.matcher(jName);

        return m.matches();
    }

    private boolean jobNameIsUnique(String jName) {
        for (Job j : jobList){
            if (j.equals(jName)){
                return false;
            }
        }
        return true;
    }

}
