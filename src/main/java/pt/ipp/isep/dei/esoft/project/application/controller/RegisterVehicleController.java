package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.dto.CreateVehicleDTO;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.repository.ENUM.VehicleTypeRepository;

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
     * Register vehicle boolean.
     *
     * @param dto  the dto
     * @param type the type
     * @return the boolean
     */
    public boolean registerVehicle(CreateVehicleDTO dto, VehicleTypeRepository type){
        return vehicleRepository.registerVehicle(dto.brand, dto.model, dto.vehicleID, type, dto.grossWeight, dto.tare,
                dto.currentKm, dto.registerDate, dto.acquisitionDate, dto.checkupFrequency);
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
