package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterJobController;

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

    public void run() {
        System.out.println("\n\n--- Register Skill----------------------");

        requestJobName();

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
        System.out.print("\nJob name: ");
        jobName = input.nextLine();
    }
}
