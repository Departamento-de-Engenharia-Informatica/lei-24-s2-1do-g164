package pt.ipp.isep.dei.esoft.project.ui.gui.controllers20_29;

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
import pt.ipp.isep.dei.esoft.project.application.controller.ListGreenSpacesController;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The type Green space menu gui controller.
 */
public class GreenSpaceMenuGUIController {

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
     * The Txt green space list text.
     */
    @FXML
    TextArea txtGreenSpaceListText;
    /**
     * The Border pane.
     */
    @FXML
    BorderPane borderPane;

    private ListGreenSpacesController controller = new ListGreenSpacesController();



    @FXML
    private void initialize(){
        update();
    }

    /**
     * Update.
     */
    public void update(){
        txtGreenSpaceListText.clear();
        ArrayList<GreenSpaceDTO> greenSpaceDTOsList = controller.getGreenSpaceDTOsList();

        showGreenSpaces(greenSpaceDTOsList);

    }
    private void showGreenSpaces(ArrayList<GreenSpaceDTO> greenSpaceDTOsList) {
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------GREEN SPACES---------------------------------\n");
        for (GreenSpaceDTO greenSpaceDTO : greenSpaceDTOsList) {
            sb.append("Name: " + greenSpaceDTO.name + " Address: " + greenSpaceDTO.address + " Area: " + greenSpaceDTO.area + " Type: " + greenSpaceDTO.type + "\n\n");
        }
        txtGreenSpaceListText.setText(sb.toString());
    }

    /**
     * Open register green space window.
     *
     * @param event the event
     */
    public void openRegisterGreenSpaceWindow(ActionEvent event) {
        try {
            File file = new File("src\\main\\resources\\fxml\\registergreenspace.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURL());
            Parent root = loader.load();
            RegisterGreenSpaceGUIController controller1 = loader.getController();
            controller1.setGreenSpaceGUIController(this);
            Stage newStage = new Stage();
            newStage.setTitle("Register Green Space");
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
            stage.setScene(new Scene(root, 400, 400));
            // Set the new scene or update the current scene with the new root
            stage.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sort.
     *
     * @param actionEvent the action event
     */
    public void sort(ActionEvent actionEvent) {
        txtGreenSpaceListText.clear();
        ArrayList<GreenSpaceDTO> greenSpaceDTOsList = controller.getGreenSpaceDTOsList();

        controller.sortGreenSpaces(greenSpaceDTOsList);

        showGreenSpaces(greenSpaceDTOsList);
    }
}
