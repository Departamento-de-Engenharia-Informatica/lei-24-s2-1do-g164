package pt.ipp.isep.dei.esoft.project.ui.gui.controllers1_8;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleCheckupController;
import pt.ipp.isep.dei.esoft.project.application.controller.UpdateVehicleCurrentKmController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

public class UpdateKmGUIController {

    @FXML
    private ComboBox<Vehicle> cmbVehicles; // Replace String with your Vehicle object

    @FXML
    private TextField txtNewKm;

    private VFMMenuGUIController vfmMenuGUIController;
    private UpdateVehicleCurrentKmController controller = new UpdateVehicleCurrentKmController();

    private RegisterVehicleCheckupController controller2 = new RegisterVehicleCheckupController();

    public void setVFMMenuGUIController(VFMMenuGUIController vfmMenuGUIController) {
        // Set the controller
        this.vfmMenuGUIController = vfmMenuGUIController;
    }

    @FXML
    public void initialize() {
        // Initialization logic here
        cmbVehicles.getItems().setAll(controller2.getVehicles());
    }

    @FXML
    private void updateKm(ActionEvent event) {
        Vehicle selectedVehicle = cmbVehicles.getValue();
        String newKmStr = txtNewKm.getText();

        if (selectedVehicle == null || newKmStr.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "All fields must be filled!");
            return;
        }

        int newKm;
        try {
            newKm = Integer.parseInt(newKmStr);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Km must be an integer!");
            return;
        }

        if (controller.updateVehicleCurrentKm(selectedVehicle, newKm)) {
            vfmMenuGUIController.update();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Km updated successfully.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Update Error", "Failed to update Km!");
        }
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) txtNewKm.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}