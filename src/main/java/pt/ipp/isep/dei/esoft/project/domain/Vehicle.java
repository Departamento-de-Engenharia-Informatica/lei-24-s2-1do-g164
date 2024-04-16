package pt.ipp.isep.dei.esoft.project.domain;

public class Vehicle {
    private String brand;
    private String model;
    private String vehicleID;
    private String type;
    private double grossWeight;
    private double tare;
    private int currentKm;
    private String registerDate;
    private String acquisitionDate;
    private int checkupFrequency;
    private VehicleCheckup lastCheckup;
    public Vehicle(String brand, String model, String vehicleID, String type, double grossWeight, double tare, int currentKm, String registerDate, String acquisitionDate, int checkupFrequency) {
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
        this.lastCheckup = new VehicleCheckup("No checkup registered", 0);
    }


}
