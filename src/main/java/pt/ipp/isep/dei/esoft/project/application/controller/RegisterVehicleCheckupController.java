package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.Date;

public class RegisterVehicleCheckupController {
    private VehicleRepository vehicleRepository;

    public RegisterVehicleCheckupController(){ this.vehicleRepository= Repositories.getInstance().getVehicleRepository();}

    public ArrayList<Vehicle> getVehicles(){return vehicleRepository.getVehiclesNeedingCheckup(vehicleRepository.getVehicleList());
    }

    public boolean createVehicleCheckup(Vehicle vehicle, Date date, int currentKm){
        if (vehicle != null) {
            vehicle.addCheckup(vehicle,date, currentKm);
            return true;
        }
        return false;

    }
}
