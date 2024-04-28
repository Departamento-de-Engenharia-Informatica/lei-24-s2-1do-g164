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

        private Collaborator displayAndSelectCollaborator() {
            ArrayList<Collaborator> collaboratortList = controller.getCollaboratorList();
            int listSize = collaboratortList.size();
            int answer = -1;

            Scanner sc = new Scanner(System.in);

            while (answer < 0 || answer > listSize) {
                displayCollaboratorList(collaboratortList);
                System.out.print("\nSelect the collaborator: ");
                answer = sc.nextInt();
            }

            if (answer == 0) {
                redirectToHrmUI();
            }
            return collaboratortList.get(answer - 1);
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
        private ArrayList<Skill> displayAndSelectSkills() {
            ArrayList<Skill> skillsList = controller.getSkillsList();
            int listSize = skillsList.size();
            ArrayList<Skill> chosenSkills = new ArrayList<>();

            Scanner sc = new Scanner(System.in);

            int answer = -1;
            while (answer != 0) {
                displaySkillsList(skillsList);
                System.out.print("\nSelect a skill number to assign it to the collaborator (0 to stop): ");
                answer = sc.nextInt();
                if (answer >= 1 && answer <= listSize) {
                    Skill selectedSkill = skillsList.get(answer - 1);
                    if (chosenSkills.contains(selectedSkill)) {
                        System.out.print("\nSkill already assigned. Please select a different skill.");
                    } else {
                        chosenSkills.add(selectedSkill);
                    }

                } else if (answer!=0){
                    System.out.print("\nInvalid input. Please enter a valid skill number.");
                }
            }

            return chosenSkills;
        }

        private void redirectToHrmUI() {
            MenuItem item = new MenuItem(AuthenticationController.ROLE_HRM, new HrmUI());
            item.run();
        }
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