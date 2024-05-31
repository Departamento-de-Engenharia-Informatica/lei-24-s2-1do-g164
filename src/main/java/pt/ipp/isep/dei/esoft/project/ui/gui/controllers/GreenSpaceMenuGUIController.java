package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterToDoEntryController;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoEntryDTO;
import pt.ipp.isep.dei.esoft.project.repository.enums.UrgencyDegreeENUM;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GreenSpaceMenuGUIController {

    @FXML
    Button btnAddEntry;
    @FXML
    Button btnCancel;
    @FXML
    TextArea txtGreenSpaceListText;
    @FXML
    BorderPane borderPane;
    //alterar a ComboBox para dar display das opções de ordenação usadas no algoritmo
    @FXML
    ComboBox<UrgencyDegreeENUM> cmbSortSize;

    private RegisterGreenSpaceController controller = new RegisterGreenSpaceController();

    @FXML
    private void initialize(){
        //""
        cmbSortSize.getItems().setAll(UrgencyDegreeENUM.values());
        update();
    }

    public void update(){
        txtGreenSpaceListText.clear();
        ArrayList<GreenSpaceDTO> greenSpaceDTOsList = controller.getGreenSpaceDTOsList();
        for(GreenSpaceDTO greenSpaceDTO : greenSpaceDTOsList){
            txtGreenSpaceListText.appendText(greenSpaceDTO.toStringLong() + "\n");
        }
    }

    public void openRegisterGreenSpaceWindow(ActionEvent event) {
        try {
            File file = new File("src\\main\\resources\\fxml\\registergreenspace.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURL());
            Parent root = loader.load();
            RegisterGreenSpaceGUIController controller1 = loader.getController();
            controller1.setGreenSpaceGUIController(this);
            Stage newStage = new Stage();
            newStage.setTitle("Register Green Space");
            newStage.setScene(new Scene(root));
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
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
}
