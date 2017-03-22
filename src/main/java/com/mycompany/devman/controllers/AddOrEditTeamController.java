
package com.mycompany.devman.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jakub
 */
public class AddOrEditTeamController implements Initializable {

    @FXML
    Button cancel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void close() {
        Stage window = (Stage) cancel.getScene().getWindow();
        window.close();
    }
    
    public void onCancelButtonClick() {
        close();
    }
    
    public void onAddButtonClick() {
        close();
    }
}
