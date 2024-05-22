package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterToDoEntryController;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoEntryDTO;

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

    private RegisterGreenSpaceController controller = new RegisterGreenSpaceController();

    @FXML
    private void initialize(){
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
            newStage.setTitle("Add Entry to To-Do List");
            newStage.setScene(new Scene(root));
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeWindow(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
