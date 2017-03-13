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
public class PasswordRecoveryStage1Controller implements Initializable {
    
    @FXML
    Button backButton;
    
    @FXML
    Button nextButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        backButton.setDisable(true);
    }    
    
    public void onNextButtonClick() throws IOException {
        Stage window = (Stage)nextButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/PasswordRecoveryStage2.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/passwordrecoverystage2.css");
        
        window.setScene(scene);
    }
    
    private void close() {
        Stage window = (Stage) backButton.getScene().getWindow();
        window.close();
    }
    
    public void onCancelButtonClick() {
        close();
    }
}
