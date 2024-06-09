package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;
import pt.ipp.isep.dei.esoft.project.repository.enums.VehicleTypeENUM;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository implements Serializable {

    private final ArrayList<Vehicle> vehicleList = new ArrayList<>();

    public ArrayList<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public boolean registerVehicle(String brand, String model, String vehicleID, VehicleTypeENUM type, double grossWeight, double tare, int currentKm, String registerDate, String acquisitionDate, int checkupFrequency) {
        Vehicle vehicle = new Vehicle(brand, model, vehicleID, type, grossWeight, tare, currentKm, registerDate, acquisitionDate, checkupFrequency);
        if (isVehicleUnique(vehicle)) {
            return vehicleList.add(vehicle);
        }
        return false;
    }

    private boolean isVehicleUnique(Vehicle vehicle) {
        for (Vehicle v : vehicleList) {
            if (v.equals(vehicle)) {
                return false;
            }
        }
        return true;
    }

    public int size() {
        return this.vehicleList.size();
    }

    public ArrayList<Vehicle> getVehiclesNeedingCheckup() {
        ArrayList<Vehicle> vehiclesNeedingCheckup = new ArrayList<>();
        for (Vehicle v : this.vehicleList) {
            if (v.needsCheckup()) {
                vehiclesNeedingCheckup.add(v);
            }
        }
        return vehiclesNeedingCheckup;
    }

    public boolean createVehicleCheckup(Vehicle vehicle, LocalDate date, int currentKm) {
        VehicleCheckup vehicleCheckup = new VehicleCheckup(date, currentKm);
        if (vehicle != null) {
            vehicle.addCheckup(vehicleCheckup);
            return true;
        }
        return false;
    }

    public boolean updateVehicleCurrentKm(Vehicle vehicle, int currentKm) {
        if (vehicle != null) {
            vehicle.setCurrentKm(currentKm);
            return true;
        }
        return false;
    }

    public ArrayList<Vehicle> getVehiclesByIds(List<String> vehicleIds) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        for (String id : vehicleIds) {
            for (Vehicle vehicle : vehicleList) {
                if (vehicle.getVehicleID().equals(id)) {
                    vehicles.add(vehicle);
                    break;
                }
            }
        }
        return vehicles;
    }

 }
