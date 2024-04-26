package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateTeamProposalController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.HrmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.VfmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class CreateTeamProposalUI implements Runnable {

    private final CreateTeamProposalController controller;
    private final SkillRepository skillRepository;
    private int min, max;
    private ArrayList<Skill> skills;

    public CreateTeamProposalUI() {
        controller = new CreateTeamProposalController();
        skillRepository = Repositories.getInstance().getSkillRepository();
    }

    private CreateTeamProposalController getController() {
        return controller;
    }

    private void redirectToHrmUI() {
        MenuItem item = new MenuItem(AuthenticationController.ROLE_HRM, new HrmUI());
        item.run();
    }


    @Override
    public void run() {
        System.out.println("\n\n----------- Create Team Proposal----------------------");
//        int max = requestMaxSize();
//        int min = requestMinSize();
//        ArrayList<Skill> availableSkills = skillRepository.getSkillList();
//        displayAvailableSkills(availableSkills);
//        ArrayList<Skill> skills = requestSkills(availableSkills);
//
//        try {
//            //Team team = controller.createTeamProposal(max, min, skills);
//            System.out.println("\nTeam proposal created successfully!");
//
//        } catch (InputMismatchException e) {
//            System.out.println("\nError: " + e.getMessage());
//        }
        requestData();
        submitData();
    }

    private void requestData() {
        try {
            this.max = Utils.readIntegerFromConsole("Type maximum number of collaborators:");
            this.min = Utils.readIntegerFromConsole("Type minimum number of collaborators:");
        } catch (NumberFormatException ex) {
            System.out.println("Number is not in a valid format");
            redirectToHrmUI();
        }

        if (this.min > this.max) {
            System.out.println("Minimum number cannot be higher than maximum number");
            redirectToHrmUI();
        }

        var skills = this.controller.getSkillsList();
        if (skills.isEmpty()){
            System.out.println("No skills available");
            redirectToHrmUI();
        }

        displayAvailableSkills(skills);
        this.skills = requestSkills(skills);
    }

    private void submitData(){
        try {
            var team = this.controller.createTeamProposal(this.max, this.min, this.skills);
            System.out.println("Team successfully created!");
            System.out.println(team.toString());
        } catch (InputMismatchException ex){
            System.out.println(ex.getMessage());
            redirectToHrmUI();
        }
    }


    private int requestMaxSize() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Maximum size of the team: ");
        return sc.nextInt();
    }


    private int requestMinSize() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Minimum size of the team: ");
        return sc.nextInt();
    }


    private void displayAvailableSkills(List<Skill> skills) {
        System.out.println("\nAvailable skills:");
        for (int i = 0; i < skills.size(); i++) {
            System.out.println((i + 1) + ". " + skills.get(i).getSkillName());
        }
    }

    private ArrayList<Skill> requestSkills(ArrayList<Skill> skills) {
        Scanner input = new Scanner(System.in);
        ArrayList<Skill> selectedSkills = new ArrayList<>();

        boolean addingSkills = true;
        while (addingSkills) {
            System.out.print("Enter the index of the skill you want to add (or type 'done' to finish): ");
            System.out.println("type 'exit' to cancel");
            String userInput = input.nextLine();

            if (userInput.equalsIgnoreCase("done")) {
                addingSkills = false;
            } else if (userInput.equalsIgnoreCase("exit")) {
                redirectToHrmUI();
            } else {
                try {
                    int index = Integer.parseInt(userInput) - 1;
                    if (index >= 0 && index < skills.size()) {
                        selectedSkills.add(skills.get(index));
                    } else {
                        System.out.println("Invalid index. Please try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
        }

        return selectedSkills;
        }


    }

