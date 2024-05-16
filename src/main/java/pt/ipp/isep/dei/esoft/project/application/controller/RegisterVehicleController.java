package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Controller class responsible for handling vehicle registration operations.
 */
public class RegisterVehicleController {
    private VehicleRepository vehicleRepository;

    /**
     * Constructs a new RegisterVehicleController.
     */
    public RegisterVehicleController(){
        getVehicleRepository();
    }

    /**
     * Registers a new vehicle with the provided details.
     *
     * @param brand           The brand of the vehicle.
     * @param model           The model of the vehicle.
     * @param vehicleID       The vehicle ID.
     * @param type            The type of the vehicle (enum).
     * @param grossWeight     The gross weight of the vehicle.
     * @param tare            The tare weight of the vehicle.
     * @param currentKm       The current kilometers of the vehicle.
     * @param registerDate    The registration date of the vehicle (in "DD-MM-YYYY" format).
     * @param acquisitionDate The acquisition date of the vehicle (in "DD-MM-YYYY" format).
     * @param checkupFrequency The checkup frequency of the vehicle (in kilometers).
     * @return {@code true} if the vehicle is successfully registered, {@code false} otherwise.
     */
    public boolean registerVehicle(String brand, String model, String vehicleID, VehicleTypeRepository type, double grossWeight, double tare, int currentKm, String registerDate, String acquisitionDate, int checkupFrequency){
        return vehicleRepository.registerVehicle(brand, model, vehicleID, type, grossWeight, tare, currentKm, registerDate, acquisitionDate, checkupFrequency);
    }

    /**
     * Retrieves a list of available vehicle types.
     *
     * @return An ArrayList containing the available vehicle types.
     */
    public ArrayList<VehicleTypeRepository> getVehicleTypesList(){
        return new ArrayList<>(Arrays.asList(VehicleTypeRepository.values()));
    }

    /**
     * Retrieves the vehicle repository instance.
     *
     * If the vehicle repository instance is not initialized, it initializes
     * it by obtaining it from the Repositories singleton class. Subsequent calls
     * return the already initialized repository instance.
     *
     * @return the vehicle repository instance
     */
    private VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();
            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }
}
