package pt.ipp.isep.dei.esoft.project.ui.gui.controllers1_8;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleController;
import pt.ipp.isep.dei.esoft.project.repository.enums.DocumentTypeENUM;
import pt.ipp.isep.dei.esoft.project.repository.enums.VehicleTypeENUM;

import java.time.LocalDate;

public class RegisterVehicleGUIController {

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtModel;

    @FXML
    private DatePicker dpRegistrationDate;

    @FXML
    private DatePicker dpAcquisitionDate;

    @FXML
    private TextField txtCurrentKm;

    @FXML
    private TextField txtCheckupFrequency;

    @FXML
    private TextField txtGrossWeight;

    @FXML
    private TextField txtTare;

    @FXML
    private ComboBox<VehicleTypeENUM> cmbVehicleType; // Replace String with your ENUM type

    @FXML
    private TextField txtVehicleID;

    private RegisterVehicleController controller = new RegisterVehicleController();


    private  VFMMenuGUIController vfmMenuGUIController;

    @FXML
    public void initialize() {
        // Initialization logic here
        cmbVehicleType.getItems().setAll(VehicleTypeENUM.values());
    }

    @FXML
    private void registerVehicle(ActionEvent event) {
        String brand = txtBrand.getText();
        String model = txtModel.getText();
        String vehicleID = txtVehicleID.getText();
        String currentKm = txtCurrentKm.getText();
        String checkupFrequency = txtCheckupFrequency.getText();
        String grossWeight = txtGrossWeight.getText();
        String tare = txtTare.getText();
        LocalDate registerDate = dpRegistrationDate.getValue();
        LocalDate acquisitionDate = dpAcquisitionDate.getValue();

        if (brand.isEmpty() || model.isEmpty() || vehicleID.isEmpty() || currentKm.isEmpty() || checkupFrequency.isEmpty() || grossWeight.isEmpty() || tare.isEmpty() || registerDate == null || acquisitionDate == null || cmbVehicleType.getSelectionModel().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "All fields must be filled!");
            return;
        }
        VehicleTypeENUM type = cmbVehicleType.getValue(); // Assuming the ComboBox is populated with VehicleTypeENUM values

        if (!brand.matches("[a-zA-Z ]+") || !model.matches("[a-zA-Z ]+")) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Brand and Model should only contain alphabetic characters and spaces!");
            return;
        }

        if (!vehicleID.matches("([0-9]{2}-[0-9]{2}-[A-Z]{2})|([0-9]{2}-[A-Z]{2}-[0-9]{2})|([A-Z]{2}-[0-9]{2}-[A-Z]{2})")) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Vehicle ID (License Plate) is not in a valid format!");
            return;
        }

        if (acquisitionDate.isBefore(registerDate)) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Acquisition date should be after the registration date!");
            return;
        }

        int currentKmInt;
        try {
            currentKmInt = Integer.parseInt(currentKm);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Current Km must be an integer!");
            return;
        }

        int checkupFrequencyInt;
        try {
            checkupFrequencyInt = Integer.parseInt(checkupFrequency);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Checkup Frequency must be an integer!");
            return;
        }

        int grossWeightInt;
        try {
            grossWeightInt = Integer.parseInt(grossWeight);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Gross Weight must be an integer!");
            return;
        }

        int tareInt;
        try {
            tareInt = Integer.parseInt(tare);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Tare must be an integer!");
            return;
        }

        if (controller.registerVehicle(brand, model, vehicleID, type, grossWeightInt, tareInt, currentKmInt, registerDate.toString(), acquisitionDate.toString(), checkupFrequencyInt)) {
            vfmMenuGUIController.update();
            txtBrand.clear();
            txtModel.clear();
            txtVehicleID.clear();
            txtCurrentKm.clear();
            txtCheckupFrequency.clear();
            txtGrossWeight.clear();
            txtTare.clear();
            dpRegistrationDate.setValue(null);
            dpAcquisitionDate.setValue(null);
            cmbVehicleType.getSelectionModel().clearSelection();
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Vehicle registered successfully.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Registration Error", "Failed to register vehicle!");
        }
    }

    public void setVFMMenuGUIController(VFMMenuGUIController vfmMenuGUIController) {
        // Set the controller
        this.vfmMenuGUIController = vfmMenuGUIController;
    }


    @FXML
    private void closeWindow() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}