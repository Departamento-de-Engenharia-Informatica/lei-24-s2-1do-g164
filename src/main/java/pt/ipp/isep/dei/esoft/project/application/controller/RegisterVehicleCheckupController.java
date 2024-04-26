package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class RegisterVehicleCheckupController {
    private VehicleRepository vehicleRepository;

    public RegisterVehicleCheckupController(){
        this.vehicleRepository= Repositories.getInstance().getVehicleRepository();
    }

    public ArrayList<Vehicle> getVehicles(){
        return vehicleRepository.getVehiclesWithoutBookedCheckup();
    }

    public boolean createVehicleCheckup(Vehicle vehicle, LocalDate date, int currentKm){
        if (vehicle != null) {
            vehicle.addCheckup(date, currentKm);
            return true;
        }
        return false;
    }
}
