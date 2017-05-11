/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.devman.controllers.employeePanel;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.mycompany.devman.domain.Task;
import com.mycompany.devman.domain.WorkTime;
import com.mycompany.devman.repositories.WorkTimeRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jakub
 */
public class NewWorkTimeController implements Initializable {

    @FXML
    private Button cancel;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Spinner time;

    private Task task;

    private WorkTime workTime;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(workTime == null) {
            datePicker.setValue(LocalDate.now());
        }
        else {
            datePicker.setValue(workTime.getDate());
        }
    }

    private void setUpSpinner() {
        SpinnerValueFactory factory = new SpinnerValueFactory() {
            @Override
            public void decrement(int i) {
                if((int)this.getValue() > 1)
                    this.setValue((int)this.getValue() - 1);
            }

            @Override
            public void increment(int i) {
                if((int) this.getValue() < 14) {
                    this.setValue((int)this.getValue() + 1);
                }
            }
        };
        time.setValueFactory(factory);
        time.getValueFactory().setValue(1);
    }

    public NewWorkTimeController(Task task) {
        this.task = task;
    }

    public NewWorkTimeController(Task task, WorkTime workTime) {
        this.task = task;
        this.workTime = workTime;
    }

    private void close() {
        Stage window = (Stage) cancel.getScene().getWindow();
        window.close();
    }
    
    public void onOkButtonClick() {
        boolean newWorkTime = false;
        if(workTime == null) {
            workTime = new WorkTime();
            newWorkTime = true;
        }
        workTime.setTask(task);
        workTime.setWorkTime((Integer)time.getValue());
        workTime.setDate(datePicker.getValue());
        try {
            if (newWorkTime) {
                WorkTimeRepository.addNewWorkTime(workTime);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Błąd logowania czasu!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        }
    }
    
    public void onCancelButtonClick() {
        close();
    }
    
}
