package pt.ipp.isep.dei.esoft.project.ui.gui.controllers1_8;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterSkillController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleCheckupController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VFMMenuGUIController {

    /**
     * The Txt vehicles list text.
     */
    @FXML
    TextArea txtVehicles;

    @FXML
    private Button btnRegisterVehicle;

    @FXML
    private Button btnRegisterCheckUp;

    @FXML
    private Button btnUpdateKm;

    @FXML
    private Button btnNeedingCheckUp;

    @FXML
    private Button btnCancel;



    private RegisterVehicleCheckupController controller = new RegisterVehicleCheckupController();

    @FXML
    public void initialize() {
        // Initialization logic here
        update();
    }

    public void update(){
        txtVehicles.clear();
        ArrayList<Vehicle> vehicles = controller.getVehicles();

        showVehicles(vehicles);

    }

    private void showVehicles(ArrayList<Vehicle> vehiclList) {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------------------------VEHICLES----------------------------------\n");
        sb.append("----------------------------------------------------------------------------\n");
        for (Vehicle vehicle : vehiclList) {
            sb.append(vehicle.getBrand() + " " + vehicle.getModel() + " (" + vehicle.getVehicleID() + ") - Type:" + vehicle.getType() + " Current Kilometers: " + vehicle.getCurrentKm()).append("\n\n");
        }
        txtVehicles.setText(sb.toString());
    }


    @FXML
    private void openRegisterVehicleWindow(ActionEvent event) {
        try {
            File file = new File("src\\main\\resources\\fxml_1-8\\registervehicle.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURL());
            Parent root = loader.load();
            RegisterVehicleGUIController controller1 = loader.getController();
            controller1.setVFMMenuGUIController(this);
            Stage newStage = new Stage();
            newStage.setTitle("Register Vehicle");
            newStage.setScene(new Scene(root));
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openRegisterCheckUpWindow(ActionEvent event) {
        System.out.println("Register CheckUp");
        // Your logic here

    }

    @FXML
    private void openUpdateKmWindow(ActionEvent event) {
        System.out.println("Update Km");
        // Your logic here
    }

    @FXML
    private void handleNeedingCheckUp(ActionEvent event) {
        System.out.println("Needing CheckUp");
        // Your logic here
    }

    @FXML
    public void closeWindow(ActionEvent event){
        try {
            File file = new File("src/main/resources/fxml/loginMenu.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 350, 400));
            // Set the new scene or update the current scene with the new root
            stage.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}