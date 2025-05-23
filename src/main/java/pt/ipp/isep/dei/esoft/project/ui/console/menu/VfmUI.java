package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the VFM user interface menu.
 */
public class VfmUI implements Runnable {
    /**
     * Initializes a new instance of VfmUI.
     */
    public VfmUI() {
    }

    /**
     * Runs the VFM user interface menu.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register New Vehicle", new RegisterVehicleUI()));
        options.add(new MenuItem("Update a Vehicle's Current KM", new UpdateVehicleCurrentKmUI()));
        options.add(new MenuItem("Register Vehicle Check-up", new RegisterVehicleCheckupUI()));
        options.add(new MenuItem("List of Vehicles Needing Check-Up", new CheckUpReportUI()));
        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n---------- VFM MENU ----------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
