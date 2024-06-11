package pt.ipp.isep.dei.esoft.project.ui.gui.controllers1_8;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillsController;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignVehiclesController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDTO;
import pt.ipp.isep.dei.esoft.project.ui.gui.controllers20_29.AgendaMenuGUIController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AssignSkillsGUIController {


    /**
     * The Cmb agenda entries.
     */
    @FXML
    ListView<Skill> skillListSelection;

    /**
     * The Cmb vehicles.
     */
    @FXML
    ComboBox<Collaborator> cmbCollaborators;

    /**
     * The Btn cancel.
     */
    @FXML
    Button btnCancel;
    /**
     * The Txt area.
     */
    @FXML
    Button btnConfirm;

    private CollaboratorsMenuGUIController collaboratorsMenuGUIController;
    private AssignSkillsController controller = new AssignSkillsController();

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        refreshComboBox();
        skillListSelection.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    public void  refreshComboBox() {
        cmbCollaborators.getItems().setAll(controller.getCollaboratorList());
        skillListSelection.getItems().setAll(controller.getSkillsList());
    }

    private void selectionChanged(ObservableValue<? extends VehicleDTO> observable, VehicleDTO oldValue, VehicleDTO newValue) {
        ObservableList<Skill> selectedItems = skillListSelection.getSelectionModel().getSelectedItems();
        String getSelectedItems = selectedItems.toString();
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


    private void handleClose() {
        Stage stage = (Stage) cmbCollaborators.getScene().getWindow();
        stage.close();
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    @FXML
    public void handleAssignSkills(ActionEvent event) {

        try {
            Collaborator selectedEntry = cmbCollaborators.getValue();
            ArrayList<Skill> skillList = new ArrayList<>(skillListSelection.getSelectionModel().getSelectedItems());

            if (selectedEntry == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "No collaborator selected.");
                return;
            }

            if (skillList == null) {
                showAlert(Alert.AlertType.ERROR, "Error",  "No skills selected");
            }

            if (controller.assignSkills(selectedEntry, skillList)) {
                showAlert(Alert.AlertType.INFORMATION, "Assign Skills", "Skills assigned successfully to collaborator.");
                collaboratorsMenuGUIController.update();
                handleClose();
            } else {
                System.out.println("Failed to assign vehicle.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while assigning skills.");
        }
    }

    public void setCollaboratorMenuGUIController(CollaboratorsMenuGUIController collaboratorsMenuGUIController) {
        this.collaboratorsMenuGUIController = collaboratorsMenuGUIController;
    }
}
