/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.devman.controllers.managerPanel;

import com.mycompany.devman.ProjectRepository;
import com.mycompany.devman.domain.Project;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jakub
 */
public class AddOrEditProjectController implements Initializable {

    @FXML
    private Button cancel;
    
    @FXML
    private TextField name;
    
    @FXML
    private DatePicker startDate;
    
    @FXML
    private DatePicker endDate;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void close() {
        Stage window = (Stage) cancel.getScene().getWindow();
        window.close();
    }
    
    public void onOkButtonClick() {
        Project project = new Project();
        project.setName(name.getText());
        project.setStartDate(startDate.valueProperty().get());
        project.setEndDate(endDate.valueProperty().get());
        try {
            ProjectRepository.addProject(project);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Błąd dodawania projektu!");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        close();
    }
    
    public void onCancelButtonClick() {
        close();
    }
}
