package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class RegisterVehicleCheckupController {
    private VehicleRepository vehicleRepository;

    /**
     * Constructs a RegisterVehicleCheckupController instance.
     * Initializes the vehicleRepository using the singleton Repositories class.
     */
    public RegisterVehicleCheckupController(){
        this.vehicleRepository= Repositories.getInstance().getVehicleRepository();
    }

    /**
     * Retrieves a list of vehicles without booked checkups.
     *
     * @return An ArrayList of vehicles without booked checkups.
     */
    public ArrayList<Vehicle> getVehicles(){
        return vehicleRepository.getVehiclesWithoutBookedCheckup();
    }
    /**
     * Creates a vehicle checkup record for the specified vehicle.
     *
     * @param vehicle    The vehicle for which the checkup is to be created.
     * @param date       The date of the checkup.
     * @param currentKm  The current kilometers reading of the vehicle.
     * @return True if the checkup was successfully created, false otherwise.
     */
    public boolean createVehicleCheckup(Vehicle vehicle, LocalDate date, int currentKm){
        return vehicleRepository.createVehicleCheckup(vehicle, date, currentKm);
    }
}
