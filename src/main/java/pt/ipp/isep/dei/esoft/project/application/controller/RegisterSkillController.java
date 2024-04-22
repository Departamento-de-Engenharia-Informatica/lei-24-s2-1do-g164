package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.RegisterSkillUI;

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
        return skillRepository.registerSkill(skillName);
    }

    private static ArrayList<String> readSkillsFromFile(String filePath){
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
        }while (!validPath);

        return skillsToAdd;

    }

    public boolean registerSkillsFromFile(String filePath){

        ArrayList<String> skillsToAdd = readSkillsFromFile(filePath);

        boolean success = false;

        for (String skillName : skillsToAdd){

            if(registerSkill(skillName)){
                success =true;
            }

        }

        return success;
    }

}
