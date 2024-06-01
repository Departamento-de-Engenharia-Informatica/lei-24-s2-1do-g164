package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;

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



public class TeamtoAgendaMenuGUIController {

    @FXML
    ComboBox<AgendaEntryDTO> cmbAgendaEntries;

    @FXML
    ComboBox<TeamDTO> cmbTeams;

    @FXML
     Button btnCancel;
    @FXML
    TextField txtArea;

    private AgendaMenuGUIController agendaMenuGUIController;
    private TeamToAgendaEntryController controller = new TeamToAgendaEntryController();


    @FXML
    public void initialize() {
        cmbAgendaEntries.getItems().setAll(controller.getAgendaEntriesDTOWithoutTeam());
        cmbTeams.getItems().setAll(controller.showAvailableTeamsDTO());
    }

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
                System.out.println("Team assigned successfully!");
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

