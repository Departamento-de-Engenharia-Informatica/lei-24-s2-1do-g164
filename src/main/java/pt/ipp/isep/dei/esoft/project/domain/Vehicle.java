package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.VehicleTypeRepository;

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
    private VehicleCheckup lastCheckup;
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
        this.lastCheckup = new VehicleCheckup("No checkup registered", 0);
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public VehicleTypeRepository getType() {
        return type;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public double getTare() {
        return tare;
    }

    public int getCurrentKm() {
        return currentKm;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public String getAcquisitionDate() {
        return acquisitionDate;
    }

    public int getCheckupFrequency() {
        return checkupFrequency;
    }

    public VehicleCheckup getLastCheckup() {
        return lastCheckup;
    }

    public boolean equals(Vehicle vehicle){
        return vehicle.getVehicleID().equals(this.vehicleID);
    }
    public boolean needsCheckup() {
        int difference = this.currentKm - this.lastCheckup.getCurrentKms();
        double percentage = (double) difference / checkupFrequency;
        return difference >= checkupFrequency || percentage < 0.05;
    }
}

