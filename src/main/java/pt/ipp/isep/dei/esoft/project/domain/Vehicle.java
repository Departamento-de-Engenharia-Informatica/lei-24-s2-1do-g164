package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.VehicleTypeRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Optional;

/**
 * Represents a vehicle used within a project.
 */
public class Vehicle {

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
    private ArrayList<VehicleCheckup> checkupList;

    private final double PERCENTAGEM_TOLERANCIA = 0.05;

    /**
     * Constructs a new Vehicle instance with specified details.
     *
     * @param brand            The brand of the vehicle.
     * @param model            The model of the vehicle.
     * @param vehicleID        The unique identifier of the vehicle.
     * @param type             The type of the vehicle.
     * @param grossWeight      The gross weight of the vehicle.
     * @param tare             The tare weight of the vehicle.
     * @param currentKm        The current kilometers driven by the vehicle.
     * @param registerDate     The date when the vehicle was registered.
     * @param acquisitionDate  The date when the vehicle was acquired.
     * @param checkupFrequency The frequency (in kilometers) for vehicle checkups.
     */
    public Vehicle(String brand, String model, String vehicleID, VehicleTypeRepository type, double grossWeight, double tare, int currentKm, String registerDate, String acquisitionDate, int checkupFrequency) {
        this.brand = brand;
        this.model = model;
        this.vehicleID = vehicleID;
        this.type = type;
        this.grossWeight = grossWeight;
        this.tare = tare;
        this.currentKm = currentKm;
        this.registerDate = registerDate;
        this.acquisitionDate = acquisitionDate;
        this.checkupFrequency = checkupFrequency;
        this.checkupList = new ArrayList<>();
        this.checkupList.add(new VehicleCheckup(LocalDate.now(), 0));
    }

    /**
     * Retrieves the brand of the vehicle.
     *
     * @return The brand of the vehicle.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Retrieves the model of the vehicle.
     *
     * @return The model of the vehicle.
     */
    public String getModel() {
        return model;
    }

    /**
     * Retrieves the unique identifier of the vehicle.
     *
     * @return The vehicle's unique identifier.
     */
    public String getVehicleID() {
        return vehicleID;
    }

    /**
     * Retrieves the type of the vehicle.
     *
     * @return The type of the vehicle.
     */
    public VehicleTypeRepository getType() {
        return type;
    }

    /**
     * Retrieves the gross weight of the vehicle.
     *
     * @return The gross weight of the vehicle.
     */
    public double getGrossWeight() {
        return grossWeight;
    }

    /**
     * Retrieves the tare weight of the vehicle.
     *
     * @return The tare weight of the vehicle.
     */
    public double getTare() {
        return tare;
    }

    /**
     * Retrieves the current kilometers driven by the vehicle.
     *
     * @return The current kilometers driven.
     */
    public int getCurrentKm() {
        return currentKm;
    }

    /**
     * Retrieves the registration date of the vehicle.
     *
     * @return The registration date of the vehicle.
     */
    public String getRegisterDate() {
        return registerDate;
    }

    /**
     * Retrieves the acquisition date of the vehicle.
     *
     * @return The acquisition date of the vehicle.
     */
    public String getAcquisitionDate() {
        return acquisitionDate;
    }

    /**
     * Retrieves the frequency (in kilometers) for vehicle checkups.
     *
     * @return The checkup frequency.
     */
    public int getCheckupFrequency() {
        return checkupFrequency;
    }

    /**
     * Retrieves the last performed checkup on the vehicle.
     *
     * @return The last performed checkup.
     */


    /**
     * Checks if the vehicle needs a checkup based on its current kilometers driven and checkup frequency.
     *
     * @return {@code true} if the vehicle needs a checkup, {@code false} otherwise.
     */
    public boolean needsCheckup() {
        int difference = this.currentKm - this.checkupList.get(checkupList.size()-1).getCurrentKms();
        double percentageInKm = PERCENTAGEM_TOLERANCIA * this.checkupFrequency;

        return difference + percentageInKm >= this.checkupFrequency;
    }

    /**
     * Adds a vehicle checkup to the list of checkups and updates the current kilometers of the vehicle.
     *
     * @param vehicleCheckup The vehicle checkup to add.
     */
    public void addCheckup(VehicleCheckup vehicleCheckup) {
        this.checkupList.add(vehicleCheckup);
        this.currentKm = vehicleCheckup.getCurrentKms();
    }

    /**
     * Compares this vehicle with another object for equality.
     *
     * @param vehicle The object to compare with.
     * @return {@code true} if the vehicles have the same vehicleID, {@code false} otherwise.
     */
    public boolean equals(Vehicle vehicle) {
        return vehicle.getVehicleID().equals(this.vehicleID);
    }

    /**
     *
     * @return all the vehicle essential details
     */
    @Override
    public String toString() {
        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = this.checkupList.get(checkupList.size()-1).getDate().format(formatter);

        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", vehicleID='" + vehicleID + '\'' +
                ", type=" + type +
                ", grossWeight=" + grossWeight +
                ", tare=" + tare +
                ", currentKm=" + currentKm +
                ", registerDate='" + registerDate + '\'' +
                ", acquisitionDate='" + acquisitionDate + '\'' +
                ", checkupFrequency=" + checkupFrequency +
                ", Last Checkup: " + date +
                '}';
    }

    public void setCurrentKm(int currentKm){
        this.currentKm = currentKm;
    }

    public VehicleCheckup getLastCheckup(){
        return this.checkupList.get(checkupList.size()-1);
    }

}



