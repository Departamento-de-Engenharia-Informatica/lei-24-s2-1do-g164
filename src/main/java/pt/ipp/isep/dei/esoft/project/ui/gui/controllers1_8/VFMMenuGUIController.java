package pt.ipp.isep.dei.esoft.project.ui.gui.controllers1_8;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.model.Vehicle;
import pt.ipp.isep.dei.esoft.project.model.VehicleManagement;

public class VFMMenuGUIController {

    @FXML
    private Button btnShowNeedingCheckup;

    @FXML
    private ListView<String> list;

    private boolean showingNeedingCheckup = false;

    public VFMMenuGUIController() {
        this.vehicleManagement = new VehicleManagement();
    }

    public void show(ActionEvent actionEvent) {
        // Implement this method to handle the action of the "show" button
    }

    public void closeWindow(ActionEvent actionEvent) {
        // Implement this method to handle the action of the "close window" button
    }

    @FXML
    private void toggleShowNeedingCheckup(ActionEvent event) {
        if (showingNeedingCheckup) {
            // If currently showing vehicles needing checkup, show all vehicles
            showAllVehicles();
            btnShowNeedingCheckup.setText("Show Needing Checkup");
        } else {
            // If currently showing all vehicles, show only vehicles needing checkup
            showNeedingCheckup();
            btnShowNeedingCheckup.setText("Show All");
        }
        showingNeedingCheckup = !showingNeedingCheckup;
    }

    private void showAllVehicles() {
        // Show all vehicles
        list.getItems().clear();
        for (Vehicle vehicle : vehicleManagement.getAllVehicles()) {
            list.getItems().add(vehicle.toString());
        }
    }

    private void showNeedingCheckup() {
        // Show only vehicles needing checkup
        list.getItems().clear();
        for (Vehicle vehicle : vehicleManagement.getVehiclesNeedingCheckup()) {
            list.getItems().add(vehicle.toString());
        }
    }
}