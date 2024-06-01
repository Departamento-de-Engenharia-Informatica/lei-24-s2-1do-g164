package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.controllers.GSMMenuGUIController;

import java.io.IOException;

/**
 * The type Collaborator menu gui.
 */
public class CollaboratorMenuGUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/collaboratorMenu.fxml"));
            Parent root = loader.load();


            primaryStage.setTitle("Green Spaces Manager Menu");
            primaryStage.setScene(new Scene(root, 400, 400));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
