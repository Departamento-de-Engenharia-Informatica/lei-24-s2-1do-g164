package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import static org.junit.jupiter.api.Assertions.*;

public class JobRepositoryTest {

    @Test
    void ensureNewJobSuccessfullyAdded() {
        JobRepository jobRepository = new JobRepository();
        String jobName = "newJob";
        jobRepository.registerJob(jobName);
    }

    @Test
    void ensureJobNameIsValidated() {
        JobRepository jobRepository = new JobRepository();
        String jName1 = "newJob";
        String jName2 = "New Job";
        String jName3 = "newJob 2";
        String jName4 = "NewJob!";
        String jName5 = "New Job !";
        String jName6 = "New Job2!";
        assertTrue(jobRepository.registerJob(jName1));
        assertTrue(jobRepository.registerJob(jName2));
        assertFalse(jobRepository.registerJob(jName3));
        assertFalse(jobRepository.registerJob(jName4));
        assertFalse(jobRepository.registerJob(jName5));
        assertFalse(jobRepository.registerJob(jName6));
    }

    @Test
    void ensureRegisteringDuplicateSkillsFails() {
        JobRepository jobRepository = new JobRepository();
        String jobName = "newJob";
        String jobName2 = "newJob";
        jobRepository.registerJob(jobName);
        assertFalse(jobRepository.registerJob(jobName2));
    }

    @Test
    void ensureAddingDifferentJobsWorks() {
        //Arrange
        JobRepository jobRepository = new JobRepository();
        String job1 = "Job a";
        String job2 = "Job b";
        //Add the first task
        jobRepository.registerJob(job1);

        //Act
        boolean result = jobRepository.registerJob(job2);

        //Assert
        assertTrue(result);
    }


    @Test
    void ensureGetJobListReturnsTheCorrectList() {
        //Arrange
        JobRepository jobRepository = new JobRepository();
        String jobName = "JobName";
        Job job = new Job(jobName);
        jobRepository.registerJob(jobName);
        int expectedSize = 1;

        //Act
        int size = jobRepository.getJobList().size();

        //Assert
        assertEquals(expectedSize, size);
        assertEquals(job, jobRepository.getJobList().get(size - 1));
    }

}
