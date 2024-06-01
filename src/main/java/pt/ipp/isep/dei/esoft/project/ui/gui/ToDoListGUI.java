package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.File;

/**
 * The type To do list gui.
 */
public class ToDoListGUI extends Application {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        File file = new File("src\\main\\resources\\fxml\\todolistmenu.fxml");
        FXMLLoader loader = new FXMLLoader(file.toURL());
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("To-Do List");
        stage.show();
    }
}
