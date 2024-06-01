package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.MarkTaskCompletedController;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;

/**
 * The type Mark task completed gui controller.
 */
public class MarkTaskCompletedGUIController {

    /**
     * The Btn mark task as completed.
     */
    @FXML
    Button btnMarkTaskAsCompleted;
    /**
     * The Btn cancel.
     */
    @FXML
    Button btnCancel;
    /**
     * The Cmb agenda entries.
     */
    @FXML
    ComboBox<AgendaEntryDTO> cmbAgendaEntries;

    MarkTaskCompletedController controller = new MarkTaskCompletedController();
    AgendaMenuGUIController agendaGUIController;

    @FXML
    private void initialize() {
        cmbAgendaEntries.getItems().setAll(controller.getAgendaEntryWithoutDoneDTOList());
    }

    /**
     * Completed agenda entry.
     *
     * @param event the event
     */
    public void completedAgendaEntry(ActionEvent event) {
        AgendaEntryDTO selectedEntry = new AgendaEntryDTO(cmbAgendaEntries.getValue().description, cmbAgendaEntries.getValue().greenSpace, cmbAgendaEntries.getValue().entryStatus);

        try {
            if (selectedEntry == null) {
                showAlert(Alert.AlertType.ERROR, "Entry Error", "No entry selected.");
                return;
            }

            if (selectedEntry.getEntryStatus() == EntryStatusENUM.DONE) {
                showAlert(Alert.AlertType.ERROR, "Entry Error", "Selected entry is already completed.");
                return;
            }

            if (controller.completedAgendaEntry(selectedEntry)) {
                showAlert(Alert.AlertType.INFORMATION, "Completed Entry", "Agenda Entry was successfully completed.");
                initialize();
                agendaGUIController.update();
            } else {
                showAlert(Alert.AlertType.ERROR, "Complete Entry Error", "Failed to complete entry.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Complete Entry Error", "An error occurred while completing entry.");
        }
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
     * @param agendaGUIController the agenda gui controller
     */
    public void setAgendaGUIController(AgendaMenuGUIController agendaGUIController) {
        this.agendaGUIController = agendaGUIController;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
