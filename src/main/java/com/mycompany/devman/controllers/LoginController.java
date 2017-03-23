package com.mycompany.devman.controllers;


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
     *  Creates a window used for a new developer to register into the system.
     *  IO Exception may occur if files /fxml/Register.fxml 
     *  or /styles/register.css are missing
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
    /**
     *  Creates a window used for logging into the application.
     *  IO Exception may occur if files /fxml/ManagerPanel.fxml, /styles/managerpanel.css 
     *  /fxml/EmployeePanel.fxml or /styles/employeepanel.css are missing
     */
    
    public void onLoginButtonClick() throws IOException {
        Stage managerWindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/managerPanel/ManagerPanel.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/managerpanel.css");
        
        managerWindow.setTitle("DevMan - Panel Menadżera");
        managerWindow.setResizable(false);
        managerWindow.setScene(scene);
        managerWindow.setX(20);
        managerWindow.setY(20);
        managerWindow.show();
        
        Stage employeeWindow = new Stage();
        Parent root2 = FXMLLoader.load(getClass().getResource("/fxml/employeePanel/EmployeePanel.fxml"));
        
        Scene scene2 = new Scene(root2);
        scene2.getStylesheets().add("/styles/employeepanel.css");
        
        employeeWindow.setTitle("DevMan - Panel Pracownika");
        employeeWindow.setResizable(false);
        employeeWindow.setScene(scene2);
        employeeWindow.setX(300);
        employeeWindow.setY(300);
        employeeWindow.show();
    }
    
    /**
     *  Creates a window used for recovering forgotten passwords.
     *  IO Exception may occur if files /fxml/PasswordRecoveryStage1.fxml 
     *  or /styles/passwordrecoverystage1.css are missing
     */
    
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
