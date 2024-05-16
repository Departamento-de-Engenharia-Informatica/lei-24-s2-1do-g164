package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillsController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.DocumentTypeRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.HrmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Manages the user interface for assigning skills to a collaborator.
 */
public class AssignSkillsUI implements Runnable{

    /**
     * The controller responsible for managing skill assignments.
     */
    private final AssignSkillsController controller;

    /**
     * Constructs a new AssignSkillsUI instance.
     */
    public AssignSkillsUI() {
        controller = new AssignSkillsController();
    }

    /**
     * Gets the controller.
     *
     * @return The controller.
     */
    private AssignSkillsController getController() {
        return controller;
    }

    /**
     * Displays the list of collaborators and allows the user to select one.
     *
     * @return The selected collaborator.
     */
    private Collaborator displayAndSelectCollaborator() {
        ArrayList<Collaborator> collaboratortList = controller.getCollaboratorList();
        int listSize = collaboratortList.size();
        int answer = -1;
        Scanner sc = new Scanner(System.in);
        while (answer != 0) {
            displayCollaboratorList(collaboratortList);
            System.out.println("\nSelect a collaborator number");
            if (sc.hasNextInt()) {
                answer = sc.nextInt();
                if (answer >= 1 && answer <= listSize) {
                    return collaboratortList.get(answer - 1);
                }
                if (answer == 0) {
                    redirectToHrmUI();
                }
            } else {
                System.out.print("\nInvalid input. Please enter a valid skill number.\n");
                sc.next();
            }
        }
        return collaboratortList.get(answer - 1);
    }

    /**
     * Displays the list of collaborators.
     *
     * @param CollaboratorList The list of collaborators to display.
     */
    private void displayCollaboratorList(ArrayList<Collaborator> CollaboratorList) {
        int i = 1;
        System.out.println();
        for (Collaborator collaborator: CollaboratorList) {
            System.out.println("  " + i + " - " + collaborator.getName());
            i++;
        }
        System.out.println("  0 - Cancel");
    }

    /**
     * Displays the list of skills and allows the user to select them.
     *
     * @return The list of selected skills.
     */
    private ArrayList<Skill> displayAndSelectSkills() {
        ArrayList<Skill> skillsList = controller.getSkillsList();
        int listSize = skillsList.size();
        ArrayList<Skill> chosenSkills = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        int answer = -1;
        while (answer != 0) {
            displaySkillsList(skillsList);
            System.out.print("\nSelect a skill number to assign it to the collaborator (0 to stop): ");
            if (sc.hasNextInt()) {
                answer = sc.nextInt();
                if (answer >= 1 && answer <= listSize) {
                    Skill selectedSkill = skillsList.get(answer - 1);
                    if (chosenSkills.contains(selectedSkill)) {
                        System.out.print("\nSkill already assigned. Please select a different skill.\n");
                    } else {
                        chosenSkills.add(selectedSkill);
                    }
                } else if (answer != 0) {
                    System.out.print("\nInvalid input. Please enter a valid skill number.\n");
                }
            } else {
                System.out.print("\nInvalid input. Please enter a valid skill number.\n");
                sc.next();
            }
        }

        return chosenSkills;
    }

    /**
     * Displays the list of skills.
     *
     * @param SkillsList The list of skills to display.
     */
    private void displaySkillsList(ArrayList<Skill> SkillsList) {
        int i = 1;
        System.out.println();
        for (Skill skill: SkillsList) {
            System.out.println("  " + i + " - " + skill.getSkillName());
            i++;
        }
        System.out.println("  0 - Cancel");
    }

    /**
     * Redirects to the HRM user interface.
     */
    private void redirectToHrmUI() {
        MenuItem item = new MenuItem(AuthenticationController.ROLE_HRM, new HrmUI());
        item.run();
    }

    /**
     * Runs the AssignSkillsUI.
     */
    @Override
    public void run() {
        Collaborator collaborator = displayAndSelectCollaborator();
        System.out.println("\nSelected collaborator:");
        System.out.println("- Name: " + collaborator.getName());
        ArrayList<Skill> chosenSkills = displayAndSelectSkills();
        if (!chosenSkills.isEmpty()) {
            if (controller.assignSkills(collaborator,chosenSkills)){
                System.out.println("Skills attributed to the collaborator:");
                for (Skill skill : chosenSkills) {
                    System.out.println("- " + skill.getSkillName());
                }
            }
            else {
                System.out.println("The collaborator already has these skills");
            }
        } else {
            System.out.println("No skills assigned");
        }
    }
}
