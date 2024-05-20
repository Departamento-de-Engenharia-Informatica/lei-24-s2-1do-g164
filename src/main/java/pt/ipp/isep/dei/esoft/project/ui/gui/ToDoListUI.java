package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class ToDoListUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        File file = new File("src\\main\\resources\\fxml\\todolistmenu.fxml");
        FXMLLoader loader = new FXMLLoader(file.toURL());
        Parent root = loader.load();
        stage.setScene(new Scene(root, 600, 600));
        stage.setTitle("ToDo List");
        stage.show();
    }
}
