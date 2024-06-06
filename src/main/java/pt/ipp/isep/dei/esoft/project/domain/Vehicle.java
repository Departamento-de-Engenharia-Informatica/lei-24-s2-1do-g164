package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.enums.VehicleTypeENUM;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Vehicle implements Serializable {

    private final String brand;
    private final String model;
    private final String vehicleID;
    private final VehicleTypeENUM type;
    private final double grossWeight;
    private final double tare;
    private int currentKm;
    private final String registerDate;
    private final String acquisitionDate;
    private final int checkupFrequency;
    private final List<VehicleCheckup> checkups = new ArrayList<>();

    public Vehicle(String brand, String model, String vehicleID, VehicleTypeENUM type, double grossWeight, double tare, int currentKm, String registerDate, String acquisitionDate, int checkupFrequency) {
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

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public VehicleTypeENUM getType() {
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

    public void setCurrentKm(int currentKm) {
        this.currentKm = currentKm;
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

    public List<VehicleCheckup> getCheckups() {
        return new ArrayList<>(checkups);
    }

    public void addCheckup(VehicleCheckup checkup) {
        checkups.add(checkup);
    }

    public VehicleCheckup getLastCheckup() {
        if (checkups.isEmpty()) {
            return null;
        }
        return checkups.get(checkups.size() - 1);
    }

    public boolean needsCheckup() {
        if (checkups.isEmpty()) {
            return true;
        }
        VehicleCheckup latestCheckup = checkups.get(checkups.size() - 1);
        int kmSinceLastCheckup = currentKm - latestCheckup.getCurrentKms();
        return kmSinceLastCheckup >= checkupFrequency;
    }



    @Override
    public String toString() {
        return
                brand + " " +
                model + " (" + vehicleID +")" +
                " - Current Km: " + currentKm +
                " - Checkup Frequency: " + checkupFrequency;
    }
}
