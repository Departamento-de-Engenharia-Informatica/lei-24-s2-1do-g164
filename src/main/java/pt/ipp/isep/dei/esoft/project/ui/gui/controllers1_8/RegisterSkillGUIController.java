package pt.ipp.isep.dei.esoft.project.ui.gui.controllers1_8;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterSkillController;
import pt.ipp.isep.dei.esoft.project.ui.gui.controllers20_29.GreenSpaceMenuGUIController;

import java.util.regex.Pattern;


public class RegisterSkillGUIController {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s]+$");

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtName;

    private SkillsMenuGUIController skillsMenuGUIController;
    private RegisterSkillController controller = new RegisterSkillController();

    @FXML
    public void initialize() {
    }

    @FXML
    private void registerSkill() {

        if (isValidName(txtName.getText())) {
            if (!controller.registerSkill(txtName.getText())) {
                showAlert(Alert.AlertType.ERROR, "Skill Error", "Skill Already Exists.");
            }else{
                showAlert(Alert.AlertType.INFORMATION, "Success", "Skill registered successfully.");
                skillsMenuGUIController.update();
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

    public void setSkillMenuGUIController(SkillsMenuGUIController skillMenuGUIController) {
        // Set the controller
        this.skillsMenuGUIController = skillMenuGUIController;
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