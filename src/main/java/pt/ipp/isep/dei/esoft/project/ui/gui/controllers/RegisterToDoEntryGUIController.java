package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

public class RegisterToDoEntryGUIController {
    @FXML
    Button btnAddEntry;
    @FXML
    Button btnCancel;
    @FXML
    TextField txtDescription;
    @FXML
    TextField txtExpectedDuration;
    @FXML
    ComboBox<GreenSpace> cmbGreenSpaces;
    private ToDoListGUIController toDoListGUIController;

    public void registerToDoEntry(ActionEvent event) {
        System.out.println("yee");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setToDoListGUIController(ToDoListGUIController toDoListGUIController) {
        this.toDoListGUIController = toDoListGUIController;
    }
}

