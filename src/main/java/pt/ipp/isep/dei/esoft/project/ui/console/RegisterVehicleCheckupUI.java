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

public class RegisterVehicleCheckupUI implements Runnable {
    private final RegisterVehicleCheckupController controller;
    private Vehicle vehicle;
    private LocalDate date;
    private int currentKms;


    public RegisterVehicleCheckupUI() {
        this.controller = new RegisterVehicleCheckupController();
    }

    public void run() {
        System.out.println("\n\n--- Register Vehicle Checkup ------------------------");

        requestData();
        submitData();
    }

    private void requestData(){
        if (this.controller.getVehicles().isEmpty()) {
            System.out.println("No vehicles found");
            redirectToVfmUI();
        }
        this.vehicle = (Vehicle) Utils.showAndSelectOne(this.controller.getVehicles(), "Select Vehicle:");
        if (this.vehicle == null) {
            this.redirectToVfmUI();
        }

        try {
            this.date = Utils.readDateFromConsole("Type date (DD-MM-YYYY):");
        } catch (DateTimeParseException ex) {
            System.out.println("Date is not in a valid format");
            this.redirectToVfmUI();
        }
        if (this.date.isBefore(LocalDate.now())) {
            System.out.println("Date can't be before today's date");
            this.redirectToVfmUI();
        }

        try {
            this.currentKms = Utils.readIntegerFromConsole("Type current Kms (Last known Kms: " + this.vehicle.getCurrentKm() + "Kms):");
        } catch (NumberFormatException ex) {
            System.out.println("Current Kms are not in a valid format");
            this.redirectToVfmUI();
        }
        if (this.currentKms < this.vehicle.getCurrentKm()) {
            System.out.println("Current Kms should be higher than " + this.vehicle.getCurrentKm() + "Kms");
            this.redirectToVfmUI();
        }
    }

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

    private void redirectToVfmUI() {
        MenuItem item = new MenuItem(AuthenticationController.ROLE_VFM, new VfmUI());
        item.run();
    }
}
