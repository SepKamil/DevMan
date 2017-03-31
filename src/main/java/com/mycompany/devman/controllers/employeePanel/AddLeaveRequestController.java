/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.devman.controllers.employeePanel;

import com.mycompany.devman.LeaveRepository;
import com.mycompany.devman.domain.Leave;
import com.mycompany.devman.domain.User;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author jakub
 */
public class AddLeaveRequestController implements Initializable {

    @FXML
    private Button cancel;
    
    @FXML
    private DatePicker startDate;
    
    @FXML
    private Spinner numberOfDays;
    
    private User currentUser;
    
    private EmployeePanelController controller;
    
    public AddLeaveRequestController(User user, EmployeePanelController controller) {
        this.currentUser = user;
        this.controller = controller;
    }
    
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
                if((int) this.getValue() < 14) {
                    this.setValue((int)this.getValue() + 1);
                }
            }
        };
        numberOfDays.setValueFactory(factory);
        numberOfDays.getValueFactory().setValue(1);
    }    
    
    private void close() {
        Stage window = (Stage) cancel.getScene().getWindow();
        window.close();
    }
    
    public void onCancelButtonClick() {
        close();
    }
    
    public void onOkButtonClick() {
        Leave leave = new Leave();
        LocalDate date = startDate.valueProperty().get();
        leave.setNumberOfDays((Integer)numberOfDays.getValueFactory().getValue());
        leave.setStartDate(date);
        leave.setEmployee(currentUser);
        try {
            LeaveRepository.addNewLeaveRequest(leave);
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Bląd");
            alert.setHeaderText("Błąd składania wniosku!");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacja");
        alert.setHeaderText("Wniosek zlożony!");
        alert.setContentText("Wniosek zostanie przekazany do rozpatrzenia przez menadżera. Otrzymasz powiadomienie o jego zaakceptowaniu bądź odrzuceniu.");
        alert.showAndWait().ifPresent(rs -> {
        if (rs == ButtonType.OK) {
            close();
        }
        controller.addNewLeaveRequest(leave);
      });
    }
}
