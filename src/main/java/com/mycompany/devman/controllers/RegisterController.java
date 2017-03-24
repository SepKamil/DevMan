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
        user.setManager((User) manager.getSelectionModel().getSelectedItem());
        user.setPesel(pesel.getText());
        user.setAccountType(AccountType.EMPLOYEE);
        try {
            if(!password.getText().equals(repeatPassword.getText())) {
                throw new Exception("Hasła się nie zgadzają!");
            }
            UserRepository.addUserToDatabase(user);
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Rejestracja nieudana");
            alert.setContentText(ex.getMessage());
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
            user.setLogin("admin");
            user.setPassword("devman2017");
            user.setName("Jan");
            user.setLastName("Kowalski");
            user.setPesel("11111111111");
            user.setEmail("admin@devman.pl");
            user.setAccountType(AccountType.MANAGER);
            try {
                UserRepository.addUserToDatabase(user);
            } catch (Exception ex) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        UserRepository.findManagers().forEach(manager.getItems()::add);
        
        manager.getSelectionModel().selectFirst();
    }
    
}
