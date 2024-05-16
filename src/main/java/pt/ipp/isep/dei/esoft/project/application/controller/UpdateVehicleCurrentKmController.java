package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.ArrayList;

/**
 * The controller class responsible for updating a vehicle's current kilometers.
 * It communicates with the vehicle repository to perform the update operation.
 */

public class UpdateVehicleCurrentKmController {
    private VehicleRepository vehicleRepository;
    /**
     * Constructs an instance of UpdateVehicleCurrentKmController.
     * It initializes the vehicle repository.
     */
    public UpdateVehicleCurrentKmController() {
        getVehicleRepository();
    }

    /**
     * Gets the vehicle repository instance.
     * If not already initialized, it retrieves it from the Repositories singleton.
     * @return The vehicle repository instance.
     */
    private VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();
            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }

    /**
     * Updates the current kilometers of a vehicle.
     * @param vehicle The vehicle to be updated.
     * @param currentKms The new current kilometers for the vehicle.
     * @return True if the update operation was successful, false otherwise.
     */

    public boolean updateVehicleCurrentKm(Vehicle vehicle, int currentKms){
        return vehicleRepository.updateVehicleCurrentKm(vehicle, currentKms);
    }


    /**
     * Retrieves a list of vehicles from the repository.
     * @return An ArrayList of vehicles.
     */
    public ArrayList<Vehicle> getVehicleList(){
        return vehicleRepository.getVehicleList();
    }
}
