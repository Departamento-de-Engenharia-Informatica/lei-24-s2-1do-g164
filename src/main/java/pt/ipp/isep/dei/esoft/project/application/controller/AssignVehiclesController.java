package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDTO;
import pt.ipp.isep.dei.esoft.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.esoft.project.mappers.VehicleMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaEntryRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class AssignVehiclesController {
    private  VehicleRepository vehicleRepository;
    private AgendaEntryRepository agendaEntryRepository;

    private AgendaEntryMapper mapperAgenda;
    private VehicleMapper mapperVehicle;
    private final AuthenticationController authenticationController = new AuthenticationController();


    /**
     * Constructor for AssignVehiclesController.
     */
    public AssignVehiclesController() {
        this.mapperVehicle = new VehicleMapper();
        this.mapperAgenda = new AgendaEntryMapper();
        Repositories repositories = Repositories.getInstance();
        this.agendaEntryRepository = repositories.getAgendaEntryRepository();
        this.vehicleRepository = repositories.getVehicleRepository();
    }

    /**
     * Gets the list of AgendaEntries.
     *
     * @return The list of AgendaEntries.
     */
    public List<AgendaEntryDTO> getAgendaEntryListDTO() {
        var entries = agendaEntryRepository.getAgendaEntryWithoutDoneList(authenticationController.getCurrentUserEmail());
        return mapperAgenda.toDtoList(entries);
    }

    /**
     * Gets the list of vehicles.
     *
     * @return The list of vehicles.
     */
    public ArrayList<VehicleDTO> getVehiclesListDTO() {
        var vehicles = vehicleRepository.getVehicleList();
        return mapperVehicle.toDtoList(vehicles) ;
    }

    /**
     * Assigns vehicles to a Agenda Entry.
     *
     * @param agendaEntry  The agendaEntry to whom vehicles will be assigned.
     * @param vehiclesList The list of vehicles to be assigned.
     * @return True if the vehicles were assigned successfully, false otherwise.
     */

    public boolean assignVehciles(AgendaEntryDTO agendaEntry, List<VehicleDTO> vehiclesList) {
        return agendaEntryRepository.assignVehicles(mapperAgenda.toEntity(agendaEntry), mapperVehicle.toEntityList(vehiclesList));
    }
}
