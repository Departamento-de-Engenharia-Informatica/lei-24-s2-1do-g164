package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateTeamProposalController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.HrmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


/**
 * The type Create team proposal ui.
 */
public class CreateTeamProposalUI implements Runnable {
    private final CreateTeamProposalController controller;
    private int min, max;
    private ArrayList<Skill> skills;


    /**
     * Constructs a CreateTeamProposalUI object with initialized controller and skill repository.
     */
    public CreateTeamProposalUI() {
        controller = new CreateTeamProposalController();
    }

    /**
     * Redirects the user to the HR Manager UI.
     */

    private void redirectToHrmUI() {
        MenuItem item = new MenuItem(AuthenticationController.ROLE_HRM, new HrmUI());
        item.run();
    }

    /**
     * Executes the UI flow for creating a team proposal.
     */
    @Override
    public void run() {
        System.out.println("\n\n----------- Create Team Proposal----------------------");
        requestData();
        Team team = submitData();
        if (team != null) {
            confirmsTeam(team);
        }
        }


    /**
     * Requests input data from the user including the maximum and minimum number of collaborators and required skills.
     */
    private void requestData() {

        boolean validInput = false;

        while (!validInput) {
            try {
                this.max = Utils.readIntegerFromConsole("Type maximum number of collaborators:");
                this.min = Utils.readIntegerFromConsole("Type minimum number of collaborators:");

                if (this.min > this.max) {
                    System.out.println("Minimum number cannot be higher than maximum number. Please try again.");
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Number is not in a valid format. Please try again.");
            }
        }

        var skills = this.controller.getSkillsList();
        if (skills.isEmpty()) {
            System.out.println("No skills available");
            redirectToHrmUI();
        }

        displayAvailableSkills(skills);
        this.skills = requestSkills(skills);
    }

    /**
     * Submits the input data to the controller for creating the team proposal.
     *
     * @return
     */
    private Team submitData() {

        try {
            Team team = this.controller.createTeamProposal(this.max, this.min, this.skills);
            System.out.println("Team proposal created!");
            System.out.println(team.toString());
            return team;
        } catch (InputMismatchException ex) {
            System.out.println(ex.getMessage());
            redirectToHrmUI();
            return null;
        }
    }

    private void confirmsTeam(Team team) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nDo you want to accept or refuse the team proposal?");
        System.out.println("1. Accept");
        System.out.println("2. Refuse");
        System.out.print("Select an option: ");

        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        if (controller.acceptTeamProposal(team)) {
                            System.out.println("Team proposal accepted!");
                        } else {
                            System.out.println("Failed to accept team proposal.");
                        }
                        return;
                    case 2:
                        if (controller.refuseTeamProposal(team)) {
                            System.out.println("Team proposal refused!");
                        } else {
                            System.out.println("Failed to refuse team proposal.");
                        }
                        run();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        run();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }


        /**
         * Displays the available skills to the user.
         *
         * @param skills The list of available skills.
         */
        private void displayAvailableSkills (List < Skill > skills) {
            System.out.println("\nAvailable skills:");
            for (int i = 0; i < skills.size(); i++) {
                System.out.println((i + 1) + ". " + skills.get(i).getSkillName());
            }
        }


        /**
         * Requests the user to select the skills required for the team.
         *
         * @param skills The list of available skills.
         * @return An ArrayList of selected skills.
         */

        private ArrayList<Skill> requestSkills (ArrayList < Skill > skills) {
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


