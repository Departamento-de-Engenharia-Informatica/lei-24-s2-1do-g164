package pt.ipp.isep.dei.esoft.project.ui.gui.controllers1_8;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HRMMenuGUIController {

    @FXML
    private Button btnSkills;

    @FXML
    private Button btnJobs;

    @FXML
    private Button btnCollaborators;

    @FXML
    private Button btnTeams;

    @FXML
    private Button exitButton;

    @FXML
    public void initialize() {
        // Initialization logic here
    }

    @FXML
    private void openSkillsMenuWindow(ActionEvent event) {
        try {
            File file = new File("src/main/resources/fxml_1-8/skillsMenu.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 700, 700));
            stage.setTitle("Skills Menu");
            // Set the new scene or update the current scene with the new root
            stage.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openJobsMenuWindow(ActionEvent event) {
        try {
            File file = new File("src/main/resources/fxml_1-8/jobsMenu.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 700, 700));
            stage.setTitle("Jobs Menu");
            // Set the new scene or update the current scene with the new root
            stage.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openCollaboratorsMenuWindow() {
        // Logic to open the CollaboratorsMenuWindow
        // This will depend on your application's logic
    }

    @FXML
    private void openTeamsMenuWindow() {
        // Logic to open the TeamsMenuWindow
        // This will depend on your application's logic
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}