package com.mycompany.devman.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kuba3
 */
public class LoginController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    
    public void onRegisterButtonClick() throws IOException {
        Stage registerWindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Register.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");
        
        registerWindow.setTitle("DevMan - Rejestracja");
        registerWindow.setResizable(false);
        registerWindow.setScene(scene);
        registerWindow.setX(20);
        registerWindow.setY(20);
        registerWindow.show();
    }
    
    public void onLoginButtonClick() throws IOException {
        Stage managerWindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ManagerPanel.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/managerpanel.css");
        
        managerWindow.setTitle("DevMan - Panel Menadżera");
        managerWindow.setResizable(false);
        managerWindow.setScene(scene);
        managerWindow.setX(20);
        managerWindow.setY(20);
        managerWindow.show();
        
        Stage employeeWindow = new Stage();
        Parent root2 = FXMLLoader.load(getClass().getResource("/fxml/EmployeePanel.fxml"));
        
        Scene scene2 = new Scene(root2);
        scene2.getStylesheets().add("/styles/employeepanel.css");
        
        employeeWindow.setTitle("DevMan - Panel Pracownika");
        employeeWindow.setResizable(false);
        employeeWindow.setScene(scene2);
        employeeWindow.setX(300);
        employeeWindow.setY(300);
        employeeWindow.show();
    }
    
    public void onRecoveryButtonClick() throws IOException {
        Stage recoveryWindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/PasswordRecoveryStage1.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/passwordrecoverystage1.css");
        
        recoveryWindow.setTitle("DevMan - Przypominanie hasła");
        recoveryWindow.setResizable(false);
        recoveryWindow.setScene(scene);
        recoveryWindow.setX(20);
        recoveryWindow.setY(20);
        recoveryWindow.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
