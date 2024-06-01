package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * The type Main app controller.
 */
public class MainAppController {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    /**
     * The Switch button.
     */
    @FXML
    Button switchButton;

    /**
     * Switch scene.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void switchScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(new File("src\\main\\resources\\fxml\\logouttest.fxml").toURL());
        Parent root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
