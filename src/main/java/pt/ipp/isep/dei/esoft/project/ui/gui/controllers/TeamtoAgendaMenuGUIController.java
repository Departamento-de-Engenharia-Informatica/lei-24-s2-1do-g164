package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.TeamToAgendaEntryController;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.TeamDTO;
import javafx.event.ActionEvent;


public class TeamtoAgendaMenuGUIController {

    @FXML
    private ComboBox<AgendaEntryDTO> cmbAgendaEntries;

    @FXML
    private ComboBox<TeamDTO> cmbTeams;

    @FXML
    private Button btnCancel;

    private AgendaMenuGUIController agendaMenuGUIController;
    private TeamToAgendaEntryController controller;


    @FXML
    public void initialize() {

        cmbAgendaEntries.getItems().setAll(controller.getAgendaEntryDTOList());
        cmbTeams.getItems().setAll(controller.showAvailableTeamsDTO());
    }

    @FXML
    public void handleAssignTeam(ActionEvent actionEvent) {
        AgendaEntryDTO agendaEntryDTO = new AgendaEntryDTO(cmbAgendaEntries.getValue().description, cmbAgendaEntries.getValue().greenSpace, cmbAgendaEntries.getValue().team);

        if (agendaEntryDTO != null) {
            if (controller.assignTeamToAgendaEntry(agendaEntryDTO)) {
                System.out.println("Team assigned successfully!");
                handleClose();
            } else {
                System.out.println("Failed to assign team.");
            }
        }
    }

    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setTeamtoAgendaMenuGUIController(AgendaMenuGUIController agendaMenuGUIController) {
        this.agendaMenuGUIController = agendaMenuGUIController;
    }


    @FXML
    public void handleCancel() {
        handleClose();
    }

    private void handleClose() {
        Stage stage = (Stage) cmbAgendaEntries.getScene().getWindow();
        stage.close();
    }


}

