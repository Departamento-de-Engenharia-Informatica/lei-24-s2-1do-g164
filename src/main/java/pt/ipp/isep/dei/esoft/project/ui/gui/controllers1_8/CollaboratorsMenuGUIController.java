package pt.ipp.isep.dei.esoft.project.ui.gui.controllers1_8;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterJobController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CollaboratorsMenuGUIController {

    @FXML
    public Button btnAssignSkills;
    @FXML
    private Button btnRegisterSkill;

    @FXML
    private Button btnCancel;

    @FXML
    private TextArea txtText;

    private RegisterCollaboratorController controller = new RegisterCollaboratorController();

    @FXML
    public void initialize() {
        update();
    }


    public void update(){
        txtText.clear();
        ArrayList<Collaborator> collaboratorsList = controller.getCollaboratorList();

        showGreenSpaces(collaboratorsList);

    }
    private void showGreenSpaces(ArrayList<Collaborator> collaboratorList) {
        StringBuilder sb = new StringBuilder();
        sb.append("-----------------------------COLLABORATORS-----------------------------\n");
        sb.append("-----------------------------------------------------------------------\n");
        for (Collaborator collaborator : collaboratorList) {
            sb.append(collaborator.getName()).append(" - ").append(collaborator.getSkills()).append("\n");
        }
        txtText.setText(sb.toString());
    }

    @FXML
    private void openRegisterCollaboratorWindow() {
        try {
            File file = new File("src\\main\\resources\\fxml_1-8\\registerCollaborator.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURL());
            Parent root = loader.load();

            RegisterCollaboratorGUIController controller = loader.getController();
            controller.setCollaboratorMenuGUIController(this);  // Set the collaboratorsMenuGUIController instance

            Stage newStage = new Stage();
            newStage.setTitle("Register Collaborator");
            newStage.setScene(new Scene(root));
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void closeWindow(ActionEvent event){
        try {
            File file = new File("src/main/resources/fxml/hrmMenu.fxml");
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

    public void openAssignSkillsWindow(ActionEvent event) {
        try {
            File file = new File("src\\main\\resources\\fxml_1-8\\assignSkills.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURL());
            Parent root = loader.load();

            AssignSkillsGUIController controller = loader.getController();
            controller.setCollaboratorMenuGUIController(this);  // Set the collaboratorsMenuGUIController instance

            Stage newStage = new Stage();
            newStage.setTitle("Register Collaborator");
            newStage.setScene(new Scene(root));
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
