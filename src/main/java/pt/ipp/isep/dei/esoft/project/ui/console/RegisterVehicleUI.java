package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;
import pt.ipp.isep.dei.esoft.project.repository.DocumentTypeRepository;
import pt.ipp.isep.dei.esoft.project.repository.VehicleTypeRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.VfmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * User Interface class for vehicle registration.
 */
public class RegisterVehicleUI implements Runnable {

    private final RegisterVehicleController controller;
    private String brand;
    private String model;
    private String vehicleID;
    private VehicleTypeRepository type;
    private double grossWeight;
    private double tare;
    private int currentKm;
    private String registerDate;
    private String acquisitionDate;
    private int checkupFrequency;
    private VehicleCheckup lastCheckup;

    /**
     * Constructs a new RegisterVehicleUI instance.
     */
    public RegisterVehicleUI() {
        controller = new RegisterVehicleController();
    }

    /**
     * Retrieves the controller associated with this UI.
     *
     * @return The RegisterVehicleController instance.
     */
    private RegisterVehicleController getController() {
        return controller;
    }

    /**
     * Displays the list of vehicle types and allows the user to select one.
     *
     * @param vehicleTypeList The list of available vehicle types.
     * @return The selected VehicleTypeRepository.
     */
    private VehicleTypeRepository displayAndSelectVehicleType(List<VehicleTypeRepository> vehicleTypeList) {
        int i = 1;
        for (VehicleTypeRepository vType : vehicleTypeList) {
            System.out.println("  " + i + " - " + vType);
            i++;
        }
        System.out.println("  0 - Cancel");

        int answer = -1;
        Scanner sc = new Scanner(System.in);

        while (answer < 0 || answer > vehicleTypeList.size()) {
            System.out.println("Select the vehicle's type: ");
            answer = sc.nextInt();
        }

        if (answer == 0) {
            redirectToVfmUI();
        }

        type = vehicleTypeList.get(answer - 1);
        return type;
    }

    /**
     * Prompts the user to enter the current kilometers of the vehicle.
     *
     * @return The entered current kilometers.
     */
    private int requestCurrentKm() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Current Kilometers: ");
        return sc.nextInt();
    }

    /**
     * Prompts the user to enter the checkup frequency of the vehicle.
     *
     * @return The entered checkup frequency in kilometers.
     */
    private int requestCheckupFrequency() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Checkup Frequency (km): ");
        return sc.nextInt();
    }

    /**
     * Prompts the user to enter the gross weight of the vehicle.
     *
     * @return The entered gross weight in kilograms.
     */
    private double requestGrossWeight() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Gross Weight (kg): ");
        return sc.nextDouble();
    }

    /**
     * Prompts the user to enter the tare weight of the vehicle.
     *
     * @return The entered tare weight in kilograms.
     */
    private double requestTareWeight() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Tare Weight (kg): ");
        return sc.nextDouble();
    }

    /**
     * Prompts the user to enter the acquisition date of the vehicle.
     *
     * @return The entered acquisition date (in "DD-MM-YY" format).
     */
    private String requestAcquisitionDate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Acquisition Date (DD-MM-YY): ");
        return sc.nextLine();
    }

    /**
     * Prompts the user to enter the register date of the vehicle.
     *
     * @return The entered register date (in "DD-MM-YY" format).
     */
    private String requestRegisterDate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Register Date (DD-MM-YY): ");
        return sc.nextLine();
    }

    /**
     * Prompts the user to enter the brand of the vehicle.
     *
     * @return The entered brand of the vehicle.
     */
    private String requestBrand() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Brand: ");
        return sc.nextLine();
    }

    /**
     * Prompts the user to enter the model of the vehicle.
     *
     * @return The entered model of the vehicle.
     */
    private String requestModel() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Model: ");
        return sc.nextLine();
    }

    /**
     * Prompts the user to enter the vehicle ID (plate ID) of the vehicle.
     *
     * @return The entered vehicle ID (plate ID) in "XX-XX-XX" format.
     */
    private String requestVehicleID() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Plate ID (XX-XX-XX): ");
        return sc.nextLine();
    }

    /**
     * Collects all necessary data from the user to register a vehicle.
     */
    private void requestData() {
        this.vehicleID = requestVehicleID();
        this.brand = requestBrand();
        this.model = requestModel();
        this.currentKm = requestCurrentKm();
        this.checkupFrequency = requestCheckupFrequency();
        this.acquisitionDate = requestAcquisitionDate();
        this.registerDate = requestRegisterDate();
        this.grossWeight = requestGrossWeight();
        this.tare = requestTareWeight();
    }

    /**
     * Submits the collected data to the controller to register the vehicle.
     */
    private void submitData() {
        boolean success = controller.registerVehicle(brand, model, vehicleID, type, grossWeight, tare, currentKm, registerDate, acquisitionDate, checkupFrequency);
        if (success) {
            System.out.println("\nVehicle successfully registered!");
        } else {
            System.out.println("\nVehicle registration failed!");
        }
    }

    /**
     * Runs the RegisterVehicleUI, displaying prompts and processing input to register a vehicle.
     */
    @Override
    public void run() {
        System.out.println("\n\n--- Register Vehicle ------------------------");
        displayAndSelectVehicleType(controller.getVehicleTypesList());
        requestData();
        submitData();
    }

    /**
     * Redirects the user to the Vehicle Fleet Manager (VFM) user interface.
     */
    private void redirectToVfmUI() {
        MenuItem item = new MenuItem(AuthenticationController.ROLE_VFM, new VfmUI());
        item.run();
    }
}
