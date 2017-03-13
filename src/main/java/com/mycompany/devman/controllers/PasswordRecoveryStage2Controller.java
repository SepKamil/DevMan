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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kuba3
 */
public class PasswordRecoveryStage2Controller implements Initializable {

    @FXML
    Button finishButton;
    
    private void close() {
        Stage window = (Stage) finishButton.getScene().getWindow();
        window.close();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void onFinishButtonClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacja");
        alert.setHeaderText("Przypominanie hasła.");
        alert.setContentText("Podane hasło stanie się twoim hasłem tymczasowym. Możesz je zmienić po zalogowaniu.");
        alert.showAndWait().ifPresent(rs -> {
        if (rs == ButtonType.OK) {
            close();
        }
      });
    }
    
    public void onBackButtonClick() throws IOException {
        Stage window = (Stage)finishButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/PasswordRecoveryStage1.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/passwordrecoverystage1.css");
        
        window.setScene(scene);
    }
    
    public void onCancelButtonClick() {
        close();
    }
}