package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;
import pt.ipp.isep.dei.esoft.project.repository.ENUM.VehicleTypeRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.VfmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * @return The selected VehicleType.
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
        do {
            String in = Utils.readLineFromConsole("Select the vehicle's type (0 to cancel): ");
            try {
                assert in != null;
                answer = Integer.parseInt(in);
                if(answer < 0 || answer > vehicleTypeList.size()){
                    System.out.println("Invalid option!");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid option!");
                answer = -1;
            }
        } while (answer < 0 || answer > vehicleTypeList.size());

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
        int value;
        do {
            String in = Utils.readLineFromConsole("Enter Current kilometers: ");
            try {
                assert in != null;
                value = Integer.parseInt(in);
                if(value < 0){
                    System.out.println("Invalid number of kilometers!");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number of kilometers!");
                value = -1;
            }
        } while (value < 0);
        return value;
    }

    /**
     * Prompts the user to enter the checkup frequency of the vehicle.
     *
     * @return The entered checkup frequency in kilometers.
     */
    private int requestCheckupFrequency() {
        int value;
        do {
            String in = Utils.readLineFromConsole("Enter Checkup Frequency (km): ");
            try {
                assert in != null;
                value = Integer.parseInt(in);
                if(value < 0){
                    System.out.println("Invalid number of kilometers!");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number of kilometers!");
                value = -1;
            }
        } while (value < 0);
        return value;
    }

    /**
     * Prompts the user to enter the gross weight of the vehicle.
     *
     * @return The entered gross weight in kilograms.
     */
    private double requestGrossWeight() {
        int value;
        do {
            String in = Utils.readLineFromConsole("Enter Gross Weight (kg): ");
            try {
                assert in != null;
                value = Integer.parseInt(in);
                if(value < 0){
                    System.out.println("Invalid weight!");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid weight");
                value = -1;
            }
        } while (value < 0);
        return value;
    }

    /**
     * Prompts the user to enter the tare weight of the vehicle.
     *
     * @return The entered tare weight in kilograms.
     */
    private double requestTareWeight() {
        int value;
        do {
            String in = Utils.readLineFromConsole("Enter Tare Weight (kg): ");
            try {
                assert in != null;
                value = Integer.parseInt(in);
                if(value < 0){
                    System.out.println("Invalid weight!");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid weight");
                value = -1;
            }
        } while (value < 0);
        return value;
    }

    /**
     * Prompts the user to enter the acquisition date of the vehicle.
     *
     * @return The entered acquisition date (in "DD-MM-YY" format).
     */
    private String requestAcquisitionDate() {
        Scanner sc = new Scanner(System.in);
        String acquisitionDate;
        do{
            System.out.print("\nEnter Acquisition Date (DD-MM-YYYY): ");
            acquisitionDate = sc.nextLine();
        }while (!isValidDateFormat(acquisitionDate));
        return acquisitionDate;
    }

    /**
     * Prompts the user to enter the register date of the vehicle.
     *
     * @return The entered register date (in "DD-MM-YY" format).
     */
    private String requestRegisterDate() {
        Scanner sc = new Scanner(System.in);
        String registerDate;
        do{
            System.out.print("\nEnter Register Date (DD-MM-YYYY): ");
            registerDate = sc.nextLine();
        }while (!isValidDateFormat(registerDate));
        return registerDate;
    }

    /**
     * Prompts the user to enter the brand of the vehicle.
     *
     * @return The entered brand of the vehicle.
     */
    private String requestBrand() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Brand: ");
        return sc.nextLine();
    }

    /**
     * Prompts the user to enter the model of the vehicle.
     *
     * @return The entered model of the vehicle.
     */
    private String requestModel() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Model: ");
        return sc.nextLine();
    }

    /**
     * Prompts the user to enter the vehicle ID (plate ID) of the vehicle.
     *
     * @return The entered vehicle ID (plate ID) in "XX-XX-XX" format.
     */
    private String requestVehicleID() {
        Scanner sc = new Scanner(System.in);
        String plate;
        do{
            System.out.print("\nEnter Plate ID (XX-XX-XX): ");
            plate = sc.nextLine();
        }while (!isValidPlateFormat(plate, this.registerDate));
        return plate;
    }

    /**
     * Collects all necessary data from the user to register a vehicle.
     */
    private void requestData() {
        this.brand = requestBrand();
        this.model = requestModel();
        this.currentKm = requestCurrentKm();
        this.checkupFrequency = requestCheckupFrequency();
        this.acquisitionDate = requestAcquisitionDate();
        this.registerDate = requestRegisterDate();
        this.vehicleID = requestVehicleID();
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
            System.out.print("There already exists a vehicle with the same Plate ID!");
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

    /**
     * Validates the format of the plate string based on the acquisition date.
     *
     * @param plateString The plate ID string to validate.
     * @param date        The acquisition date of the vehicle.
     * @return True if the plate format is valid for the given acquisition date, false otherwise.
     */
    private static boolean isValidPlateFormat(String plateString, String date) {

        int length = date.length();
        int year =Integer.parseInt( date.substring(length - 4));
        String regex;
        if (year > 2020) {
            regex = "[A-Z]{2}-\\d{2}-[A-Z]{2}";
        } else if (year >= 2005) {
            regex = "\\d{2}-[A-Z]{2}-\\d{2}";
        } else if (year >= 1992) {
            regex = "\\d{2}-\\d{2}-[A-Z]{2}";
        }else {
            regex = "^(([A-Z]{2}-\\d{2}-(\\d{2}|[A-Z]{2}))|(\\d{2}-(\\d{2}-[A-Z]{2}|[A-Z]{2}-\\d{2})))$";
        }
        Pattern p = Pattern.compile(regex);
        if (plateString == null) {
            return false;
        }
        Matcher m = p.matcher(plateString);
        if(!m.matches()){
            System.out.println("Invalid plate format!");
        }
        return m.matches();
    }

    /**
     * Validates the format of the date string.
     *
     * @param dateString The date string to validate.
     * @return True if the date format is valid, false otherwise.
     */
    private static boolean isValidDateFormat(String dateString) {
        String regex = "^(0[1-9]|[1-2][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$";
        Pattern p = Pattern.compile(regex);
        if (dateString == null) {
            return false;
        }
        Matcher m = p.matcher(dateString);
        if(!m.matches()){
            System.out.println("Invalid date format!");
        }
        return m.matches();
    }
}
