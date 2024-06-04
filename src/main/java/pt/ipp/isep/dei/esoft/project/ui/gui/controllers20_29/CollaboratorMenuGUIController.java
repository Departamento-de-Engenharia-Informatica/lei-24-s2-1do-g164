package pt.ipp.isep.dei.esoft.project.ui.gui.controllers20_29;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * The type Collaborator menu gui controller.
 */
public class CollaboratorMenuGUIController {


    @FXML
    private void openMyAgenda(ActionEvent event) {
        try {
            File file = new File("src/main/resources/fxml/myAgenda.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            //Set the new scene or update the current scene with the new root
            stage.setScene(new Scene(root, 600, 600));
            stage.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("My Agenda button clicked");
    }

    public void openCompleteAgendaEntryWindow(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/markTaskCompleted.fxml"));
            Parent root = loader.load();
            MarkTaskCompletedGUIController completedGUIController = loader.getController();
            completedGUIController.setAgendaGUIController(this);
            Stage newStage = new Stage();
            newStage.setTitle("Completed Agenda Entry");
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Unable to open Complet Agenda Entry window.");
        }
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        try {
            File file = new File("src/main/resources/fxml/loginmenu.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 350, 400));
            // Set the new scene or update the current scene with the new root
            stage.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
