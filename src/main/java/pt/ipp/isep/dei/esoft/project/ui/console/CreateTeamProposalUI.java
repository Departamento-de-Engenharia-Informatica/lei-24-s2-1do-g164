package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateteamProposalController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.HrmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * CreateTeamProposalUI class.
 * Provides a console-based user interface for creating team proposals.
 */
public class CreateTeamProposalUI implements Runnable {

    private final CreateteamProposalController controller;
    private final SkillRepository skillRepository;

    /**
     * Constructor for CreateTeamProposalUI.
     * Initializes the controller and skill repository.
     */
    public CreateTeamProposalUI() {
        controller = new CreateteamProposalController();
        skillRepository = Repositories.getInstance().getSkillRepository();
    }

    /**
     * Retrieves the controller associated with this UI.
     *
     * @return The CreateteamProposalController instance.
     */
    private CreateteamProposalController getController() {
        return controller;
    }

    /**
     * Redirects the user to the HRM user interface.
     */
    private void redirectToHrmUI() {
        MenuItem item = new MenuItem(AuthenticationController.ROLE_HRM, new HrmUI());
        item.run();
    }

    /**
     * Runs the CreateTeamProposalUI.
     * Displays available skills, prompts the user to specify team size and required skills,
     * and creates a team proposal based on the input.
     */
    @Override
    public void run() {
        System.out.println("\n\n--- Register Job----------------------");
        int max = requestMaxSize();
        int min = requestMinSize();
        List<Skill> availableSkills = skillRepository.getSkillList();
        displayAvailableSkills(availableSkills);
        ArrayList<Skill> skills = requestSkills(availableSkills);

        try {
            Team team = controller.createTeamProposal(max, min, skills);
            System.out.println("\nTeam proposal created successfully!");

        } catch (InputMismatchException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    /**
     * Prompts the user to enter the maximum size of the team.
     *
     * @return The maximum size of the team entered by the user.
     */
    private int requestMaxSize() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Maximum size of the team: ");
        return sc.nextInt();
    }

    /**
     * Prompts the user to enter the minimum size of the team.
     *
     * @return The minimum size of the team entered by the user.
     */
    private int requestMinSize() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Minimum size of the team: ");
        return sc.nextInt();
    }

    /**
     * Displays the available skills to the user.
     *
     * @param skills The list of available skills.
     */
    private void displayAvailableSkills(List<Skill> skills) {
        System.out.println("\nAvailable skills:");
        for (int i = 0; i < skills.size(); i++) {
            System.out.println((i + 1) + ". " + skills.get(i).getSkillName());
        }
    }

    /**
     * Prompts the user to select the skills they want to add to the team proposal.
     *
     * @param skills The list of available skills.
     * @return The list of skills selected by the user.
     */
    private ArrayList<Skill> requestSkills(List<Skill> skills) {
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
