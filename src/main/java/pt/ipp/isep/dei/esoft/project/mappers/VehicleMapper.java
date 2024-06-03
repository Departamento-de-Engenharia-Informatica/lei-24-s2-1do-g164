package pt.ipp.isep.dei.esoft.project.mappers;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDTO;
import pt.ipp.isep.dei.esoft.project.repository.enums.VehicleTypeENUM;

import java.util.ArrayList;
import java.util.List;

public class VehicleMapper {
    public static List<VehicleDTO> toDtoList(List<Vehicle> vehicles) {
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            vehicleDTOList.add(toDTO(vehicle));
        }
        return vehicleDTOList;
    }

    public static VehicleDTO toDTO(Vehicle vehicle) {
        VehicleDTO dto = new VehicleDTO();
        dto.setBrand(vehicle.getBrand());
        dto.setModel(vehicle.getModel());
        dto.setVehicleID(vehicle.getVehicleID());
        dto.setType(vehicle.getType());
        dto.setGrossWeight(vehicle.getGrossWeight());
        dto.setTare(vehicle.getTare());
        dto.setCurrentKm(vehicle.getCurrentKm());
        dto.setRegisterDate(vehicle.getRegisterDate());
        dto.setAcquisitionDate(vehicle.getAcquisitionDate());
        dto.setCheckupFrequency(vehicle.getCheckupFrequency());
        return dto;
    }
}
