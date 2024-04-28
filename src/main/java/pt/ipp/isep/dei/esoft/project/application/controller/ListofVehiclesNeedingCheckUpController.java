package pt.ipp.isep.dei.esoft.project.application.controller;


import java.util.ArrayList;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

/**
 * Controller class responsible for Listing the Vehicles needing a Check-Up and their respective characteristics
 */
public class ListofVehiclesNeedingCheckUpController {
    private VehicleRepository vehicleRepository;

    /**
     * Constructs a new ListofVehiclesNeedingCheckUpController
     */
    public ListofVehiclesNeedingCheckUpController(){
        this.vehicleRepository = Repositories.getInstance().getVehicleRepository();
    }

    /**
     * Retrieves a list of vehicles needing a check-up
     * @return ArrayList of Vehicles objects representing vehicles needing a check-up
     */
    public  ArrayList<Vehicle> getVehicles() {
        return vehicleRepository.getVehiclesNeedingCheckup();
    }

}
