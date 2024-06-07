package pt.ipp.isep.dei.esoft.project.ui.gui.controllers1_8;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateTeamProposalController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Team;

public class AcceptTeamGUIController {

    @FXML
    private ListView<String> listView;
    private Team team;
    private Collaborator collaborator;
    private CreateTeamProposalController controller;
    private CreateTeamPropoalGUIController createTeamPropoalGUIController;

    public AcceptTeamGUIController() {
        this.controller = new CreateTeamProposalController();
    }

    public void setTeam(Team team) {
        this.team = team;
        populateListView();
    }

    private void populateListView() {
        listView.getItems().clear();
        for (Collaborator collaborator : team.getCollaborators()) {
            String collaboratorDetails = collaborator.getName() + ", Skills: " + collaborator.getSkills();
            listView.getItems().add(collaboratorDetails);
        }
    }
    @FXML
    private void acceptTeamProposal() {

        if (controller.acceptTeamProposal(team)) {
            showAlert(Alert.AlertType.INFORMATION, "Accept Team", "Team successfully accepted.");
            if (createTeamPropoalGUIController != null) {
                createTeamPropoalGUIController.getTeamGUIController().addTeam(team);
            }
            createTeamPropoalGUIController.update();
            closeWindow();
        } else {
                showAlert(Alert.AlertType.ERROR, "Accept Team", "Could not accept team");

        }
    }

    @FXML
    private void refuseTeamProposal() {
        if (controller.refuseTeamProposal(team)) {
            showAlert(Alert.AlertType.INFORMATION, "Refuse Team", "Team refused!.");

            closeWindow();
        } else {
            showAlert(Alert.AlertType.ERROR, "Refuse Team", "Could not refuse team");
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) listView.getScene().getWindow();
        stage.close();
    }

    public void setCreateTeamPropoalGUIController(CreateTeamPropoalGUIController createTeamPropoalGUIController) {
        this.createTeamPropoalGUIController = createTeamPropoalGUIController;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
