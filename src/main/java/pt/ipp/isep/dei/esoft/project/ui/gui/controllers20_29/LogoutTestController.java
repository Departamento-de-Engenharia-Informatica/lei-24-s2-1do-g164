package pt.ipp.isep.dei.esoft.project.ui.gui.controllers20_29;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The type Logout test controller.
 */
public class LogoutTestController {

    @FXML
    private Button logoutButton;
    @FXML
    private AnchorPane scenePane;
    /**
     * The Stage.
     */
    Stage stage;

    /**
     * Logout.
     *
     * @param event the event
     */
    public void logout(ActionEvent event){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout");
        alert.setContentText("Do you want to save before exiting");
        if(alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
        }
    }
}
