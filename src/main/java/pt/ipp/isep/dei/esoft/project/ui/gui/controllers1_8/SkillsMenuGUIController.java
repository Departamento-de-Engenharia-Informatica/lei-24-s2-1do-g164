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
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.ui.gui.controllers20_29.PostponeAgendaEntryGUIController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SkillsMenuGUIController {

    @FXML
    private Button btnRegisterSkill;

    @FXML
    private Button btnCancel;

    @FXML
    private TextArea txtText;

    private RegisterSkillController controller = new RegisterSkillController();

    @FXML
    public void initialize() {
        update();
    }


    public void update(){
        txtText.clear();
        ArrayList<Skill> skillList = controller.getSkillsList();

        showGreenSpaces(skillList);

    }
    private void showGreenSpaces(ArrayList<Skill> skillList) {
        StringBuilder sb = new StringBuilder();
        sb.append("-------------------SKILLS-------------------\n");
        sb.append("--------------------------------------------\n");
        for (Skill skill : skillList) {
            sb.append(skill.getSkillName() + "\n");
        }
        txtText.setText(sb.toString());
    }

    @FXML
    private void openRegisterSkillWindow() {
        try {
            File file = new File("src\\main\\resources\\fxml_1-8\\registerSkill.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURL());
            Parent root = loader.load();
            RegisterSkillGUIController controller1 = loader.getController();
            controller1.setSkillMenuGUIController(this);
            Stage newStage = new Stage();
            newStage.setTitle("Register Skill");
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
}