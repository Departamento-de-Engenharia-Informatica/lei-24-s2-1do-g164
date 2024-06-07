package pt.ipp.isep.dei.esoft.project.ui.gui.controllers1_8;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterJobController;


import java.util.regex.Pattern;


public class RegisterJobGUIController {

    private final static Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s]+$");
    @FXML
    private Button btnRegister;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtName;

    private JobsMenuGUIController jobsMenuGUIController;
    private RegisterJobController controller = new RegisterJobController();

    @FXML
    public void initialize() {
    }


    @FXML
    private void registerJob() {

        if (isValidName(txtName.getText())) {
            if (!controller.registerJob(txtName.getText())) {
                showAlert(Alert.AlertType.ERROR, "Job Error", "Job Already Exists.");
            }else{
                showAlert(Alert.AlertType.INFORMATION, "Success", "Job registered successfully.");
                jobsMenuGUIController.update();
                txtName.clear();
            }
        }else{
            showAlert(Alert.AlertType.ERROR, "Name Error", "Name format is incorrect, only alphabetic characters are allowed.");
        }

    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void setJobsMenuGUIController(JobsMenuGUIController jobsMenuGUIController) {
        // Set the controller
        this.jobsMenuGUIController = jobsMenuGUIController;
    }

    private boolean isValidName(String name) {
        return NAME_PATTERN.matcher(name).matches();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
