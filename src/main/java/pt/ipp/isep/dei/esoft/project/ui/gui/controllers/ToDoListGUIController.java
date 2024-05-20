package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

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
        System.out.println("Window");
    }

    public void openRegisterAgendaWindow(ActionEvent event) {
        System.out.println("Agenda");
    }
}
