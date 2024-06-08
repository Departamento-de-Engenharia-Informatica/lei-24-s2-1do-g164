package pt.ipp.isep.dei.esoft.project.ui.gui.controllers1_8;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateTeamProposalController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TeamGUIController {


    @FXML
    private TextArea txtText;

    private CreateTeamProposalController controller = new CreateTeamProposalController();

    @FXML
    public void initialize() {
        update();
    }


    public void update() {
        txtText.clear();
        var teams = controller.getTeams();
        StringBuilder sb = new StringBuilder();
        sb.append("-------------------Teams--------------------\n");
        for (Team team : teams) {
            sb.append(formatTeamDetails(team));
        }
        txtText.setText(sb.toString());
    }

    public void addTeam(Team team) {
        String teamDetails = team.toString();
        txtText.appendText(teamDetails + "\n");
    }

    private String formatTeamDetails(Team team) {
        StringBuilder sb = new StringBuilder();
        for (Collaborator collaborator : team.getCollaborators()) {
            sb.append("  Name: ").append(collaborator.getName()).append("\n");
            sb.append("  Skills: ").append(collaborator.getSkills()).append("\n");
        }
        sb.append("--------------------------------------------\n\n");
        return sb.toString();
    }




    @FXML
    private void openCreateTeamWindow() {
        try {
            File file = new File("src\\main\\resources\\fxml_1-8\\createProposal.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURL());
            Parent root = loader.load();
            CreateTeamPropoalGUIController controller = loader.getController();
            controller.setTeamGUIController(this);
            Stage newStage = new Stage();
            newStage.setTitle("Create Team Proposal");
            newStage.setScene(new Scene(root));
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void closeWindow(ActionEvent event){
        try {
            File file = new File("src/main/resources/fxml/hrmMenu.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 400, 400));

            stage.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

