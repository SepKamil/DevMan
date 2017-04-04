/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.devman.controllers.managerPanel;

import com.mycompany.devman.TaskRepository;
import com.mycompany.devman.TeamRepository;
import com.mycompany.devman.domain.Task;
import com.mycompany.devman.domain.Team;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jakub
 */
public class NewOrEditTaskController implements Initializable {

    @FXML
    private Button cancel;
    
    @FXML
    private TextField name;
    
    @FXML
    private DatePicker startDate;
    
    @FXML
    private DatePicker endDate;
    
    @FXML
    private Spinner predictedTime;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory factory = new SpinnerValueFactory() {
            @Override
            public void decrement(int i) {
                if((int)this.getValue() > 1)
                    this.setValue((int)this.getValue() - 1);
            }

            @Override
            public void increment(int i) {
                if((int) this.getValue() < 1000) {
                    this.setValue((int)this.getValue() + 1);
                }
            }
        };
        predictedTime.setValueFactory(factory);
        predictedTime.getValueFactory().setValue(1);
    } 
    
    private void close() {
        Stage window = (Stage) cancel.getScene().getWindow();
        window.close();
    }
    
    public void onOkButtonClick() {
        Task task = new Task();
        task.setName(name.getText());
        task.setStartDate(startDate.valueProperty().get());
        task.setEndDate(endDate.valueProperty().get());
        task.setPredictedTime((Integer)predictedTime.getValue());
        try {
            TaskRepository.addTask(task);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Błąd dodawania zadania!");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            return;
        }
        close();
    }
    
    public void onCancelButtonClick() {
        close();
    }
    
}
