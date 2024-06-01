package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.UpdateVehicleCurrentKmController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.VfmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.InputMismatchException;

import static java.lang.System.in;

/**
 * This class represents the user interface for updating a vehicle's current kilometers.
 */
public class UpdateVehicleCurrentKmUI implements Runnable{
    private final UpdateVehicleCurrentKmController controller;
    private Vehicle vehicle;
    private int currentKms;

    /**
     * Constructs an instance of UpdateVehicleCurrentKmUI.
     */
    public UpdateVehicleCurrentKmUI(){
        this.controller = new UpdateVehicleCurrentKmController();
    }

    /**
     * Runs the update vehicle's current kilometers user interface.
     * This method displays necessary information, requests input, and submits the data for updating.
     */

    public void run() {
        System.out.println("\n\n--- Update a Vehicle's Current Kms ------------------------");
        requestData();
        submitData();
    }
    /**
     * Requests necessary data from the user for updating a vehicle's current kilometers.
     * It selects a vehicle from the list and requests the new current kilometers.
     */
    private void requestData(){
        if (this.controller.getVehicleList().isEmpty()) {
            System.out.println("No vehicles found");
            redirectToVfmUI();
        }
        this.vehicle = (Vehicle) Utils.showAndSelectOne(this.controller.getVehicleList(), "Select Vehicle:");
        this.currentKms = requestCurrentKm(this.vehicle);
    }

    /**
     * Submits the data for updating the vehicle's current kilometers.
     * It communicates with the controller to perform the update operation and provides appropriate feedback.
     */
    private void submitData() {
        if (controller.updateVehicleCurrentKm(this.vehicle, this.currentKms)) {
            System.out.println("Kms updated successfully");
        }
            else{
                System.out.println("Kms update failed!");
            }
        }
    /**
     * Redirects the user interface to the Vehicle Fleet Management UI.
     * This method is called when there are no vehicles found.
     */

    private void redirectToVfmUI() {
        MenuItem item = new MenuItem(AuthenticationController.ROLE_VFM, new VfmUI());
        item.run();
    }


    /**
     * Requests input for the new current kilometers for the vehicle.
     * It validates the input to ensure it is a valid positive integer and greater than the current kilometers of the vehicle.
     * @param vehicle The vehicle for which the current kilometers are being updated.
     * @return The new current kilometers provided by the user.
     */
    private int requestCurrentKm(Vehicle vehicle) {
        int value;
        do {
            String in = Utils.readLineFromConsole("Enter Current Km (Last kms: " + vehicle.getCurrentKm() + "): ");
            try {
                assert in != null;
                value = Integer.parseInt(in);
                if(value < 0){
                    System.out.println("Invalid number of kilometers!");
                }
                else if(value < vehicle.getCurrentKm()){
                    System.out.println("Number of kms must be greater than the last current kms");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number of kilometers!");
                value = -1;
            }
        } while (value < 0 || value < vehicle.getCurrentKm());
        return value;
    }
}
