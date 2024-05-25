package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.repository.enums.GreenSpaceTypeENUM;

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

    @FXML
    private void initialize() {
        cmbGreenSpaceTypes.getItems().setAll(GreenSpaceTypeENUM.values());
    }

    public void registerGreenSpace(ActionEvent event) {
        try {
            String name = txtName.getText();
            if (name == null || name.trim().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "Name cannot be empty.");
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
            GreenSpaceDTO dto = new GreenSpaceDTO(name, address, area, type);
            if (controller.registerGreenSpace(dto)) {
                System.out.println(controller.getGreenSpaceList());
                greenSpaceMenuGUIController.update();
                closeWindow(event);
            } else {
                showAlert(Alert.AlertType.ERROR, "Register Error", "There already exists a Green Space with this name!");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Register Error", "Invalid area. Please enter a valid number.");
        }
    }

    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setGreenSpaceGUIController(GreenSpaceMenuGUIController greenSpaceMenuGUIController) {
        this.greenSpaceMenuGUIController = greenSpaceMenuGUIController;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
