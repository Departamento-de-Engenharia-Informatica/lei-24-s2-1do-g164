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
        return skillRepository.registerSkill(skillName);
    }

    private static ArrayList<String> readSkillsFromFile(String filePath){


        ArrayList<String> skillsToAdd = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {


            scanner.useDelimiter("[,\n;]");

            while (scanner.hasNext()) {
                String skillName = scanner.next().trim();
                if (!skillName.isEmpty()) {
                    skillsToAdd.add(skillName);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        }

        return skillsToAdd;

    }

    public boolean registerSkillsFromFile(String fileName){

        ArrayList<String> skillsToAdd = readSkillsFromFile(fileName);

        boolean success = false;

        for (String skillName : skillsToAdd){

            if(registerSkill(skillName)){
                success =true;
            }

        }

        return success;
    }

}
