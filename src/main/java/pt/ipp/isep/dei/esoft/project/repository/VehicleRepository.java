package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * Repository class for managing vehicles.
 */
public class VehicleRepository implements Serializable {
    private final ArrayList<Vehicle> vehicleList = new ArrayList<>();

    /**
     * Retrieves the list of vehicles in the repository.
     *
     * @return The list of vehicles.
     */
    public ArrayList<Vehicle> getVehicleList() {
        return vehicleList;
    }

    /**
     * Registers a new vehicle with the provided details.
     *
     * @param brand            The brand of the vehicle.
     * @param model            The model of the vehicle.
     * @param vehicleID        The unique identifier of the vehicle.
     * @param type             The type of the vehicle.
     * @param grossWeight      The gross weight of the vehicle.
     * @param tare             The tare weight of the vehicle.
     * @param currentKm        The current kilometers of the vehicle.
     * @param registerDate     The registration date of the vehicle (in "DD-MM-YYYY" format).
     * @param acquisitionDate  The acquisition date of the vehicle (in "DD-MM-YYYY" format).
     * @param checkupFrequency The checkup frequency of the vehicle (in kilometers).
     * @return {@code true} if the vehicle is successfully registered, {@code false} otherwise.
     */
    public boolean registerVehicle(String brand, String model, String vehicleID, VehicleTypeRepository type, double grossWeight, double tare, int currentKm, String registerDate, String acquisitionDate, int checkupFrequency) {
        Vehicle vehicle = new Vehicle(brand, model, vehicleID, type, grossWeight, tare, currentKm, registerDate, acquisitionDate, checkupFrequency);
        if (isVehicleUnique(vehicle)) {
            vehicleList.add(vehicle);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the given vehicle is unique (not already registered).
     *
     * @param vehicle The vehicle to check.
     * @return {@code true} if the vehicle is unique, {@code false} if it already exists.
     */
    private boolean isVehicleUnique(Vehicle vehicle) {
        for (Vehicle v : vehicleList) {
            if (v.equals(vehicle)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Validates if the provided date string has a valid "DD-MM-YYYY" format.
     *
     * @param dateString The date string to validate.
     * @return {@code true} if the date format is valid, {@code false} otherwise.
     */


    /**
     * Retrieves the number of vehicles in the repository.
     *
     * @return The number of vehicles.
     */
    public int size() {
        return this.vehicleList.size();
    }

    /**
     * Retrieves vehicles that need a checkup based on their current state.
     *
     * @param vehicles The list of vehicles to check.
     * @return The list of vehicles that need a checkup.
     */
    public ArrayList<Vehicle> getVehiclesNeedingCheckup() {
        ArrayList<Vehicle> vehiclesNeedingCheckup = new ArrayList<>();

        for (Vehicle v : this.vehicleList) {
            if (v.needsCheckup()) {
                vehiclesNeedingCheckup.add(v);
            }
        }
        return vehiclesNeedingCheckup;
    }

    public ArrayList<Vehicle> getVehiclesWithoutBookedCheckup() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle v : this.vehicleList) {
            var checkup = v.getLastCheckup();
            if (checkup.isEmpty()) {
                vehicles.add(v);
            } else if (checkup.get().getDate().isBefore(LocalDate.now())) {
                vehicles.add(v);
            }
        }
        return vehicles;
    }

    public boolean createVehicleCheckup(Vehicle vehicle, LocalDate date, int currentKm){
        if (vehicle != null) {
            vehicle.addCheckup(date, currentKm);
            return true;
        }
        return false;
    }

//    public boolean createVehicleCheckup(Vehicle vehicle, Date date, int currentKm) {
//        if (vehicle != null && validar cenas){
//            vehicle.addCheckup(vehicle, date, currentKm);
//            return true;
//        }
//        return false;
//
//
//    }
}
