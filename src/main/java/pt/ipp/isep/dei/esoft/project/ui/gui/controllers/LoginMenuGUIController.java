package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class LoginMenuGUIController {

    @FXML
    public Scene scene;

    @FXML
    public Parent root;

    @FXML
    private Stage stage;

    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Button loginButton;
    @FXML
    Button exitButton;

    AuthenticationController controller = new AuthenticationController();

    private boolean doLogin() {
        int maxAttempts = 3;
        boolean success = false;
        do {
            maxAttempts--;
            String id = usernameField.getText();
            String pwd = passwordField.getText();
            success = controller.doLogin(id, pwd);
            if (!success) {
                System.out.println("Invalid UserId and/or Password. \n You have  " + maxAttempts + " more attempt(s).");
            }

        } while (!success && maxAttempts > 0);
        return success;
    }

    @FXML
    private void accessMenu(ActionEvent event) throws IOException {
        boolean success = doLogin();

        if (success) {
            List<UserRoleDTO> roles = this.controller.getUserRoles();
            if ((roles == null) || (roles.isEmpty())) {
                System.out.println("No role assigned to user.");
            } else {
                UserRoleDTO role = roles.get(0);
                if (!Objects.isNull(role)) {
                    this.redirectToRoleGUI(role, event);
                } else {
                    System.out.println("No role selected.");
                }
            }
        }
        this.logout();
    }

    public void logout(){
        controller.doLogout();
    }

    public void redirectToRoleGUI(UserRoleDTO role, ActionEvent event) throws IOException {
        if(role.getDescription().equalsIgnoreCase(controller.ROLE_GSM)){
            switchMenus(event, "todolistmenu");
        }
    }

    public void switchMenus(ActionEvent event, String fileName) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/fxml/" + fileName + ".fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


}
