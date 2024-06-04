package pt.ipp.isep.dei.esoft.project.ui.gui.controllers20_29;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.TeamToAgendaEntryController;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.TeamDTO;
import javafx.event.ActionEvent;


/**
 * The type Teamto agenda menu gui controller.
 */
public class TeamtoAgendaMenuGUIController {

    /**
     * The Cmb agenda entries.
     */
    @FXML
    ComboBox<AgendaEntryDTO> cmbAgendaEntries;

    /**
     * The Cmb teams.
     */
    @FXML
    ComboBox<TeamDTO> cmbTeams;

    /**
     * The Btn cancel.
     */
    @FXML
     Button btnCancel;
    /**
     * The Txt area.
     */
    @FXML
    TextField txtArea;

    private AgendaMenuGUIController agendaMenuGUIController;
    private TeamToAgendaEntryController controller = new TeamToAgendaEntryController();


    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        refreshComboBox();
    }

    public void  refreshComboBox() {
        cmbAgendaEntries.getItems().setAll(controller.getAgendaEntriesDTOWithoutTeam());
        cmbTeams.getItems().setAll(controller.showAvailableTeamsDTO());
    }
    /**
     * Handle assign team.
     *
     * @param event the event
     */
    @FXML
    public void handleAssignTeam(ActionEvent event) {
        try {
            AgendaEntryDTO selectedEntry = cmbAgendaEntries.getValue();
            TeamDTO teamEntry = cmbTeams.getValue();

            if (selectedEntry == null) {
                showAlert(Alert.AlertType.ERROR, "Cancel Entry Error", "No entry selected.");
                return;
            }

            if (teamEntry == null) {
                showAlert(Alert.AlertType.ERROR, "Cancel Entry Error",  "No team selected");
            }

            if (controller.assignTeamToAgendaEntry(selectedEntry, teamEntry)) {
                showAlert(Alert.AlertType.INFORMATION, "Assign Team", "Team assigned successfully to agenda entry.");
                agendaMenuGUIController.update();
                handleClose();
            } else {
                System.out.println("Failed to assign team.");
            }
        } catch (Exception e) {
        e.printStackTrace();
        showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while assigning team.");
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

