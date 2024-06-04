package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignVehiclesController;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDTO;

import java.util.ArrayList;

/**
 * The type Vehicles to agenda menu gui controller.
 */

public class AssignVehiclesGUIController {

        /**
         * The Cmb agenda entries.
         */
        @FXML
        ListView<VehicleDTO> vehicleListSelection;

        /**
         * The Cmb vehicles.
         */
        @FXML
        ComboBox<AgendaEntryDTO> cmbAgendaEntries;

        /**
         * The Btn cancel.
         */
        @FXML
        Button btnCancel;
        /**
         * The Txt area.
         */
        @FXML
        Button btnConfirm;

        private AgendaMenuGUIController agendaMenuGUIController;
        private AssignVehiclesController controller = new AssignVehiclesController();

        /**
         * Initialize.
         */
        @FXML
        public void initialize() {
            refreshComboBox();
        }

        public void  refreshComboBox() {
            cmbAgendaEntries.getItems().setAll(controller.getAgendaEntryListDTO());
            vehicleListSelection.getItems().setAll(controller.getVehiclesListDTO());
        }
        /**
         * Handle assign vehicles.
         *
         * @param event the event
         */
        @FXML
        public void handleAssignVehicles(ActionEvent event) {
            try {
                AgendaEntryDTO selectedEntry = cmbAgendaEntries.getValue();
                vehicleListSelection.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                ArrayList<VehicleDTO> vehicleList = (ArrayList<VehicleDTO>) vehicleListSelection.getSelectionModel().getSelectedItems();

                if (selectedEntry == null) {
                    showAlert(Alert.AlertType.ERROR, "Cancel Entry Error", "No entry selected.");
                    return;
                }

                if (vehicleList == null) {
                    showAlert(Alert.AlertType.ERROR, "Cancel Entry Error",  "No team selected");
                }

                if (controller.assignVehciles(selectedEntry, vehicleList)) {
                    showAlert(Alert.AlertType.INFORMATION, "Assign Vehicle", "Vehicle assigned successfully to agenda entry.");
                    agendaMenuGUIController.update();
                    handleClose();
                } else {
                    System.out.println("Failed to assign vehicle.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while assigning vehicle.");
            }

        }

    public void setAgendaMenuGUIController(AgendaMenuGUIController agendaMenuGUIController) {
        this.agendaMenuGUIController = agendaMenuGUIController;
    }

    /**
         * Close window.
         *
         * @param event the event
         */
        public void closeWindow(ActionEvent event) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }

        /**
         * Sets agenda gui controller.
         *
         * @param agendaMenuGUIController the agenda menu gui controller
         */
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



