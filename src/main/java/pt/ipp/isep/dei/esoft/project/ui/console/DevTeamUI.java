package pt.ipp.isep.dei.esoft.project.ui.console;

/**
 * Represents the user interface for displaying the development team information.
 */
public class DevTeamUI implements Runnable {

    /**
     * Constructs a new DevTeamUI instance.
     */
    public DevTeamUI() {
    }

    /**
     * Runs the DevTeamUI, displaying the development team information.
     */
    @Override
    public void run() {
        System.out.println("\n");
        System.out.println("---------- DEVELOPMENT TEAM ----------");
        System.out.println("  Dinis Araújo - 1230767@isep.ipp.pt");
        System.out.println("  Gabriela Teixeira - 1230609@isep.ipp.pt");
        System.out.println("  Leonor Marinho - 1230977@isep.ipp.pt");
        System.out.println("  Vasco Azevedo - 1230776@isep.ipp.pt");
    }
}
