package pt.ipp.isep.dei.esoft.project.ui.gui.controllers1_8;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.enums.DocumentTypeENUM;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class RegisterCollaboratorGUIController {
    private final static Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s]+$");
    private final static Pattern ADDRESS_PATTERN = Pattern.compile("^[a-zA-Z0-9\\s,]+$");
    private final static Pattern PHONE_PATTERN = Pattern.compile("^[0-9]{9}$");
    private final static Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    private final static Pattern TAXPAYER_PATTERN = Pattern.compile("^[0-9]{9}$");
    private final static Pattern DOCUMENT_NUMBER_PATTERN = Pattern.compile("^[0-9]{8}$");



    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtAddress;

    @FXML
    private ComboBox cmbIDDocumentTypes;

    @FXML
    private DatePicker dtBirthdate;

    @FXML
    private TextField txtEmail;

    @FXML
    private DatePicker dtAddmissionDate;

    @FXML
    private TextField txtDocumentNumber;

    @FXML
    private TextField txtTaxpayerNumber;

    @FXML
    private ComboBox cmbJobs;

    private CollaboratorsMenuGUIController collaboratorsMenuGUIController;
    private RegisterCollaboratorController controller = new RegisterCollaboratorController();

    @FXML
    private void initialize() {
        cmbIDDocumentTypes.getItems().setAll(DocumentTypeENUM.values());
        cmbJobs.getItems().setAll(controller.getJobList());
    }

    /**
     * Register green space.
     *
     * @param event the event
     */
    public void registerCollaborator(ActionEvent event) {
        try {
            String name = txtName.getText();
            if (name == null || name.trim().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "Name cannot be empty.");
                return;
            }
            if (!isValidName(name) ){
                showAlert(Alert.AlertType.ERROR, "Register Error", "Name cannot have special characters.");
                return;
            }

            String phone = txtPhone.getText();
            if (phone == null || phone.trim().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "Phone number cannot be empty.");
                return;
            }
            if (!isValidPhone(phone) ){
                showAlert(Alert.AlertType.ERROR, "Register Error", "Phone number cannot have special characters.");
                return;
            }

            String address = txtAddress.getText();
            if (address == null || address.trim().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "Address cannot be empty.");
                return;
            }
            if (!isValidAddress(address) ){
                showAlert(Alert.AlertType.ERROR, "Register Error", "Address cannot have special characters.");
                return;
            }

            String email = txtEmail.getText();
            if (email == null || email.trim().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "Email cannot be empty.");
                return;
            }
            if (!isValidEmail(email) ){
                showAlert(Alert.AlertType.ERROR, "Register Error", "Email is not in the correct format.");
                return;
            }

            String taxpayerNumber = txtTaxpayerNumber.getText();
            if (taxpayerNumber == null || taxpayerNumber.trim().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "Taxpayer number cannot be empty.");
                return;
            }
            if (!isValidTaxpayerNumber(taxpayerNumber) ){
                showAlert(Alert.AlertType.ERROR, "Register Error", "Taxpayer number must have 9 digits.");
                return;
            }



            DocumentTypeENUM type = (DocumentTypeENUM) cmbIDDocumentTypes.getSelectionModel().getSelectedItem();
            if (type == null) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "You must select an ID Document type.");
                return;
            }

            LocalDate birthdate = dtBirthdate.getValue();
            if (birthdate == null) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "Birthdate cannot be empty.");
                return;
            }
            if (!isValidBirthdate(birthdate)) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "Collaborator must be at least 18 years old.");
                return;
            }

            LocalDate admissionDate = dtAddmissionDate.getValue();
            if (admissionDate == null) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "Admission date cannot be empty.");
                return;
            }
            if (!isValidAdmissionDate(birthdate, admissionDate)) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "Admission date must be after birthdate.");
                return;
            }


            Job job = (Job) cmbJobs.getSelectionModel().getSelectedItem();
            if (job == null) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "You must select a Job.");
                return;
            }

            String documentNumber = txtDocumentNumber.getText();
            if (documentNumber == null || documentNumber.trim().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Register Error", "Document number cannot be empty.");
                return;
            }
            if (!isValidDocumentNumber(documentNumber) ){
                showAlert(Alert.AlertType.ERROR, "Register Error", "Document number needs to have 8 numbers.");
                return;
            }




            if (controller.registerCollaborator(name, Integer.parseInt(phone), String.valueOf(birthdate), String.valueOf(admissionDate), address, Integer.parseInt(documentNumber) ,job, type,Integer.parseInt(taxpayerNumber), email)) {
                System.out.println(controller.getCollaboratorList());
                collaboratorsMenuGUIController.update();
                closeWindow(event);
            } else {
                showAlert(Alert.AlertType.ERROR, "Register Error", "There already exists a Collaborator with this taxpayer number!");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Register Error", "Invalid. Please enter valid credentials.");
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

    public void setCollaboratorMenuGUIController(CollaboratorsMenuGUIController collaboratorMenuGUIController) {
    this.collaboratorsMenuGUIController = collaboratorMenuGUIController;
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

    private boolean isValidPhone(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }

    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean isValidTaxpayerNumber(String taxpayerNumber) {
        return TAXPAYER_PATTERN.matcher(taxpayerNumber).matches();
    }

    private boolean isValidAddress(String address) {
        return ADDRESS_PATTERN.matcher(address).matches();
    }

    private boolean isValidDocumentNumber(String documentNumber) {
        return DOCUMENT_NUMBER_PATTERN.matcher(documentNumber).matches();
    }

    private boolean isValidBirthdate(LocalDate birthdate) {
        return birthdate.plusYears(18).isBefore(LocalDate.now());
    }

    private boolean isValidAdmissionDate(LocalDate birthdate, LocalDate admissionDate) {
        return admissionDate.minusYears(18).isAfter(birthdate);
    }


}
