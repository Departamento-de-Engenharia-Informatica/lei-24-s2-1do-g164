package pt.ipp.isep.dei.esoft.project.ui.gui.controllers20_29;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterAgendaEntryController;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MyAgendaGUIController {

    @FXML
    private Button btnSort;
    @FXML
    private ListView<AgendaEntryDTO> list;
    @FXML
    private DatePicker datePicker1;
    @FXML
    private DatePicker datePicker2;
    @FXML
    private ComboBox<EntryStatusENUM> combo;

    private RegisterAgendaEntryController controller = new RegisterAgendaEntryController();

    @FXML
    private void initialize(){
        datePicker1.setValue(null);
        datePicker2.setValue(null);

        datePicker2.setDisable(true);
        combo.setDisable(true);
        ObservableList<EntryStatusENUM> statusList = FXCollections.observableArrayList(EntryStatusENUM.values());

        combo.getItems().clear();

        combo.setItems(statusList);
        btnSort.setDisable(true);
    }

    public void closeWindow(ActionEvent event) {
        try {
            File file = new File("src/main/resources/fxml/collaboratorMenu.fxml");
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

    public void show(ActionEvent actionEvent) {
        ArrayList<AgendaEntryDTO> agendaEntryDTOS = controller.getAgendaEntryListDTO();
        list.getItems().clear();
        sort(agendaEntryDTOS);

        EntryStatusENUM entryStatusENUM = combo.getValue();
        if (entryStatusENUM != null) {
            for (AgendaEntryDTO agendaEntryDTO : agendaEntryDTOS) {
                if (agendaEntryDTO.entryStatus.equals(entryStatusENUM) && agendaEntryDTO.getDate().isBefore(datePicker2.getValue()) &&
                        agendaEntryDTO.getDate().isAfter(datePicker1.getValue())) {
                    list.getItems().add(agendaEntryDTO);
                }
            }
        } else {
            for (AgendaEntryDTO agendaEntryDTO : agendaEntryDTOS) {
                if (agendaEntryDTO.getDate().isBefore(datePicker2.getValue()) &&
                        agendaEntryDTO.getDate().isAfter(datePicker1.getValue())) {
                    list.getItems().add(agendaEntryDTO);
                }
            }
        }

        if (list.getItems().isEmpty()){
            showAlert(Alert.AlertType.INFORMATION, "No Results", "There is no results for your search!");
        }
        initialize();
    }

    private void sort(ArrayList<AgendaEntryDTO> list) {
        Collections.sort(list, Comparator.comparing(AgendaEntryDTO::getDate));
    }

    public void datePicker1Action(ActionEvent event) {
        if (datePicker1.getValue() != null) {
            datePicker2.setDisable(false);
        }
    }

    public void datePicker2Action(ActionEvent event) {
        if (datePicker2.getValue() != null) {
            if (datePicker1.getValue().isBefore(datePicker2.getValue())) {
                combo.setDisable(false);
                btnSort.setDisable(false);
            } else{
                showAlert(Alert.AlertType.ERROR, "Date invalid", "The second date must be after the first one!");
            }
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
