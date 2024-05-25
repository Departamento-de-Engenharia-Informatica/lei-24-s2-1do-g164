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
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoEntryDTO;
import pt.ipp.isep.dei.esoft.project.mappers.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.repository.enums.UrgencyDegreeENUM;

public class RegisterToDoEntryGUIController {
    @FXML
    private Button btnAddEntry;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtExpectedDuration;
    @FXML
    private ComboBox<GreenSpaceDTO> cmbGreenSpaces;
    @FXML
    private ComboBox<UrgencyDegreeENUM> cmbUrgencyDegree;

    private ToDoListGUIController toDoListGUIController;
    private RegisterToDoEntryController controller = new RegisterToDoEntryController();
    private GreenSpaceMapper greenSpaceMapper = new GreenSpaceMapper();

    @FXML
    private void initialize() {
        cmbUrgencyDegree.getItems().setAll(UrgencyDegreeENUM.values());
        cmbGreenSpaces.getItems().setAll(controller.getGreenSpaceDTOsList());
    }

    public void registerToDoEntry(ActionEvent event) {
        try {
            String description = txtDescription.getText();
            if (description == null || description.trim().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "Description cannot be empty.");
                return;
            }

            int expectedDuration = Integer.parseInt(txtExpectedDuration.getText());
            if (expectedDuration <= 0) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "The expected duration must be a positive number.");
                return;
            }

            GreenSpaceDTO greenSpace = cmbGreenSpaces.getValue();
            if (greenSpace == null) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "You must select a Green Space.");
                return;
            }

            UrgencyDegreeENUM urgencyDegree = cmbUrgencyDegree.getValue();
            if (urgencyDegree == null) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "You must select an Urgency Degree.");
                return;
            }

            ToDoEntryDTO dto = new ToDoEntryDTO(description, expectedDuration, greenSpaceMapper.toEntity(greenSpace), urgencyDegree);
            if (controller.registerToDoEntry(dto)) {
                toDoListGUIController.update();
                closeWindow(event);
            } else {
                showAlert(Alert.AlertType.ERROR, "Register Error", "This task has already been registered.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Register Error", "Invalid expected duration. Please enter a valid number.");
        }
    }

    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setToDoListGUIController(ToDoListGUIController toDoListGUIController) {
        this.toDoListGUIController = toDoListGUIController;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
