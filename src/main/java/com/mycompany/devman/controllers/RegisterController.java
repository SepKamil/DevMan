package com.mycompany.devman.controllers;

import com.mycompany.devman.UserRepository;
import com.mycompany.devman.domain.AccountType;
import com.mycompany.devman.domain.User;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kuba3
 */
public class RegisterController implements Initializable {

    @FXML
    Button registerButton;
    
    @FXML
    TextField name;
    
    @FXML
    TextField lastName;
    
    @FXML
    TextField email;
    
    @FXML
    TextField login;
    
    @FXML
    TextField password;
    
    @FXML
    TextField repeatPassword;
    
    @FXML
    ChoiceBox manager;
    
    @FXML
    TextField pesel;

    private void close() {
        Stage window = (Stage) registerButton.getScene().getWindow();
        window.close();
    }

    /**
     * Concludes developer registation and closes the password window after the
     * user confirms reading the message
     */
    public void OnRegisterButtonClick() {
        User user = new User();
        user.setLogin(login.getText());
        user.setPassword(password.getText());
        user.setName(name.getText());
        user.setLastName(lastName.getText());
        user.setEmail(email.getText());
        user.setManager((User)manager.getSelectionModel().getSelectedItem());
        user.setPesel(pesel.getText());
        try {
            UserRepository.addUserToDatabase(user);
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText("Rejestracja nieudana");
        alert.setContentText("Sprawdź prawidłowość danych wpisanych do formularza.");
        alert.showAndWait();
        return;
        }
        
        
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
        if (UserRepository.findManagers().isEmpty()) {
            User user = new User();
            user.setLogin("test");
            user.setPassword("test");
            user.setName("test");
            user.setLastName("test");
            user.setPesel("123456789");
            user.setAccountType(AccountType.MANAGER);
            try {
                UserRepository.addUserToDatabase(user);
            } catch (Exception ex) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        UserRepository.findManagers().forEach(manager.getItems()::add);
        
        
    }

}
