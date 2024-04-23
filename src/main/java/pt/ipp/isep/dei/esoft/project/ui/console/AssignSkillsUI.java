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

public class AssignSkillsUI implements Runnable{

    /**
     * Constructs a new AssignSkillsUI instance.
     */
    private final AssignSkillsController controller;


    public AssignSkillsUI() {
        controller = new AssignSkillsController();
    }

    private AssignSkillsController getController() {
        return controller;
    }

    private Collaborator displayAndSelectJob() {
        ArrayList<Collaborator> CollaboratortList = controller.getCollaboratorList();
        int listSize = CollaboratortList.size();
        int answer = -1;

        Scanner sc = new Scanner(System.in);

        while (answer < 0 || answer > listSize) {
            displayCollaboratorList(CollaboratortList);
            System.out.print("\nSelect the collaborator's job: ");
            answer = sc.nextInt();
        }

        if (answer == 0) {
            redirectToHrmUI();
        }
        return CollaboratortList.get(answer - 1);
    }

    private void displayCollaboratorList(ArrayList<Collaborator> CollaboratorList) {
        int i = 1;
        System.out.println();
        for (Collaborator collaborator: CollaboratorList) {
            System.out.println("  " + i + " - " + collaborator.getName());
            i++;
        }
        System.out.println("  0 - Cancel");
    }


    private void displaySkillsList(ArrayList<Skill> SkillsList) {
        int i = 1;
        System.out.println();
        for (Skill skill: SkillsList) {
            System.out.println("  " + i + " - " + skill.getSkillName());
            i++;
        }
        System.out.println("  0 - Cancel");
    }

    private Skill displayAndSelectSkills() {
        ArrayList<Skill> SkillsList = controller.getSkillsList();
        int listSize = SkillsList.size();
        int answer = -1;
        ArrayList<Skill> escolherskills = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        while (answer < 0 || answer > listSize) {
            displaySkillsList(SkillsList);
            System.out.print("\nSelect skills and select one when you want to stop: ");
            answer = sc.nextInt();
        }

        if (answer == 0) {
            redirectToHrmUI();
        }
        return SkillsList.get(answer - 1);
    }


    private void redirectToHrmUI() {
        MenuItem item = new MenuItem(AuthenticationController.ROLE_HRM, new HrmUI());
        item.run();
    }







    @Override
    public void run() {

    }


}