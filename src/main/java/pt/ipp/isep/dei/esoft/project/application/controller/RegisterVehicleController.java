package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.DocumentTypeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;
import pt.ipp.isep.dei.esoft.project.repository.VehicleTypeRepository;

import java.util.ArrayList;
import java.util.Arrays;

public class RegisterVehicleController {
    private final VehicleRepository vehicleRepository;

    public RegisterVehicleController(){
        this.vehicleRepository = Repositories.getInstance().getVehicleRepository();
    }
    public boolean registerVehicle(String brand, String model, String vehicleID, int type, double grossWeight, double tare, int currentKm, String registerDate, String acquisitionDate, int checkupFrequency){
        return vehicleRepository.registerVehicle(brand, model, vehicleID, getVehicleTypeList().get(type-1), grossWeight, tare, currentKm, registerDate, acquisitionDate, checkupFrequency);
    }

    public ArrayList<VehicleTypeRepository> getVehicleTypeList(){
        return new ArrayList<>(Arrays.asList(VehicleTypeRepository.values()));
    }

}
