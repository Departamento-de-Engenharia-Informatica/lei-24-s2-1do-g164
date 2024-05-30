package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;
import com.kitfox.svg.A;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CancelAgendaEntryController;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;

public class CancelAgendaEntryGUIController {

    @FXML
         Button btnCancelEntry;
    @FXML
         Button btnCancel;

    @FXML
    ComboBox<AgendaEntryDTO> cmbAgendaEntries;
     CancelAgendaEntryController controller = new CancelAgendaEntryController();

     AgendaMenuGUIController agendaGUIController;

    @FXML
    private void initialize() {
        cmbAgendaEntries.getItems().setAll(controller.getAgendaEntryDTOListByStatus(EntryStatusENUM.CANCELLED));
    }

    public void cancelAgendaEntry(ActionEvent event) {

        AgendaEntryDTO selectedEntry = new AgendaEntryDTO(cmbAgendaEntries.getValue().description, cmbAgendaEntries.getValue().greenSpace, cmbAgendaEntries.getValue().entryStatus);

        try {

            if (selectedEntry == null) {
                showAlert(Alert.AlertType.ERROR, "Cancel Entry Error", "No entry selected.");
                return;
            }

            if (selectedEntry.entryStatus == EntryStatusENUM.CANCELLED) {
                showAlert(Alert.AlertType.ERROR, "Cancel Entry Error", "Selected entry is already cancelled.");
                return;
            }

            if (controller.cancelAgendaEntry(selectedEntry)) {
                agendaGUIController.update();
                showAlert(Alert.AlertType.INFORMATION, "Cancel Entry", "Agenda Entry cancelled successfully.");
                cmbAgendaEntries.getItems().remove(selectedEntry);
            } else {
                showAlert(Alert.AlertType.ERROR, "Cancel Entry Error", "Failed to cancel entry.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Cancel Entry Error", "An error occurred while canceling entry.");
        }
    }

    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

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
