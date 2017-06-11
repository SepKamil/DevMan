
package com.mycompany.devman.controllers.managerPanel;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import com.mycompany.devman.domain.User;
import com.mycompany.devman.repositories.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jakub
 */
public class EditEmployeeController extends Observable implements Initializable {

    @FXML
    private Button cancel;

    @FXML
    private TextField name;

    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private Spinner<Integer> daysSpinner;

    @FXML Spinner<Integer> hoursSpinner;

    private User user;

    private ManagerPanelController controller;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name.setText(user.getName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
        SpinnerValueFactory factory = new SpinnerValueFactory() {
            @Override
            public void decrement(int i) {
                if((int)this.getValue() > 1)
                    this.setValue((int)this.getValue() - 1);
            }

            @Override
            public void increment(int i) {
                if((int) this.getValue() < 365) {
                    this.setValue((int)this.getValue() + 1);
                }
            }
        };
        SpinnerValueFactory factory2 = new SpinnerValueFactory() {
            @Override
            public void decrement(int i) {
                if((int)this.getValue() > 1)
                    this.setValue((int)this.getValue() - 1);
            }

            @Override
            public void increment(int i) {
                if((int) this.getValue() < 8) {
                    this.setValue((int)this.getValue() + 1);
                }
            }
        };
        daysSpinner.setValueFactory(factory);
        daysSpinner.getValueFactory().setValue(user.getLeaveDaysPerYear());
        hoursSpinner.setValueFactory(factory2);
        hoursSpinner.getValueFactory().setValue(user.getHoursPerDay());
    }

    public EditEmployeeController(User user, ManagerPanelController controller) {
        this.user = user;
        this.controller = controller;
    }
    
    private void close() {
        Stage window = (Stage) cancel.getScene().getWindow();
        window.close();
    }

    /**
     * Closes the window without saving changes
     */
    public void onCancelButtonClick() {
        close();
    }

    /**
     * Edits an employee and saves the changes to the database.
     * If saving fails, an Exception is thrown
     * @throws Exception if changes cannot be saved
     */
    public void onOkButtonClick() throws Exception {
        user.setName(name.getText());
        user.setLastName(lastName.getText());
        user.setEmail(email.getText());
        user.setHoursPerDay(hoursSpinner.getValue());
        user.setLeaveDaysPerYear(daysSpinner.getValue());
        UserRepository.updateUser(user);
        controller.updateEmployee(user);
        close();
    }   
}