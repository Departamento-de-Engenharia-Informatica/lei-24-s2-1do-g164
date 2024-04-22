package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterSkillController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.HrmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.Scanner;

/**
 * RegisterSkillUI - User interface for registering skills.
 */
public class RegisterSkillUI implements Runnable {

    private final RegisterSkillController controller;
    private String nameOrPath;

    /**
     * Constructs a new RegisterSkillUI instance.
     */
    public RegisterSkillUI() {
        controller = new RegisterSkillController();
    }

    /**
     * Gets the RegisterSkillController associated with this UI.
     *
     * @return the RegisterSkillController
     */
    public RegisterSkillController getController() {
        return controller;
    }

    /**
     * Runs the RegisterSkillUI.
     */
    public void run() {
        System.out.println("\n\n--- Register Skill----------------------");

        int option = displayAndSelectTypeOfInput();

        if (option == 0) {
            redirectToHrmUI();
            return;
        }

        requestData(option);

        if (nameOrPath.equals("0")) {
            redirectToHrmUI();
            return;
        }

        submitData(option);
    }

    /**
     * Redirects to the HR Manager UI.
     */
    private void redirectToHrmUI() {
        MenuItem item = new MenuItem(AuthenticationController.ROLE_HRM, new HrmUI());
        item.run();
    }

    /**
     * Submits skill data to the controller for registration.
     *
     * @param option the input option selected
     */
    private void submitData(int option) {
        boolean success = false;

        if (option == 1) {
            success = getController().registerSkill(nameOrPath);
        } else if (option == 2) {
            success = getController().registerSkillsFromFile(nameOrPath);
        }

        if (success) {
            if (option == 1) {
                System.out.println("\nSkill successfully created!");
            } else {
                System.out.println("\nSkills successfully created!");
            }
        } else {
            if (option == 1) {
                System.out.println("\nSkill not created, validation for this skill name failed!");
            } else if (option == 2) {
                System.out.println("\nSkills not created, no skills in the file passed validation!");
            }
        }
    }

    /**
     * Requests data based on the input option.
     *
     * @param option the input option selected
     */
    private void requestData(int option) {
        if (option == 1) {
            nameOrPath = requestSkillName();
        } else if (option == 2) {
            nameOrPath = requestFilePath();
        }
    }

    /**
     * Requests the file path input from the user.
     *
     * @return the file path input
     */
    private String requestFilePath() {
        Scanner input = new Scanner(System.in);
        System.out.print("File path (write 0 to cancel): ");
        return input.nextLine();
    }

    /**
     * Requests the skill name input from the user.
     *
     * @return the skill name input
     */
    private String requestSkillName() {
        Scanner input = new Scanner(System.in);
        System.out.print("\nSkill name (write 0 to cancel): ");
        return input.nextLine();
    }

    /**
     * Displays options for input and selects an option.
     *
     * @return the selected input option
     */
    private int displayAndSelectTypeOfInput() {
        System.out.println(" 1 - Skill name for single skill input");
        System.out.println(" 2 - File path for multiple skill input");
        System.out.println(" 0 - Cancel");

        int value;
        do {
            String in = Utils.readLineFromConsole("\nSelect a type of input: ");
            try {
                assert in != null;
                value = Integer.parseInt(in);
            } catch (NumberFormatException ex) {
                value = -1;
            }
        } while (value < 0 || value > 2);

        return value;
    }
}
