package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.controllers20_29.GSMMenuGUIController;

import java.io.IOException;

/**
 * The type Gsm menu gui.
 */
public class GSMMenuGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gsmMenu.fxml"));
            Parent root = loader.load();

            // Get the controller from the loader
            GSMMenuGUIController controller = loader.getController();

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
