package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.PostponeAgendaEntryController;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;

/**
 * The type Postpone agenda entry gui controller.
 */
public class PostponeAgendaEntryGUIController {

    /**
     * The Btn cancel entry.
     */
    @FXML
    Button btnCancelEntry;
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

    /**
     * The Date.
     */
    @FXML
    DatePicker date;
    /**
     * The Controller.
     */
    PostponeAgendaEntryController controller = new PostponeAgendaEntryController();

    /**
     * The Agenda gui controller.
     */
    AgendaMenuGUIController agendaGUIController;

    @FXML
    private void initialize() {
        cmbAgendaEntries.getItems().setAll(controller.getAgendaEntryDTOsList());
    }

    /**
     * Postpone agenda entry.
     *
     * @param event the event
     */
    public void postponeAgendaEntry(ActionEvent event) {
        try {
            AgendaEntryDTO selectedEntry = cmbAgendaEntries.getValue();

            if (selectedEntry == null) {
                showAlert(Alert.AlertType.ERROR, "Postpone Entry Error", "No entry selected.");
                return;
            }

            if (selectedEntry.entryStatus == EntryStatusENUM.CANCELLED) {
                showAlert(Alert.AlertType.ERROR, "Postpone Entry Error", "Selected entry is already cancelled.");
                return;
            }

            if (controller.postponeAgendaEntry(selectedEntry, date.getValue())) {
                cmbAgendaEntries.getItems().setAll(controller.getAgendaEntryDTOsList());
                agendaGUIController.update();
                closeWindow(event);
                showAlert(Alert.AlertType.INFORMATION, "Postpone Entry", "Agenda Entry postponed successfully.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Postpone Entry Error", "Failed to postpone entry.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Postpone Entry Error", "An error occurred while postponing entry.");
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
     * Sets postpone agenda gui controller.
     *
     * @param agendaGUIController the agenda gui controller
     */
    public void setPostponeAgendaGUIController(AgendaMenuGUIController agendaGUIController) {
        this.agendaGUIController = agendaGUIController;
    }


    private void showAlert(Alert.AlertType alertType, String title, String message) {

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
