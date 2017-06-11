package com.mycompany.devman.controllers.managerPanel;


import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import com.mycompany.devman.domain.User;
import com.mycompany.devman.repositories.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author jakub
 */
public class EmployeeVerifyController extends Observable implements Initializable {

    @FXML
    TableView<User> employeeTable;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setUpEmployeeTable();
    }

    private void setUpEmployeeTable(){
        employeeTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        TableColumn name = new TableColumn("Imię");
        name.setMinWidth(150);
        name.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        TableColumn lastName = new TableColumn("Nazwisko");
        lastName.setMinWidth(150);
        lastName.setCellValueFactory(
                new PropertyValueFactory<>("lastName"));

        TableColumn email = new TableColumn("E-mail");
        email.setMinWidth(150);
        email.setCellValueFactory(
                new PropertyValueFactory<>("email"));


        employeeTable.getItems().addAll(UserRepository.findInactiveUsers());
        employeeTable.getColumns().clear();
        employeeTable.getColumns().addAll(name, lastName, email);
    }

    /**
     * Deletes a newly registered user from the database
     * @throws Exception if database cannot be updated
     */
    public void onDeleteButtonClick() throws Exception {
        for(User user : employeeTable.getSelectionModel().getSelectedItems()) {
            UserRepository.deleteUser(user);
        }
        employeeTable.getItems().removeAll(employeeTable.getSelectionModel().getSelectedItems());
        setChanged();
        notifyObservers();
    }

    /**
     * Approves a newly registered user, allowing them to log into the application
     * @throws Exception if database cannot be updated
     */
    public void onAcceptButtonClick() throws Exception {
        for(User user : employeeTable.getSelectionModel().getSelectedItems()) {
            user.setUserState(User.userState.ACTIVE);
            UserRepository.updateUser(user);
        }
        employeeTable.getItems().removeAll(employeeTable.getSelectionModel().getSelectedItems());
        setChanged();
        notifyObservers();
    }
}
