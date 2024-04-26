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
        if (isVehicleUnique(vehicle) && isValidDateFormat(acquisitionDate) && isValidDateFormat(registerDate)) {
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
    public static boolean isValidDateFormat(String dateString) {
        if (dateString.length() != 10) {
            return false;
        }

        String dayStr = dateString.substring(0, 2);
        String monthStr = dateString.substring(3, 5);
        String yearStr = dateString.substring(6);

        if (!isNumeric(dayStr) || !isNumeric(monthStr) || !isNumeric(yearStr)) {
            return false;
        }

        int day = Integer.parseInt(dayStr);
        int month = Integer.parseInt(monthStr);
        int year = Integer.parseInt(yearStr);

        if (month < 1 || month > 12 || day < 1 || day > 31) {
            return false;
        }

        return dateString.charAt(2) == '-' && dateString.charAt(5) == '-';
    }

    /**
     * Helper method to check if a string is numeric.
     *
     * @param str The string to check.
     * @return {@code true} if the string is numeric, {@code false} otherwise.
     */
    private static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
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
