
package com.mycompany.devman.controllers.managerPanel;

import com.mycompany.devman.repositories.ProjectRepository;
import com.mycompany.devman.repositories.TeamRepository;
import com.mycompany.devman.domain.Project;
import com.mycompany.devman.domain.Team;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jakub
 */
public class AddOrEditTeamController implements Initializable {

    @FXML
    private Button cancel;
    
    @FXML
    private TextField name;
    
    @FXML
    private ChoiceBox projects;
   
    private ManagerPanelController controller;
    
    public AddOrEditTeamController(ManagerPanelController controller) {
        this.controller = controller;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ProjectRepository.findAll().forEach(projects.getItems()::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        projects.getSelectionModel().selectFirst();
    }    
    
    private void close() {
        Stage window = (Stage) cancel.getScene().getWindow();
        window.close();
    }
    
    public void onCancelButtonClick() {
        close();
    }
    
    public void onAddButtonClick() {
        Team team = new Team();
        team.setName(name.getText());
        team.setProject((Project)projects.getSelectionModel().getSelectedItem());
        try {
            TeamRepository.addTeam(team);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Błąd dodawania drużyny!");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        controller.addTeam(team);
        close();
    }
}
