package pt.ipp.isep.dei.esoft.project.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterJobController;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterJobControllerTest {

    @Test
    void registerRegularJobTest(){
        RegisterJobController controller = new RegisterJobController();
        String jobName = "newJob";
        assertTrue(controller.registerJob(jobName));
    }

    @Test
    void registerInvalidJobTest(){
        RegisterJobController controller = new RegisterJobController();
        String jobName = "New_Job";
        String jobName2 = "New Job!";
        String jobName3 = "Job 3";
        assertFalse(controller.registerJob(jobName));
        assertFalse(controller.registerJob(jobName2));
        assertFalse(controller.registerJob(jobName3));
    }

    @Test
    void registerRepeatedJobTest(){
        RegisterJobController controller = new RegisterJobController();
        String jobName = "newJob";
        controller.registerJob(jobName);
        assertFalse(controller.registerJob(jobName));
    }

    @Test
    void registerEmptyJobTest(){
        RegisterJobController controller = new RegisterJobController();
        String jobName = "";
        assertFalse(controller.registerJob(jobName));
    }

}
