package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.io.Serializable;
import java.util.ArrayList;

public class VehicleRepository implements Serializable {
    private final ArrayList<Vehicle> vehicleList = new ArrayList<>();

    public ArrayList<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public boolean registerVehicle(String brand, String model, String vehicleID, VehicleTypeRepository type, double grossWeight, double tare, int currentKm, String registerDate, String acquisitionDate, int checkupFrequency) {
        Vehicle vehicle = new Vehicle(brand, model, vehicleID, type, grossWeight, tare, currentKm, registerDate, acquisitionDate, checkupFrequency);
        if (vehicleIsUnique(vehicle) && isValidDateFormat(acquisitionDate) && isValidDateFormat(registerDate)) {
            vehicleList.add(vehicle);
            return true;
        } else {
            return false;
        }
    }

    private boolean vehicleIsUnique(Vehicle vehicle) {
        for (Vehicle v : vehicleList) {
            if (v.equals(vehicle)) {
                return false;
            }
        }
        return true;
    }

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

    private static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public int size() {
        return this.vehicleList.size();
    }

    public ArrayList<Vehicle> getVehiclesNeedingCheckup(ArrayList<Vehicle> vehicles) {
        ArrayList<Vehicle> vehiclesNeedingCheckup = new ArrayList<>();

        for (Vehicle v : vehicles) {
            if (v.needsCheckup()) {
                vehiclesNeedingCheckup.add(v);
            }
        }
        return vehiclesNeedingCheckup;
    }
}

