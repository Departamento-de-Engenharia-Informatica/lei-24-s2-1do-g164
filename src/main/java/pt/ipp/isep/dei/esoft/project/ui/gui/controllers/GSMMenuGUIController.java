package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;

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

public class GSMMenuGUIController {

    @FXML
    private Button greenSpacesButton;

    @FXML
    private Button toDoListButton;

    @FXML
    private Button agendaButton;

    @FXML
    private Button exitButton;

    @FXML
    private void openGreenSpaces(ActionEvent event) {
        // Logic to open the Green Spaces view
        try {
            File file = new File("src\\main\\resources\\fxml\\greenspacemenu.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURL());
            Parent root = loader.load();
            GreenSpaceMenuGUIController controller1 = loader.getController();
            Stage newStage = new Stage();
            newStage.setTitle("Add Entry to To-Do List");
            newStage.setScene(new Scene(root));
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Green Spaces button clicked");
    }

    @FXML
    private void openToDoList(ActionEvent event) {
        // Logic to open the To Do List view
        try {
            File file = new File("src\\main\\resources\\fxml\\todolistmenu.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURL());
            Parent root = loader.load();
            ToDoListGUIController controller1 = loader.getController();
            Stage newStage = new Stage();
            newStage.setTitle("Add Entry to To-Do List");
            newStage.setScene(new Scene(root));
            newStage.show();

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
            stage.setScene(new Scene(root, 600, 600));
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

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 350, 400));
            // Set the new scene or update the current scene with the new root
            stage.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
