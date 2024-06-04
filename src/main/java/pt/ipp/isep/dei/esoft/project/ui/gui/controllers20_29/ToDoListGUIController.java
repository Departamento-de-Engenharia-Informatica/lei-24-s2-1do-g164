package pt.ipp.isep.dei.esoft.project.ui.gui.controllers20_29;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterToDoEntryController;
import pt.ipp.isep.dei.esoft.project.dto.ToDoEntryDTO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The type To do list gui controller.
 */
public class ToDoListGUIController {

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
     * The Txt to do list text.
     */
    @FXML
    TextArea txtToDoListText;

    private RegisterToDoEntryController controller = new RegisterToDoEntryController();

    @FXML
    private void initialize(){
        update();
    }

    /**
     * Update.
     */
    public void update(){
        txtToDoListText.clear();
        ArrayList<ToDoEntryDTO> toDoEntryDTOsList = controller.getToDoEntryDTOsList();
        for(ToDoEntryDTO toDoEntryDTO : toDoEntryDTOsList){
            txtToDoListText.appendText(toDoEntryDTO.toString() + "\n");
        }
    }

    /**
     * Open register to do window.
     *
     * @param event the event
     */
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

    /**
     * Close window.
     *
     * @param event the event
     */
    public void closeWindow(ActionEvent event){
        try {
            File file = new File("src/main/resources/fxml/gsmMenu.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Set the new scene
            stage.setScene(new Scene(root));
            // Optionally, set the title or other properties of the stage if needed
            stage.setTitle("GSM Menu");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
