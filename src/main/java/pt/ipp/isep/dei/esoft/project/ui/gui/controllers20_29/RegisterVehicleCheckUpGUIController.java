package pt.ipp.isep.dei.esoft.project.ui.gui.controllers20_29;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleCheckupController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;
import pt.ipp.isep.dei.esoft.project.ui.gui.controllers1_8.VFMMenuGUIController;

import java.time.LocalDate;

public class RegisterVehicleCheckUpGUIController {

    @FXML
    private ComboBox cmbVehicles; // Replace String with your Vehicle object

    @FXML
    private DatePicker CheckdpDate;

    @FXML
    private TextField kmAtCheck;

    private VFMMenuGUIController vfmMenuGUIController;

    private RegisterVehicleCheckupController controller = new RegisterVehicleCheckupController();

    private VehicleRepository repository = new VehicleRepository();

    @FXML
    public void initialize() {
        // Initialization logic here
        cmbVehicles.getItems().setAll(controller.getVehicles());
    }

    @FXML
    private void registerCheckUp(ActionEvent event) {
        Vehicle vehicle = (Vehicle) cmbVehicles.getValue();
        LocalDate checkUpDate = CheckdpDate.getValue();
        String kmAtCheckStr = kmAtCheck.getText();

        if (vehicle == null || checkUpDate == null || kmAtCheckStr.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "All fields must be filled!");
            return;
        }

        int kmAtCheckInt;
        try {
            kmAtCheckInt = Integer.parseInt(kmAtCheckStr);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Km at CheckUp must be an integer!");
            return;
        }

        if (controller.createVehicleCheckup(vehicle, checkUpDate, kmAtCheckInt)) {
            repository.updateVehicleCurrentKm(vehicle, kmAtCheckInt);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Vehicle Check-Up registered successfully.");
            vfmMenuGUIController.update();
            closeWindow(event);

        } else {
            showAlert(Alert.AlertType.ERROR, "Registration Error", "Failed to register vehicle check-up!");
        }
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) cmbVehicles.getScene().getWindow();
        stage.close();
    }
    public void setVFMMenuGUIController(VFMMenuGUIController vfmMenuGUIController) {
        // Set the controller
        this.vfmMenuGUIController = vfmMenuGUIController;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}