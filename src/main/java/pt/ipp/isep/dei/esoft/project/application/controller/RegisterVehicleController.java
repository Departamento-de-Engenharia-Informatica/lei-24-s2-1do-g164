package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

public class RegisterVehicleController {
    private final VehicleRepository vehicleRepository;

    public RegisterVehicleController(){
        this.vehicleRepository = Repositories.getInstance().getVehicleRepository();
    }
    public boolean registerVehicle(String brand, String model, String vehicleID, String type, double grossWeight, double tare, int currentKm, String registerDate, String acquisitionDate, int checkupFrequency){
        Vehicle vehicle = new Vehicle(brand, model, vehicleID, type, grossWeight, tare, currentKm, registerDate, acquisitionDate, checkupFrequency);
        return vehicleRepository.registerVehicle(vehicle);
    }
}
