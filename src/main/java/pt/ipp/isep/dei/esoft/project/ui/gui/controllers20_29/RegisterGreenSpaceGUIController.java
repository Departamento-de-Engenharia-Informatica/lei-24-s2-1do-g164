package pt.ipp.isep.dei.esoft.project.ui.gui.controllers20_29;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.repository.enums.GreenSpaceTypeENUM;

import java.util.regex.Pattern;

/**
 * The type Register green space gui controller.
 */
public class RegisterGreenSpaceGUIController {
    @FXML
    private Button btnAddEntry;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtArea;
    @FXML
    private TextField txtAddress;
    @FXML
    private ComboBox<GreenSpaceTypeENUM> cmbGreenSpaceTypes;

    private GreenSpaceMenuGUIController greenSpaceMenuGUIController;
    private RegisterGreenSpaceController controller = new RegisterGreenSpaceController();
    private AuthenticationController authenticationController = new AuthenticationController();

    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s]+$");


    @FXML
    private void initialize() {
        cmbGreenSpaceTypes.getItems().setAll(GreenSpaceTypeENUM.values());
    }

    /**
     * Register green space.
     *
     * @param event the event
     */
    public void registerGreenSpace(ActionEvent event) {
        try {
            String name = txtName.getText();
            if (name == null || name.trim().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "Name cannot be empty.");
                return;
            }
            if (!isValidName(name)) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "Name can't have special characters");
                return;
            }

            String address = txtAddress.getText();
            if (address == null || address.trim().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "Address cannot be empty.");
                return;
            }


            int area = Integer.parseInt(txtArea.getText());
            if (area <= 0) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "The area must be a positive number.");
                return;
            }

            GreenSpaceTypeENUM type = cmbGreenSpaceTypes.getSelectionModel().getSelectedItem();
            if (type == null) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "You must select a Green Space type.");
                return;
            }
            GreenSpaceDTO dto = new GreenSpaceDTO(name, address, area, type, authenticationController.getCurrentUserEmail());
            if (controller.registerGreenSpace(dto)) {
                showAlert(Alert.AlertType.INFORMATION, "Register Green Space", "Green Space registered successfully.");
                greenSpaceMenuGUIController.update();
                closeWindow(event);
            } else {
                showAlert(Alert.AlertType.ERROR, "Register Error", "There already exists a Green Space with this name!");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Register Error", "Invalid area. Please enter a valid number.");
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
     * Sets green space gui controller.
     *
     * @param greenSpaceMenuGUIController the green space menu gui controller
     */
    public void setGreenSpaceGUIController(GreenSpaceMenuGUIController greenSpaceMenuGUIController) {
        this.greenSpaceMenuGUIController = greenSpaceMenuGUIController;
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
