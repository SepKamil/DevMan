package com.mycompany.devman.controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kuba3
 */
public class RegisterController implements Initializable {

    @FXML
    Button registerButton;
    
    private void close() {
        Stage window = (Stage) registerButton.getScene().getWindow();
        window.close();
    }
    /**
     *  Concludes developer registation and closes the password window after the user confirms reading the message
     */
    public void OnRegisterButtonClick() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Informacja");
        alert.setHeaderText("Rejestracja udana!");
        alert.setContentText("Twoje konto będzie aktywne, gdy zatwierdzi je menadżer.");
        alert.showAndWait().ifPresent(rs -> {
        if (rs == ButtonType.OK) {
            close();
        }
      });
    }
    
    /**
     * Closes the Password recovery window
     */
    public void onCancelButtonClick() {
        close();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
}
