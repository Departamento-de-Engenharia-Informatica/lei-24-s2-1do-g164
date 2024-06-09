package pt.ipp.isep.dei.esoft.project.ui.gui.controllers20_29;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignVehiclesController;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDTO;

import java.util.List;

/**
 * The type Vehicles to agenda menu gui controller.
 */
public class AssignVehiclesGUIController {

    @FXML
    ListView<VehicleDTO> vehicleListSelection;

    @FXML
    ComboBox<AgendaEntryDTO> cmbAgendaEntries;

    @FXML
    Button btnCancel;

    @FXML
    Button btnConfirm;

    private AgendaMenuGUIController agendaMenuGUIController;
    private AssignVehiclesController controller = new AssignVehiclesController();

    @FXML
    public void initialize() {
        refreshComboBox();
        vehicleListSelection.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void refreshComboBox() {
        cmbAgendaEntries.getItems().setAll(controller.getAgendaEntryListDTO());
        vehicleListSelection.getItems().setAll(controller.getVehiclesListDTO());
    }

    private void selectionChanged(ObservableValue<? extends VehicleDTO> observable, VehicleDTO oldValue, VehicleDTO newValue) {
        ObservableList<VehicleDTO> selectedItems = vehicleListSelection.getSelectionModel().getSelectedItems();
        String getSelectedItems = selectedItems.toString();
    }

    @FXML
    public void handleAssignVehicles(ActionEvent event) {
        try {
            AgendaEntryDTO selectedEntry = cmbAgendaEntries.getValue();
            List<VehicleDTO> vehicleList = vehicleListSelection.getSelectionModel().getSelectedItems().stream().toList();

            if (selectedEntry == null) {
                showAlert(Alert.AlertType.ERROR, "Assign Vehicle Error", "No entry selected.");
                return;
            }

            if (vehicleList.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Assign Vehicle Error", "At least one vehicle must be selected.");
                return;
            }

            if (controller.assignVehicles(selectedEntry, vehicleList)) {
                showAlert(Alert.AlertType.INFORMATION, "Assign Vehicle", "Vehicle assigned successfully to agenda entry.");
                agendaMenuGUIController.update();
                handleClose();
            } else {
                showAlert(Alert.AlertType.ERROR, "Assign Vehicle Error", "There are vehicles not available in this period of time.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "There are vehicles not available in this period of time.");
        }
    }

    public void setAgendaMenuGUIController(AgendaMenuGUIController agendaMenuGUIController) {
        this.agendaMenuGUIController = agendaMenuGUIController;
    }

    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setAgendaGUIController(AgendaMenuGUIController agendaMenuGUIController) {
        this.agendaMenuGUIController = agendaMenuGUIController;
    }

    private void handleClose() {
        Stage stage = (Stage) cmbAgendaEntries.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
