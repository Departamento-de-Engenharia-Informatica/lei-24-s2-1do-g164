package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignVehiclesController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.ToDoEntry;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.HrmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the user interface for assigning vehicles to an entry in the agenda.
 */
public class AssignVehiclesUI implements Runnable{

    private final AssignVehiclesController controller;

    public AssignVehiclesUI() {
        this.controller = new AssignVehiclesController();
    }

    private ToDoEntry displayAndSelectToDoEntry() {
        ArrayList<ToDoEntry> toDoEntriesList = controller.getToDoEntryList();
        int listSize = toDoEntriesList.size();
        int answer;
        Scanner sc = new Scanner(System.in);
        do {
            displayToDoEntriesList(toDoEntriesList);
            System.out.println("\nSelect an entry number (0 to cancel): ");
            while (!sc.hasNextInt()) {
                System.out.println("\nInvalid input. Please enter a valid entry number.\n");
                sc.next();
            }
            answer = sc.nextInt();
            if (answer == 0) redirectToHrmUI();
        } while (answer < 1 || answer > listSize);
        return toDoEntriesList.get(answer - 1);
    }

    private void displayToDoEntriesList(ArrayList<ToDoEntry> toDoEntriesList) {
        System.out.println();
        for (int i = 0; i < toDoEntriesList.size(); i++) {
            ToDoEntry entry = toDoEntriesList.get(i);
            System.out.println("  " + (i+1) + " - " + entry.getEntryStatus());
        }
        System.out.println("  0 - Cancel");
    }

    private ArrayList<Vehicle> displayAndSelectVehicles() {
        ArrayList<Vehicle> vehiclesList = controller.getVehicleList(); // Alteração aqui
        ArrayList<Vehicle> chosenVehicles = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int answer;
        do {
            displayVehiclesList(vehiclesList);
            System.out.println("\nSelect a vehicle number to assign it to the entry (0 to stop): ");
            while (!sc.hasNextInt()) {
                System.out.println("\nInvalid input. Please enter a valid vehicle number.\n");
                sc.next();
            }
            answer = sc.nextInt();
            if (answer != 0 && (answer < 1 || answer > vehiclesList.size())) {
                System.out.println("\nInvalid input. Please enter a valid vehicle number.\n");
            }
            if (answer != 0) {
                Vehicle selectedVehicle = vehiclesList.get(answer - 1);
                if (!chosenVehicles.contains(selectedVehicle)) {
                    chosenVehicles.add(selectedVehicle);
                } else {
                    System.out.println("\nVehicle already assigned. Please select a different vehicle.\n");
                }
            }
        } while (answer != 0);
        return chosenVehicles;
    }

    private void displayVehiclesList(ArrayList<Vehicle> vehiclesList) {
        System.out.println();
        for (int i = 0; i < vehiclesList.size(); i++) {
            Vehicle vehicle = vehiclesList.get(i);
            System.out.println("  " + (i+1) + " - " + vehicle.getVehicleID());
        }
        System.out.println("  0 - Cancel");
    }

    private void redirectToHrmUI() {
        MenuItem item = new MenuItem(AuthenticationController.ROLE_HRM, new HrmUI());
        item.run();
    }

    @Override
    public void run() {
        ToDoEntry toDoEntry = displayAndSelectToDoEntry();
        System.out.println("\nSelected entry:");
        System.out.println("- Status: " + toDoEntry.getEntryStatus());
        ArrayList<Vehicle> chosenVehicles = displayAndSelectVehicles();
        if (!chosenVehicles.isEmpty()) {
            if (controller.assignVehicles(toDoEntry, chosenVehicles)){
                System.out.println("Vehicles attributed to the entry:");
                for (Vehicle vehicle : chosenVehicles) {
                    System.out.println("- " + vehicle.getVehicleID());
                }
            } else {
                System.out.println("The entry already has these vehicles");
            }
        } else {
            System.out.println("No vehicles assigned");
        }
    }
}
