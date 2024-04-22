package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

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
            if (option == 1){
                System.out.println("\nSkill successfully created!");
            }else if (option == 2){
                System.out.println("\nSkills successfully created!");
            }
        } else {
            if (option == 1){
                System.out.println("\nSkill not created, validation for this skill name failed!");
            }else if (option == 2){
                System.out.println("\nSkills not created, no skills in the file passed validation!");
            }
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
        System.out.print("\nSkill name: ");
        return input.nextLine();
    }

    private int displayAndSelectTypeOfInput() {
        System.out.println(" 1 - Skill name for single skill input");
        System.out.println(" 2 - File path for multiple skill input");

        int value;
        do {
            String in = Utils.readLineFromConsole("\nSelect a type of input: ");
            try {
                value = Integer.parseInt(in);
            } catch (NumberFormatException ex) {
                value = -1;
            }
        } while (value < 0 || value > 2);

        return value;
    }
}
