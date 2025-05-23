package pt.ipp.isep.dei.esoft.project.ui.gui.controllers20_29;

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

import java.util.regex.Pattern;

/**
 * The type Register to do entry gui controller.
 */
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

    private final RegisterToDoEntryController controller = new RegisterToDoEntryController();
    private final GreenSpaceMapper greenSpaceMapper = new GreenSpaceMapper();

    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s]+$");



    @FXML
    private void initialize() {
        cmbUrgencyDegree.getItems().setAll(UrgencyDegreeENUM.values());
        cmbGreenSpaces.getItems().setAll(controller.getGreenSpaceDTOsList());
    }

    /**
     * Register to do entry.
     *
     * @param event the event
     */
    public void registerToDoEntry(ActionEvent event) {
        try {
            String description = txtDescription.getText();
            if (description == null || description.trim().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "Description cannot be empty.");
                return;
            }

            if (!isValidName(description)) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "Description can't have special characters");
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
                showAlert(Alert.AlertType.INFORMATION, "Register To-do Entry", "To-do Entry registered successfully.");
                toDoListGUIController.update();
                closeWindow(event);
            } else {
                showAlert(Alert.AlertType.ERROR, "Register Error", "This task has already been registered.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Register Error", "Invalid expected duration. Please enter a valid number.");
        }
    }

    /**
     * Close window.
     *
     * @param event the event
     */
    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Sets to do list gui controller.
     *
     * @param toDoListGUIController the to do list gui controller
     */
    public void setToDoListGUIController(ToDoListGUIController toDoListGUIController) {
        this.toDoListGUIController = toDoListGUIController;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    private boolean isValidName(String name) {
        return NAME_PATTERN.matcher(name).matches();
    }
}
