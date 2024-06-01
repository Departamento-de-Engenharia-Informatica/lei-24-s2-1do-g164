package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;

import java.io.File;
import java.io.IOException;

/**
 * The type Gsm menu gui controller.
 */
public class GSMMenuGUIController {

    @FXML
    private Button greenSpacesButton;

    @FXML
    private Button toDoListButton;

    @FXML
    private Button agendaButton;

    @FXML
    private Button exitButton;

    private final AuthenticationController authenticationController = new AuthenticationController();

    @FXML
    private void openGreenSpaces(ActionEvent event) {
        // Logic to open the Green Spaces view
        try {
            File file = new File("src/main/resources/fxml/greenspacemenu.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 700, 700));
            stage.setTitle("Green Spaces");
            // Set the new scene or update the current scene with the new root
            stage.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Green Spaces button clicked");
    }

    @FXML
    private void openToDoList(ActionEvent event) {
        // Logic to open the To Do List view
        try {
            File file = new File("src/main/resources/fxml/todolistmenu.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 700, 700));
            stage.setTitle("To-Do List");
            // Set the new scene or update the current scene with the new root
            stage.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("To Do List button clicked");
    }

    @FXML
    private void openAgenda(ActionEvent event) {
        // Logic to open the Agenda view
        try {
            File file = new File("src/main/resources/fxml/agendaEntryMenu.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1300, 700));
            stage.setTitle("Agenda");
            // Set the new scene or update the current scene with the new root
            stage.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Agenda button clicked");
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        // Logic to close the window
        System.out.println("Exit button clicked");
        try {
            File file = new File("src/main/resources/fxml/loginmenu.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 350, 400));
            stage.getScene().setRoot(root);
            System.out.println(authenticationController.getCurrentUserEmail());
            authenticationController.doLogout();
            System.out.println(authenticationController.getCurrentUserEmail());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
