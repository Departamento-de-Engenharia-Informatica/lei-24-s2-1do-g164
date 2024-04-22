package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterJobController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.HrmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;

import java.util.Scanner;

/**
 * RegisterJobUI - User interface for registering a job.
 */
public class RegisterJobUI implements Runnable {

    private final RegisterJobController controller;
    private String jobName;

    /**
     * Constructs a new RegisterJobUI instance.
     */
    public RegisterJobUI() {
        controller = new RegisterJobController();
    }

    /**
     * Gets the RegisterJobController associated with this UI.
     *
     * @return the RegisterJobController
     */
    public RegisterJobController getController() {
        return controller;
    }

    /**
     * Redirects to the HR Manager UI.
     */
    private void redirectToHrmUI() {
        MenuItem item = new MenuItem(AuthenticationController.ROLE_HRM, new HrmUI());
        item.run();
    }

    /**
     * Runs the RegisterJobUI.
     */
    public void run() {
        System.out.println("\n\n--- Register Job----------------------");

        requestJobName();

        if (jobName.equals("0")) {
            redirectToHrmUI();
        }
        submitData();
    }

    /**
     * Submits job data to the controller for registration.
     */
    private void submitData() {
        boolean success;
        success = getController().registerJob(jobName);

        if (success) {
            System.out.println("\nJob successfully created!");
        } else {
            System.out.println("\nJob not created, validation for this job name failed!");
        }
    }

    /**
     * Requests the user to input a job name.
     */
    private void requestJobName() {
        Scanner input = new Scanner(System.in);
        System.out.print("\nJob name (write 0 to cancel): ");
        jobName = input.nextLine();
    }
}
