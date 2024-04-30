package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.UpdateVehicleCurrentKmController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.VfmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.InputMismatchException;

import static java.lang.System.in;

public class UpdateVehicleCurrentKmUI implements Runnable{
    private final UpdateVehicleCurrentKmController controller;
    private Vehicle vehicle;
    private int currentKms;

    public UpdateVehicleCurrentKmUI(){
        this.controller = new UpdateVehicleCurrentKmController();
    }

    public void run() {
        System.out.println("\n\n--- Update a Vehicle's Current Kms ------------------------");
        requestData();
        submitData();
    }

    private void requestData(){
        if (this.controller.getVehicleList().isEmpty()) {
            System.out.println("No vehicles found");
            redirectToVfmUI();
        }
        this.vehicle = (Vehicle) Utils.showAndSelectOne(this.controller.getVehicleList(), "Select Vehicle:");
        this.currentKms = requestCurrentKm(this.vehicle);
    }

    private void submitData() {
        if (controller.updateVehicleCurrentKm(this.vehicle, this.currentKms)) {
            System.out.println("Kms updated successfully");
        }
            else{
                System.out.println("Kms update failed!");
            }
        }

    private void redirectToVfmUI() {
        MenuItem item = new MenuItem(AuthenticationController.ROLE_VFM, new VfmUI());
        item.run();
    }

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
