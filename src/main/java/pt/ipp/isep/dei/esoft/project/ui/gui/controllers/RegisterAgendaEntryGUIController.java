package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterAgendaEntryController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoEntryDTO;
import pt.ipp.isep.dei.esoft.project.repository.enums.GreenSpaceType;
import pt.ipp.isep.dei.esoft.project.repository.enums.UrgencyDegree;

import java.time.LocalDate;

public class RegisterAgendaEntryGUIController {

    @FXML
    Button btnAddEntry;
    @FXML
    Button btnCancel;
    @FXML
    ComboBox<ToDoEntryDTO> cmbToDoEntries;
    @FXML
    DatePicker dpStartingDate;

    private AgendaMenuGUIController agendaMenuGUIController;
    private final RegisterAgendaEntryController controller = new RegisterAgendaEntryController();

    @FXML
    private void initialize(){
        cmbToDoEntries.getItems().setAll(controller.getToDoEntryDTOsList());

    }

    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void registerAgendaEntry(ActionEvent event) {
        try {
            ToDoEntryDTO toDoDTO = cmbToDoEntries.getValue();
            LocalDate date = dpStartingDate.getValue();
            AgendaEntryDTO agendaDTO = new AgendaEntryDTO(toDoDTO, date);
            if(controller.registerAgendaEntry(agendaDTO)) {
                agendaMenuGUIController.update();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Register Error");
                alert.setHeaderText("There already exists a task with this name!");
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

    public void setAgendaMenuGUIController(AgendaMenuGUIController toDoListGUIController) {
        this.agendaMenuGUIController = agendaMenuGUIController;
    }

}
