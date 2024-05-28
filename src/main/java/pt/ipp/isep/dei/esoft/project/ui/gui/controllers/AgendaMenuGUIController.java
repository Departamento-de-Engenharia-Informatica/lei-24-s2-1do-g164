package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CancelAgendaEntryController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterAgendaEntryController;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AgendaMenuGUIController {

    @FXML
    Button btnCancelEntry;
    @FXML
    Button btnCancel;

    @FXML
    Button btnAddTeam;

    @FXML
    Button btnAddVehicles;
    @FXML
    Button btnPostpone;

    @FXML
    Button btnAddAgendaEntry;

    @FXML
    BorderPane borderPane;

    @FXML
    TextArea txtText;

    private RegisterAgendaEntryController controller = new RegisterAgendaEntryController();

    @FXML
    private void initialize(){
        update();
    }

    public void update(){
        ArrayList<AgendaEntryDTO> agendaEntryDTOS = controller.getAgendaEntryListDTO();
        txtText.clear();
        for(AgendaEntryDTO agendaEntryDTO : agendaEntryDTOS){
            txtText.appendText(agendaEntryDTO.toString() + "\n");
        }
    }

    public void openAssignTeamAgendaWindow(ActionEvent event) {
        try {
            File file = new File("src\\main\\resources\\fxml\\assignTeamToAgendaEntry.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 700, 700));
            stage.setTitle("Assign Team to Agenda Entry");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    public void openCancelAgendaEntryWindow(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/cancelAgendaEntry.fxml"));
            Parent root = loader.load();
            CancelAgendaEntryGUIController cancelGUIController = loader.getController();
            cancelGUIController.setAgendaGUIController(this);
            Stage newStage = new Stage();
            newStage.setTitle("Cancel Agenda Entry");
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Unable to open Cancel Agenda Entry window.");
        }
        }
    public void closeWindow(ActionEvent event){
        try {
            File file = new File("src/main/resources/fxml/gsmMenu.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 400, 400));
            // Set the new scene or update the current scene with the new root
            stage.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
