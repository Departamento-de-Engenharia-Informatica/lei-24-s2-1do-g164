package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ToDoListGUIController {

    @FXML
    Button btnAddEntry;
    @FXML
    Button btnAddAgendaEntry;
    @FXML
    TextArea txtToDoListText;
    @FXML
    BorderPane borderPane;

    public void openRegisterToDoWindow(ActionEvent event) {
        try {
            File file = new File("src\\main\\resources\\fxml\\registertodoentry.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURL());
            Parent root = loader.load();
            RegisterToDoEntryGUIController controller1 = loader.getController();
            controller1.setToDoListGUIController(this);
            Stage newStage = new Stage();
            newStage.setTitle("Add Entry to To-Do List");
            newStage.setScene(new Scene(root));
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openRegisterAgendaWindow(ActionEvent event) {
        System.out.println("Agenda");
    }
}
