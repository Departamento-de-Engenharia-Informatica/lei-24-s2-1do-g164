package pt.ipp.isep.dei.esoft.project.ui.gui.controllers1_8;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateTeamProposalController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class CreateTeamPropoalGUIController {

    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtMaxNumber;
    @FXML
    private TextField txtMinNumber;

    @FXML
    private ListView<Skill> skillListSelection;

    @FXML
    private ListView<String> collaboratorListView;

    private TeamGUIController teamGUIController;
    private CreateTeamProposalController controller = new CreateTeamProposalController();

    @FXML
    public void initialize() {
        skillListSelection.getItems().setAll(controller.getSkillsList());
        skillListSelection.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private void createProposal(ActionEvent event) {
        try {
            ArrayList<Skill> skillList = new ArrayList<>(skillListSelection.getSelectionModel().getSelectedItems());
            if (skillList.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Input Error", "At least one skill must be selected.");
                return;
            }

            if (!isValidNumber(txtMaxNumber.getText())) {
                showAlert(Alert.AlertType.ERROR, "Input Error", "Invalid maximum number.");
                return;
            }
            int max = Integer.parseInt(txtMaxNumber.getText());

            if (!isValidNumber(txtMinNumber.getText())) {
                showAlert(Alert.AlertType.ERROR, "Input Error", "Invalid minimum number.");
                return;
            }
            int min = Integer.parseInt(txtMinNumber.getText());

            if (min > max) {
                showAlert(Alert.AlertType.ERROR, "Error", "Minimum cannot be higher than maximum!");
                return;
            }

            var team = controller.createTeamProposal(max, min, skillList);
            openShowTeamWindow(team);
            teamGUIController.update();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "No collaborators found for desired skills.");
        }
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    private void openShowTeamWindow(Team team) {
        try {
            URL fxmlLocation = getClass().getResource("/fxml_1-8/acceptTeam.fxml");
            if (fxmlLocation == null) {
                throw new IOException("FXML file not found");
            }
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();
            AcceptTeamGUIController controller = loader.getController();
            controller.setTeam(team);
            controller.setCreateTeamPropoalGUIController(this);
            Stage newStage = new Stage();
            newStage.setTitle("Team Proposal Details");
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while opening team proposal window: " + e.getMessage());
        }
    }

    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setTeamGUIController(TeamGUIController teamGUIController) {
        this.teamGUIController = teamGUIController;
    }

    public TeamGUIController getTeamGUIController() {
        return teamGUIController;
    }

    public void update() {
        teamGUIController.update();
    }

    private boolean isValidNumber(String text) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }
        for (char c : text.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
