package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.repository.enums.VehicleTypeENUM;

public class VehicleDTO {

    public String brand;
    public String model;
    public String vehicleID;
    public VehicleTypeENUM type;
    public double grossWeight;
    public double tare;
    public int currentKm;
    public String registerDate;
    public String acquisitionDate;
    public int checkupFrequency;

        public VehicleDTO() {
        }

    public VehicleDTO(String brand, String model, String vehicleID, VehicleTypeENUM type, double grossWeight, double tare, int currentKm, String registerDate, String acquisitionDate, int checkupFrequency) {
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
    }

    // Getters e setters
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public VehicleTypeENUM getType() {
        return type;
    }

    public void setType(VehicleTypeENUM type) {
        this.type = type;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public double getTare() {
        return tare;
    }

    public void setTare(double tare) {
        this.tare = tare;
    }

    public int getCurrentKm() {
        return currentKm;
    }

    public void setCurrentKm(int currentKm) {
        this.currentKm = currentKm;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(String acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public int getCheckupFrequency() {
        return checkupFrequency;
    }

    public void setCheckupFrequency(int checkupFrequency) {
        this.checkupFrequency = checkupFrequency;
    }

    @Override
    public String toString() {
        return brand + " " + model + " (" + vehicleID + ")";
    }
}
