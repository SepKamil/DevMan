package com.mycompany.devman.controllers;


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
    
    /**
     *  Concludes password recovery and closes the password window after the user confirms reading the message
     */
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
    /**
     *  Returns to the first stage of password recovery in case the user made a mistake
     *  IO Exception may occur if files /fxml/PasswordRecoveryStage1.fxml 
     *  or /styles/passwordrecoverystage1.css are missing
     */
    public void onBackButtonClick() throws IOException {
        Stage window = (Stage)finishButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/PasswordRecoveryStage1.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/passwordrecoverystage1.css");
        
        window.setScene(scene);
    }
    /**
     * Closes the Password recovery window
     */
    public void onCancelButtonClick() {
        close();
    }
}