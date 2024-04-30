package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListofVehiclesNeedingCheckUpController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import java.util.ArrayList;

/**
 * User Interface for presenting a list/report of vehicles needing a check-up and their respective characteristics
 */
public class CheckUpReportUI implements Runnable {

    private ListofVehiclesNeedingCheckUpController controller;

    /**
     * Constructs a new CheckUpReportUI instance
     */
    public CheckUpReportUI() { this.controller = new ListofVehiclesNeedingCheckUpController();}


    /**
     * Executes the list/report generation process
     */
    @Override
    public void run() {
        System.out.println("\n\n--- List of Vehicles in need of a Check-Up  ------------------------");

        requestData();

    }

    /**
     * Requests data from the controller and displays the list/report
     */
    private void requestData(){
        ArrayList<Vehicle> List = this.controller.getVehicles();
        if (List.isEmpty()) {
            System.out.println("No vehicles found");
        }else{
            for (Vehicle v: List) {
                if (v.needsCheckup()) {
                    System.out.println(v.toString());
                }
            }
        }
    }

}