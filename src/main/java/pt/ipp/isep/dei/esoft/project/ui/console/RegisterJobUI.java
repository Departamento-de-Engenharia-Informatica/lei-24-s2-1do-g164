package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterJobController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.HrmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;

import java.util.Scanner;

public class RegisterJobUI implements Runnable{

    private  final RegisterJobController controller;
    private String jobName;

    public RegisterJobUI(){
        controller = new RegisterJobController();
    }

    public RegisterJobController getController(){
        return controller;
    }

    private void redirectToHrmUI() {
        MenuItem item = new MenuItem(AuthenticationController.ROLE_HRM, new HrmUI());
        item.run();
    }

    public void run() {
        System.out.println("\n\n--- Register Job----------------------");



        requestJobName();

        if (jobName.equals("0")){
            redirectToHrmUI();
        }
        submitData();

    }

    private void submitData() {
        boolean success;
        success = getController().registerJob(jobName);

        if (success){
            System.out.println("\nJob successfully created!");
        }else{
            System.out.println("\nJob not created, validation for this job name failed!");

        }
    }

    private void requestJobName() {
        Scanner input = new Scanner(System.in);
        System.out.print("\nJob name (write 0 to go cancel): ");
        jobName = input.nextLine();
    }
}
