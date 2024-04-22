package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.io.Serializable;
import java.util.ArrayList;

public class VehicleRepository implements Serializable {
    private final ArrayList<Vehicle> vehicleList = new ArrayList<>();

    public ArrayList<Vehicle> getVehicleList(){
        return vehicleList;
    }

    public boolean registerVehicle(Vehicle vehicle){
        if (validateVehicle(vehicle)){

            vehicleList.add(vehicle);

            return true;
        }else{
            return false;
        }
    }

    private boolean validateVehicle(Vehicle vehicle) {
        for (Vehicle v : vehicleList){
            if (v.equals(vehicle)){
                return true;
            }
        }
        return false;
    }
    public int size(){
        return this.vehicleList.size();
    }

    public ArrayList<Vehicle> getVehiclesNeedingCheckup(ArrayList<Vehicle> vehicles) {
        ArrayList<Vehicle> vehiclesNeedingCheckup = new ArrayList<>();

        for (Vehicle v: vehicles) {
            if (v.needsCheckup()){
                vehiclesNeedingCheckup.add(v);
            }
        }
        return vehiclesNeedingCheckup;
    }

    public boolean vehicleAlreadyExist(Vehicle vehicle){
            for(Vehicle v: vehicleList) {
            if (v.equals(vehicle)) {
                return true;
            }
        }
        return false;
    }

}
