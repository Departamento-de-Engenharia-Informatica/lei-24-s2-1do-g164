package pt.ipp.isep.dei.esoft.project.dto;
import pt.ipp.isep.dei.esoft.project.repository.enums.VehicleTypeENUM;
public class VehicleDTO {

    private String brand;
    private String model;
    private String vehicleID;
    private VehicleTypeENUM type;
    private double grossWeight;
    private double tare;
    private int currentKm;
    private String registerDate;
    private String acquisitionDate;
    private int checkupFrequency;

    // Getters and setters
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
}