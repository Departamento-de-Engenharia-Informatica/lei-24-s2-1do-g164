package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleController;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;
import pt.ipp.isep.dei.esoft.project.repository.DocumentTypeRepository;
import pt.ipp.isep.dei.esoft.project.repository.VehicleTypeRepository;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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

    public RegisterVehicleUI(){
        controller = new RegisterVehicleController();
    }
    private RegisterVehicleController getController(){
        return controller;
    }
    private void displayVehicleTypeList(List<VehicleTypeRepository> vehicleTypeList){
        int i = 1;
        for (VehicleTypeRepository vType : vehicleTypeList){
            System.out.println("  " + i + " - " + vType);
            i++;
        }
    }

    private VehicleTypeRepository displayAndSelectVehicleType(){
        List<VehicleTypeRepository> vehicleTypeList = controller.getVehicleTypesList();
        int listSize = vehicleTypeList.size();
        int answer = -1;

        Scanner sc = new Scanner(System.in);

        while(answer < 1 || answer > listSize){
            displayVehicleTypeList(vehicleTypeList);
            System.out.println("Select the vehicle's type: ");
            answer = sc.nextInt();
        }

        type = vehicleTypeList.get(answer - 1);
        return type;
    }

    private int requestCurrentKm() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter Current Kilometers: ");
            return sc.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("Must be a number!");
            System.out.println("Enter Current Kilometers: ");
            sc.nextLine();
            return sc.nextInt();
        }
    }

    private int requestCheckupFrequency() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter Checkup Frequency (km): ");
            return sc.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("Must be a number!");
            System.out.println("Enter Checkup Frequency (km): ");
            sc.nextLine();
            return sc.nextInt();
        }
    }

    private double requestGrossWeight() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter Gross Weight (kg): ");
            return sc.nextDouble();
        }
        catch (InputMismatchException e){
            System.out.println("Must be a number!");
            System.out.println("Enter Gross Weight (kg): ");
            sc.nextLine();
            return sc.nextDouble();
        }
    }

    private double requestTareWeight() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter Tare Weight (kg): ");
            return sc.nextDouble();
        }
        catch (InputMismatchException e){
            System.out.println("Must be a number!");
            System.out.println("Enter Tare Weight (kg): ");
            sc.nextLine();
            return sc.nextDouble();
        }
    }

    private String requestAcquisitionDate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Acquisition Date (DD-MM-YY): ");
        return sc.nextLine();
    }

    private String requestRegisterDate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Register Date (DD-MM-YY): ");
        return sc.nextLine();
    }

    private String requestBrand(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Brand: ");
        return sc.nextLine();
    }

    private String requestModel(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Model: ");
        return sc.nextLine();
    }

    private String requestVehicleID(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Plate ID (XX-XX-XX): ");
        return sc.nextLine();
    }

    private void requestData() {
        Scanner sc = new Scanner(System.in);

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

    private void submitData() {
        boolean success = controller.registerVehicle(brand, model, vehicleID, type, grossWeight, tare, currentKm, registerDate, acquisitionDate, checkupFrequency);
        if(success){
            System.out.println("\nVehicle successfully registered!");
        }
        else{
            System.out.println("\nVehicle registration failed!");
        }
    }

    public void run() {
        System.out.println("\n\n--- Register Vehicle ------------------------");
        displayAndSelectVehicleType();
        requestData();
        submitData();
    }
}
