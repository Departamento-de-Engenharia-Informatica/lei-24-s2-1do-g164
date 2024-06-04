package pt.ipp.isep.dei.esoft.project.mappers;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDTO;

import java.util.ArrayList;
import java.util.List;

public class VehicleMapper {


    public VehicleDTO toDTO(Vehicle vehicle){
        return new VehicleDTO(vehicle.getVehicleID(), vehicle.getModel(), vehicle.getBrand(), vehicle.getType(), vehicle.getGrossWeight(), vehicle.getTare(), vehicle.getCurrentKm(), vehicle.getRegisterDate(), vehicle.getAcquisitionDate(), vehicle.getCheckupFrequency());
    }


    public ArrayList<VehicleDTO> toDtoList(List<Vehicle> vehicleList){
        ArrayList<VehicleDTO> vehicleDTOList = new ArrayList<>();
        for(Vehicle v : vehicleList){
            vehicleDTOList.add(toDTO(v));
        }
        return vehicleDTOList;
    }

}
