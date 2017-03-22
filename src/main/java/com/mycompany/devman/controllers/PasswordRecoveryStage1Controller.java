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
    
    /**
     *  Creates a window used for 2nd stage of password recovery.
     *  IO Exception may occur if files /fxml/PasswordRecoveryStage2.fxml 
     *  or /styles/passwordrecoverystage2.css are missing
     */
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
    
    /**
     * Closes the Password recovery window
     */
    public void onCancelButtonClick() {
        close();
    }
}
