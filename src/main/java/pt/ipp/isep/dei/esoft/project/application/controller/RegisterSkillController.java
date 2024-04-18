package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RegisterSkillController {

    private final SkillRepository skillRepository;

    public RegisterSkillController(){
        this.skillRepository = Repositories.getInstance().getSkillRepository();
    }

    public boolean registerSkill(String skillName){

        Skill skill = new Skill(skillName);

        return skillRepository.registerSkill(skill);
    }

    public static ArrayList<String> readFromFile(String filePath){

        // Create an ArrayList to store the words
        ArrayList<String> skillsToAdd = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {

            // Set the delimiter pattern to split on newline or comma
            scanner.useDelimiter("[,\n]");

            while (scanner.hasNext()) {
                String skillName = scanner.next().trim(); // Trim to remove leading/trailing spaces
                if (!skillName.isEmpty()) { // Check if the word is not empty
                    skillsToAdd.add(skillName);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        }

        return skillsToAdd;

    }

    public boolean registerSkills(String fileName){

        ArrayList<String> skillsToAdd = readFromFile(fileName);
        boolean success = true;

        for (String s : skillsToAdd){

            if(!registerSkill(s)){
                success =false;
            }

        }

        return success;
    }

}
