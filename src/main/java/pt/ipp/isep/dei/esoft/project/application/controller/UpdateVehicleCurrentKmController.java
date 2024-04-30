package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.ArrayList;

public class UpdateVehicleCurrentKmController {
    private VehicleRepository vehicleRepository;

    public UpdateVehicleCurrentKmController() {
        getVehicleRepository();
    }

    private VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();
            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }

    public boolean updateVehicleCurrentKm(Vehicle vehicle, int currentKms){
        return vehicleRepository.updateVehicleCurrentKm(vehicle, currentKms);
    }

    public ArrayList<Vehicle> getVehicleList(){
        return vehicleRepository.getVehicleList();
    }
}
