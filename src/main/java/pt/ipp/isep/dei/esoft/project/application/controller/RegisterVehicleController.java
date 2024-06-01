package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.dto.CreateVehicleDTO;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;
import pt.ipp.isep.dei.esoft.project.repository.enums.VehicleTypeENUM;

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
    public RegisterVehicleController() {
        this.vehicleRepository = Repositories.getInstance().getVehicleRepository();
    }


    /**
     * Register vehicle.
     *
     * @param dto  the dto containing vehicle details
     * @param type the type of the vehicle
     * @return true if the vehicle is successfully registered, false otherwise
     */
    public boolean registerVehicle(CreateVehicleDTO dto, VehicleTypeENUM type) {
        return vehicleRepository.registerVehicle(dto.brand, dto.model, dto.vehicleID, type, dto.grossWeight, dto.tare,
                dto.currentKm, dto.registerDate, dto.acquisitionDate, dto.checkupFrequency);
    }

    /**
     * Retrieves a list of available vehicle types.
     *
     * @return An ArrayList containing the available vehicle types.
     */
    public ArrayList<VehicleTypeENUM> getVehicleTypesList() {
        return new ArrayList<>(Arrays.asList(VehicleTypeENUM.values()));
    }
}
