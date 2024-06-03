package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDTO;
import pt.ipp.isep.dei.esoft.project.mappers.VehicleMapper;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;

public class AssignVehiclesController {
    private final VehicleRepository vehicleRepository;

    public AssignVehiclesController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<VehicleDTO> getVehicles() {
        List<Vehicle> vehicles = vehicleRepository.getVehicleList();
        return VehicleMapper.toDtoList(vehicles);
    }

    public void assignVehiclesToEntity(List<String> vehicleIds, String entityId) {
        List<Vehicle> vehicles = vehicleRepository.getVehiclesByIds(vehicleIds);
        // Lógica para associar veículos à entidade (por exemplo, motorista, rota, etc.)
        // Isso dependerá da estrutura e lógica do seu projeto.
    }
}
