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
import pt.ipp.isep.dei.esoft.project.dto.ToDoEntryDTO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * The type Agenda menu gui controller.
 */
public class AgendaMenuGUIController {
    /**
     * The Btn add agenda entry.
     */
    @FXML
    Button btnAddAgendaEntry;

    /**
     * The Btn add team.
     */
    @FXML
    Button btnAddTeam;

    /**
     * The Btn add vehicles.
     */
    @FXML
    Button btnAddVehicles;

    /**
     * The Btn postpone entry.
     */
    @FXML
    Button btnPostponeEntry;

    /**
     * The Btn cancel entry.
     */
    @FXML
    Button btnCancelEntry;

    /**
     * The Btn go back.
     */
    @FXML
    Button btnGoBack;

    /**
     * The Txt agenda list text.
     */
    @FXML
    TextArea txtAgendaListText;


    private RegisterAgendaEntryController controller = new RegisterAgendaEntryController();

    @FXML
    private void initialize(){
        update();
    }


    /**
     * Update.
     */
    public void update(){
        txtAgendaListText.clear();
        ArrayList<ToDoEntryDTO> toDoEntryDTOsList = controller.getToDoEntryDTOsList();
        for(ToDoEntryDTO toDoEntryDTO : toDoEntryDTOsList){
            txtAgendaListText.appendText(toDoEntryDTO.toString() + "\n");
        }
    }


    public void openRegisterAgendaWindow(ActionEvent event) {
        try {
            File file = new File("src\\main\\resources\\fxml\\registerAgendaEntry.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURL());
            Parent root = loader.load();
            RegisterAgendaEntryGUIController controller1 = loader.getController();
            controller1.setAgendaMenuGUIController(this);
            Stage newStage = new Stage();
            newStage.setTitle("Add Entry to To-Do List");
            newStage.setScene(new Scene(root));
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
