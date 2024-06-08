package pt.ipp.isep.dei.esoft.project.ui.gui.controllers20_29;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterAgendaEntryController;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoEntryDTO;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;

import java.time.LocalDate;

/**
 * The type Register agenda entry gui controller.
 */
public class RegisterAgendaEntryGUIController {

    /**
     * The Btn add entry.
     */
    @FXML
    Button btnAddEntry;
    /**
     * The Btn cancel.
     */
    @FXML
    Button btnCancel;
    /**
     * The Cmb to do entries.
     */
    @FXML
    ComboBox<ToDoEntryDTO> cmbToDoEntries;
    /**
     * The Dp starting date.
     */
    @FXML
    DatePicker dpStartingDate;

    private AgendaMenuGUIController agendaMenuGUIController;

    private final RegisterAgendaEntryController controller = new RegisterAgendaEntryController();

    @FXML
    private void initialize(){
        cmbToDoEntries.getItems().setAll(controller.getToDoEntryDTOsList());

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
     * Register agenda entry.
     *
     * @param event the event
     */
    public void registerAgendaEntry(ActionEvent event) {
        try {
            LocalDate date = dpStartingDate.getValue();

            if (date == null){
                showAlert(Alert.AlertType.ERROR, "Register Error", "There is no date selected.");
                return;
            }

            ToDoEntryDTO toDoDTO = cmbToDoEntries.getValue();

            if (toDoDTO == null){
                showAlert(Alert.AlertType.ERROR, "Register Error", "There is no To-Do entry selected.");
                return;
            }

            controller.updateToDoEntry(toDoDTO);
            toDoDTO.entryStatus = EntryStatusENUM.PLANNED;
            AgendaEntryDTO agendaDTO = new AgendaEntryDTO(toDoDTO, date);
            if(controller.registerAgendaEntry(agendaDTO)) {
                showAlert(Alert.AlertType.INFORMATION, "Register Agenda Entry", "Agenda Entry registered successfully.");

                agendaMenuGUIController.update();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Register Error");
                alert.setHeaderText("The entry already exists!");
                alert.show();
            }
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Register Error");
            alert.setHeaderText("Invalid inputs.");
            alert.show();
        }
    }

    /**
     * Sets agenda menu gui controller.
     *
     * @param agendaListGUIController the agenda list gui controller
     */
    public void setAgendaMenuGUIController(AgendaMenuGUIController agendaListGUIController) {
        this.agendaMenuGUIController = agendaListGUIController;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
