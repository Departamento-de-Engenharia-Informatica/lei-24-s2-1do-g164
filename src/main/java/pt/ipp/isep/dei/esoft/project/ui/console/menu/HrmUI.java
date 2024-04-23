package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class HrmUI implements Runnable {
    public HrmUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register New Skill", new RegisterSkillUI()));
        options.add(new MenuItem("Register New Job", new RegisterJobUI()));
        options.add(new MenuItem("Register New Collaborator", new RegisterCollaboratorUI()));
        options.add(new MenuItem("Create Task", new CreateTaskUI()));
        options.add(new MenuItem("Create a Team Proposal", new CreateTeamProposalUI()));
        options.add(new MenuItem("Option 4", new ShowTextUI("You have chosen Option 4.")));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- HRM MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}