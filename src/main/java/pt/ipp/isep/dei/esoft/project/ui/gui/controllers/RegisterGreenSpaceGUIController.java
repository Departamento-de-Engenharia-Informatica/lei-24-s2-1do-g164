package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.repository.enums.GreenSpaceType;

public class RegisterGreenSpaceGUIController {
    @FXML
    Button btnAddEntry;
    @FXML
    Button btnCancel;
    @FXML
    TextField txtName;
    @FXML
    TextField txtArea;
    @FXML
    TextField txtAddress;
    @FXML
    ComboBox<GreenSpaceType> cmbGreenSpaceTypes;
    private GreenSpaceMenuGUIController greenSpaceMenuGUIController;
    private RegisterGreenSpaceController controller = new RegisterGreenSpaceController();

    @FXML
    private void initialize(){
        cmbGreenSpaceTypes.getItems().setAll(GreenSpaceType.values());
    }

    public void registerGreenSpace(ActionEvent event) {
        try {
            int area = Integer.parseInt(txtArea.getText());
            GreenSpaceDTO dto = new GreenSpaceDTO(txtName.getText(), txtAddress.getText(), area, cmbGreenSpaceTypes.getValue());
            if(controller.registerGreenSpace(dto)) {
                greenSpaceMenuGUIController.update();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Register Error");
                alert.setHeaderText("There already exists a Green Space with this name!");
                alert.show();
            }
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Register Error");
            alert.setHeaderText("Invalid inputs.");
            alert.show();
        }
    }

    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setGreenSpaceGUIController(GreenSpaceMenuGUIController greenSpaceMenuGUIController) {
        this.greenSpaceMenuGUIController = greenSpaceMenuGUIController;
    }
}

