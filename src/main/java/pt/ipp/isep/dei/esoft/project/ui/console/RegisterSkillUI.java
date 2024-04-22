package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RegisterSkillUI implements Runnable{

    private  final RegisterSkillController controller;
    private String nameOrPath;

    public RegisterSkillUI(){
        controller = new RegisterSkillController();
    }

    public RegisterSkillController getController(){
        return controller;
    }


    public void run() {
        System.out.println("\n\n--- Register Skill----------------------");

        int option = displayAndSelectTypeOfInput();

        requestData(option);

        submitData(option);
    }

    private void submitData(int option) {
        boolean success = false;

        if (option == 1){
            success = getController().registerSkill(nameOrPath);
        }else if (option == 2){
            success = getController().registerSkillsFromFile(nameOrPath);
        }

        if (success) {
            System.out.println("\nSkill successfully created!");
        } else {
            System.out.println("\nSkill not created!");
        }
    }

    private void requestData(int option) {
        if (option == 1){
            nameOrPath = requestSkillName();
        } else if (option == 2) {
            nameOrPath = requestFilePath();
        }
    }

    private String requestFilePath() {
        Scanner input = new Scanner(System.in);
        System.out.print("File path: ");
        return input.nextLine();
    }

    private String requestSkillName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Skill name: ");
        return input.nextLine();
    }

    private int displayAndSelectTypeOfInput() {

        int answer = -1;
        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > 2) {
            System.out.println(" 1 - Skill name for single skill input");
            System.out.println(" 2 - File path for multiple skill input");
            System.out.print("Select a type of input: ");
            answer = input.nextInt();
        }

        return answer;
    }
}
