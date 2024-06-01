package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleCheckupController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.VfmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

/**
 * The type Register vehicle checkup ui.
 */
public class RegisterVehicleCheckupUI implements Runnable {
    private final RegisterVehicleCheckupController controller;
    private Vehicle vehicle;
    private LocalDate date;
    private int currentKms;

    /**
     * Constructs a RegisterVehicleCheckupUI object with a new instance of RegisterVehicleCheckupController.
     */
    public RegisterVehicleCheckupUI() {
        this.controller = new RegisterVehicleCheckupController();
    }
    /**
     * Executes the UI flow for registering a vehicle checkup.
     */
    public void run() {
        System.out.println("\n\n--- Register Vehicle Checkup ------------------------");
        requestData();
        submitData();
    }
    /**
     * Requests input data from the user including vehicle selection, date, and current kilometers.
     * Redirects to the Vehicle Fleet Management UI if no vehicles are found or if invalid data is entered.
     */
    private void requestData() {
        if (this.controller.getVehicles().isEmpty()) {
            System.out.println("No vehicles found");
            redirectToVfmUI();
        }
        this.vehicle = (Vehicle) Utils.showAndSelectOne(this.controller.getVehicles(), "Select Vehicle:");
        if (this.vehicle == null) {
            this.redirectToVfmUI();
        }


        try {
            do {
                this.date = Utils.readDateFromConsole("Type date (DD-MM-YYYY):");
                if (this.date.isAfter(LocalDate.now())) {
                    System.out.println("Date can't be after today's date. Please try again.");
                }
            } while (this.date.isAfter(LocalDate.now()));
        } catch (DateTimeParseException ex) {
            System.out.println("Date is not in a valid format");
        }


        try {
            do {
                try {
                    this.currentKms = Utils.readIntegerFromConsole("Type current Kms (Last known Kms: " + this.vehicle.getCurrentKm() + "Kms):");
                } catch (NumberFormatException ex) {
                    System.out.println("Current Kms are not in a valid format");
                    continue;
                }

                if (this.currentKms < this.vehicle.getCurrentKm()) {
                    System.out.println("Current Kms should be higher than " + this.vehicle.getCurrentKm() + "Kms. Please try again.");
                }
            } while (this.currentKms < this.vehicle.getCurrentKm());

        } catch (Exception e) {
        }
    }

    /**
     * Submits the input data to the controller for registering the vehicle checkup.
     * Displays success or failure messages accordingly.
     */
    private void submitData() {
        try {
            boolean checkup = this.controller.createVehicleCheckup(this.vehicle, this.date, this.currentKms);
            if (checkup) {
                System.out.println("\nVehicle checkup successfully created!");
            } else {
                System.out.println("\nVehicle checkup not created!");
            }
        } catch (InputMismatchException ex) {
            System.out.println("Vehicle checkup not created!: " + ex.getMessage());
        }
    }
    /**
     * Redirects the user to the Vehicle Fleet Management UI.
     */
    private void redirectToVfmUI() {
        MenuItem item = new MenuItem(AuthenticationController.ROLE_VFM, new VfmUI());
        item.run();
    }
}
