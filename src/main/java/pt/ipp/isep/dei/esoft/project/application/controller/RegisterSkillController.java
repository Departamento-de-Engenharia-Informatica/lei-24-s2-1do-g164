package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

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
     * @param skillName the name of the skill to register
     * @return true if the skill is successfully registered, false otherwise
     */
    public boolean registerSkill(String skillName) {
        return skillRepository.registerSkill(skillName);
    }

    /**
     * Reads skills from a file and registers them.
     *
     * @param filePath the path to the file containing skill names
     * @return true if all skills are successfully registered, false otherwise
     */
    public boolean registerSkillsFromFile(String filePath) {
        ArrayList<String> skillsToAdd = readSkillsFromFile(filePath);
        boolean success = false;

        for (String skillName : skillsToAdd) {
            if (registerSkill(skillName)) {
                success = true;
            }
        }

        return success;
    }

    /**
     * Reads skill names from a file and returns them as a list.
     *
     * @param filePath the path to the file containing skill names
     * @return the list of skill names read from the file
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
                System.out.print("\nFile path:");
                filePath = sc.next();
            }
        } while (!validPath);

        return skillsToAdd;
    }
}
