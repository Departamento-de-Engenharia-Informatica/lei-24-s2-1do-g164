package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterAgendaEntryController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterToDoEntryController;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoEntryDTO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AgendaMenuGUIController {

    @FXML
    Button btnAddAgendaEntry;
    @FXML
    Button btnCancel;
    @FXML
    Button btnAddAgendaEntry1;
    @FXML
    Button btnAddAgendaEntry11;
    @FXML
    Button btnAddAgendaEntry2;
    @FXML
    Button btnAddAgendaEntry21;
    @FXML
    TextArea txtAgendaListText;
    @FXML
    BorderPane borderPane;

    @FXML
    TextArea txtText;

    private RegisterToDoEntryController controller = new RegisterToDoEntryController();

    @FXML
    private void initialize(){
        update();
    }

    public void update(){
        txtText.clear();
        ArrayList<ToDoEntryDTO> toDoEntryDTOsList = controller.getToDoEntryDTOsList();
        for(ToDoEntryDTO toDoEntryDTO : toDoEntryDTOsList){
            txtText.appendText(toDoEntryDTO.toString() + "\n");
        }
    }

    public void openAssignTeamAgendaWindow(ActionEvent event) {
        try {
            File file = new File("src\\main\\resources\\fxml\\assignTeamToAgendaEntry.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURL());
            Parent root = loader.load();
            TeamtoAgendaMenuGUIController teamtoAgendaMenuGUIController = loader.getController();
            teamtoAgendaMenuGUIController.setTeamtoAgendaMenuGUIController(this);
            Stage newStage = new Stage();
            newStage.setTitle("Assign Team To Agenda Entry");
            newStage.setScene(new Scene(root));
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeWindow(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
