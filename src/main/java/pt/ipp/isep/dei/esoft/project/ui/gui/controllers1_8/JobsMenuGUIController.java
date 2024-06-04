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
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterJobController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.ui.gui.controllers20_29.PostponeAgendaEntryGUIController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JobsMenuGUIController {

    @FXML
    private Button btnRegisterSkill;

    @FXML
    private Button btnCancel;

    @FXML
    private TextArea txtText;

    private RegisterJobController controller = new RegisterJobController();

    @FXML
    public void initialize() {
        update();
    }


    public void update(){
        txtText.clear();
        ArrayList<Job> jobList = controller.getJobList();

        showGreenSpaces(jobList);

    }
    private void showGreenSpaces(ArrayList<Job> jobList) {
        StringBuilder sb = new StringBuilder();
        sb.append("-------------------JOBS-------------------\n");
        sb.append("------------------------------------------\n");
        for (Job job : jobList) {
            sb.append(job.getJobName()).append("\n");
        }
        txtText.setText(sb.toString());
    }

    @FXML
    private void openRegisterJobWindow() {
        try {
            File file = new File("src\\main\\resources\\fxml_1-8\\registerJob.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURL());
            Parent root = loader.load();
            RegisterJobGUIController controller1 = loader.getController();
            controller1.setJobsMenuGUIController(this);
            Stage newStage = new Stage();
            newStage.setTitle("Register Job");
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