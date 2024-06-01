package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.HrmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Controller class responsible for handling skill registration operations.
 */
public class RegisterSkillController {

    private final SkillRepository skillRepository;

    /**
     * Constructs a new RegisterSkillController.
     */
    public RegisterSkillController() {
        this.skillRepository = Repositories.getInstance().getSkillRepository();
    }

    /**
     * Registers a single skill with the specified name.
     *
     * @param skillName The name of the skill to register.
     * @return {@code true} if the skill is successfully registered, {@code false} otherwise.
     */
    public boolean registerSkill(String skillName) {
        return skillRepository.registerSkill(skillName);
    }

    /**
     * Reads skills from a file and registers them.
     *
     * @param filePath The path to the file containing skill names.
     * @return {@code true} if all skills are successfully registered, {@code false} otherwise.
     */
    public boolean registerSkillsFromFile(String filePath) {
        ArrayList<String> skillsToAdd = readSkillsFromFile(filePath);
        ArrayList<String> skillsAdded = new ArrayList<>();
        boolean success = false;

        for (String skillName : skillsToAdd) {
            if (registerSkill(skillName)) {
                skillsAdded.add(skillName);
                success = true;
            }
        }

        if (success){
            System.out.println("\n---SKILLS ADDED-----------------------");
            for(String s : skillsAdded){
                System.out.println("\nNew skill: " + s);
            }
        }

        return success;
    }

    /**
     * Reads skill names from a file and returns them as a list.
     *
     * @param filePath The path to the file containing skill names.
     * @return The list of skill names read from the file.
     */
    private static ArrayList<String> readSkillsFromFile(String filePath) {
        boolean validPath = false;
        ArrayList<String> skillsToAdd = new ArrayList<>();

        do {
            try (Scanner scanner = new Scanner(new File(filePath))) {
                validPath = true;
                scanner.useDelimiter("[,\n;]");

                while (scanner.hasNext()) {
                    String skillName = scanner.next().trim();
                    if (!skillName.isEmpty()) {
                        skillsToAdd.add(skillName);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("\nFile not found: " + filePath);
                Scanner sc = new Scanner(System.in);
                System.out.print("\nFile path (write 0 to cancel):");
                filePath = sc.next();
                if (filePath.equals("0")){
                    redirectToHrmUI();
                    break;
                }
            }
        } while (!validPath);

        return skillsToAdd;
    }

    /**
     * Redirects to the HRM user interface.
     */
    private static void redirectToHrmUI() {
        MenuItem item = new MenuItem(AuthenticationController.ROLE_HRM, new HrmUI());
        item.run();
    }
}
