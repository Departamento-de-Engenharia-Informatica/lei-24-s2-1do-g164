package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the HRM user interface menu.
 */
public class HrmUI implements Runnable {
    /**
     * Initializes a new instance of HrmUI.
     */
    public HrmUI() {
    }

    /**
     * Runs the HRM user interface menu.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register New Skill", new RegisterSkillUI()));
        options.add(new MenuItem("Register New Job", new RegisterJobUI()));
        options.add(new MenuItem("Register New Collaborator", new RegisterCollaboratorUI()));
        options.add(new MenuItem("Assign Skills", new AssignSkillsUI()));
        options.add(new MenuItem("Create a Team Proposal", new CreateTeamProposalUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n---------- HRM MENU ----------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
