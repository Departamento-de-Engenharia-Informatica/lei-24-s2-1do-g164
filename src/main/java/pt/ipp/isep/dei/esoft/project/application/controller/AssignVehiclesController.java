package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.ToDoEntry;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;

/**
 * Controller class responsible for managing the assignment of vehicles to entries in the agenda.
 */
public class AssignVehiclesController {
    private VehicleRepository vehicleRepository;
    private ToDoEntryRepository toDoEntryRepository;
    AuthenticationController authenticationController = new AuthenticationController();

    /**
     * Constructor for AssignVehiclesController.
     */
    public AssignVehiclesController() {
        getToDoEntryRepository();
        getVehicleRepository();
    }

    /**
     * Retrieves the toDoEntry repository.
     *
     * @return The toDoEntry repository.
     */
    private ToDoEntryRepository getToDoEntryRepository() {
        if (toDoEntryRepository == null) {
            Repositories repositories = Repositories.getInstance();
            toDoEntryRepository = repositories.getToDoEntryRepository();
        }
        return toDoEntryRepository;
    }

    /**
     * Retrieves the vehicle repository.
     *
     * @return The vehicle repository.
     */
    private VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();
            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }

    /**
     * Gets the list of entries.
     *
     * @return The list of entries.
     */
    public ArrayList<ToDoEntry> getToDoEntryList() {
        return toDoEntryRepository.getToDoEntryList(authenticationController.getCurrentUserEmail());
    }

    /**
     * Gets the list of vehicles.
     *
     * @return The list of vehicles.
     */
    public ArrayList<Vehicle> getVehicleList() {
        return vehicleRepository.getVehicleList();
    }

    /**
     * Assigns vehicles to an entry.
     *
     * @param toDoEntry    The entry to which vehicles will be assigned.
     * @param vehiclesList The list of vehicles to be assigned.
     * @return True if the vehicles were assigned successfully, false otherwise.
     */
    public boolean assignVehicles(ToDoEntry toDoEntry, ArrayList<Vehicle> vehiclesList) {
        return toDoEntryRepository.assignVehicles(toDoEntry, vehiclesList);
    }
}
