package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;
import pt.ipp.isep.dei.esoft.project.repository.enums.VehicleTypeENUM;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

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
    public boolean registerVehicle(String brand, String model, String vehicleID, VehicleTypeENUM type, double grossWeight, double tare, int currentKm, String registerDate, String acquisitionDate, int checkupFrequency) {
        Vehicle vehicle = new Vehicle(brand, model, vehicleID, type, grossWeight, tare, currentKm, registerDate, acquisitionDate, checkupFrequency);
        if (isVehicleUnique(vehicle)) {
            return vehicleList.add(vehicle);
        }
        return false;

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


    /**
     * Creates a new vehicle checkup for the specified vehicle with the given date and current kilometers.
     *
     * @param vehicle   The vehicle for which the checkup is to be created.
     * @param date      The date of the checkup.
     * @param currentKm The current kilometers of the vehicle at the time of the checkup.
     * @return true if the checkup is successfully created, false otherwise.
     */
    public boolean createVehicleCheckup(Vehicle vehicle, LocalDate date, int currentKm) {
        var vehicleCheckup = new VehicleCheckup(date, currentKm);
        if (vehicle != null) {
            vehicle.addCheckup(vehicleCheckup);
            return true;
        }
        return false;
    }

    /**
     * Updates the current kilometers of a vehicle.
     *
     * @param vehicle   The vehicle for which to update the current kilometers.
     * @param currentKm The updated current kilometers value.
     * @return true if the current kilometers are successfully updated, false otherwise.
     */
    public boolean updateVehicleCurrentKm(Vehicle vehicle, int currentKm) {
        if (vehicle != null) {
            vehicle.setCurrentKm(currentKm);
            return true;
        }
        return false;
    }
}
