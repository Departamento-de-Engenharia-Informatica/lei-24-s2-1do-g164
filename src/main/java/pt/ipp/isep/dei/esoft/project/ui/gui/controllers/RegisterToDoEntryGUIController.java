package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterToDoEntryController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoEntryDTO;
import pt.ipp.isep.dei.esoft.project.repository.enums.GreenSpaceType;
import pt.ipp.isep.dei.esoft.project.repository.enums.UrgencyDegree;

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
    ComboBox<GreenSpaceDTO> cmbGreenSpaces;
    @FXML
    ComboBox<UrgencyDegree> cmbUrgencyDegree;
    private ToDoListGUIController toDoListGUIController;
    private RegisterToDoEntryController controller = new RegisterToDoEntryController();

    @FXML
    private void initialize(){
        cmbUrgencyDegree.getItems().setAll(UrgencyDegree.values());
        cmbGreenSpaces.getItems().setAll(controller.getGreenSpaceDTOsList());
    }

    public void registerToDoEntry(ActionEvent event) {
        try {
            int expectedDuration = Integer.parseInt(txtExpectedDuration.getText());
            ToDoEntryDTO dto = new ToDoEntryDTO(txtDescription.getText(), expectedDuration, new GreenSpace(GreenSpaceType.GARDEN, "aa", "mer23", 12), cmbUrgencyDegree.getValue());
            if(controller.registerToDoEntry(dto)) {
                toDoListGUIController.update();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Register Error");
                alert.setHeaderText("There already exists a task with this name!");
                alert.show();
            }
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Register Error");
            alert.setHeaderText("Invalid inputs.");
            alert.show();
        }
    }

    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setToDoListGUIController(ToDoListGUIController toDoListGUIController) {
        this.toDoListGUIController = toDoListGUIController;
    }
}

