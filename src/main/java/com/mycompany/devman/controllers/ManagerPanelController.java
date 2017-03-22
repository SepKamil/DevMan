package com.mycompany.devman.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javax.swing.event.HyperlinkEvent;

/**
 * FXML Controller class
 *
 * @author kuba3
 */
public class ManagerPanelController implements Initializable {

    @FXML
    TabPane tabPanel;

    @FXML
    MenuBar menuBar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuBar.getMenus().filtered(menu -> menu.getText().equals("Plik")).get(0).getItems().filtered(item -> item.getText().equals("Zamknij")).get(0).addEventHandler(EventType.ROOT, new EventHandler() {
            @Override
            public void handle(Event t) {
                close();
            }
        });
        menuBar.getMenus().filtered(menu -> menu.getText().equals("Pomoc")).get(0).getItems().filtered(item -> item.getText().equals("Informacje")).get(0).addEventHandler(EventType.ROOT, new EventHandler() {
            @Override
            public void handle(Event t) {
                Stage infoWindow = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/fxml/Info.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/register.css");

                infoWindow.setTitle("DevMan - Informacje");
                infoWindow.setResizable(false);
                infoWindow.setScene(scene);
                infoWindow.setX(20);
                infoWindow.setY(20);
                infoWindow.show();
            }
        });
    }

    private void close() {
        Stage window = (Stage) tabPanel.getScene().getWindow();
        window.close();
    }

    public void onTasksInProgressClick() {
        tabPanel.getSelectionModel().select(tabPanel.getTabs().filtered(tab -> tab.getText().equals("Zadania")).get(0));
    }

    public void onProjectsInProgressClick() {
        tabPanel.getSelectionModel().select(tabPanel.getTabs().filtered(tab -> tab.getText().equals("Projekty")).get(0));
    }

    public void onEmployeeVerifyClick() throws IOException {
        Stage employeeVerifyWindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/EmployeeVerify.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");

        employeeVerifyWindow.setTitle("DevMan - Weryfikacja pracowników");
        employeeVerifyWindow.setResizable(false);
        employeeVerifyWindow.setScene(scene);
        employeeVerifyWindow.setX(20);
        employeeVerifyWindow.setY(20);
        employeeVerifyWindow.show();
    }

    public void onLeaveVerifyClick() throws IOException {
        Stage leaveRequestVerifyWindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LeaveRequest.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");

        leaveRequestVerifyWindow.setTitle("DevMan - Weryfikacja wniosków o urlop");
        leaveRequestVerifyWindow.setResizable(false);
        leaveRequestVerifyWindow.setScene(scene);
        leaveRequestVerifyWindow.setX(20);
        leaveRequestVerifyWindow.setY(20);
        leaveRequestVerifyWindow.show();
    }
    
    public void onAddEmployeeClick() throws IOException {
        Stage employeeAddWindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddOrEditEmployee.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");

        employeeAddWindow.setTitle("DevMan - Weryfikacja pracowników");
        employeeAddWindow.setResizable(false);
        employeeAddWindow.setScene(scene);
        employeeAddWindow.setX(20);
        employeeAddWindow.setY(20);
        employeeAddWindow.show();
    }
    
    public void onEditEmployeeClick() throws IOException {
        Stage employeeEditWindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddOrEditEmployee.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");

        employeeEditWindow.setTitle("DevMan - Weryfikacja pracowników");
        employeeEditWindow.setResizable(false);
        employeeEditWindow.setScene(scene);
        employeeEditWindow.setX(20);
        employeeEditWindow.setY(20);
        employeeEditWindow.show();
    }
    
    public void onAddTeamClick() throws IOException {
        Stage teamAddWindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddOrEditTeam.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");

        teamAddWindow.setTitle("DevMan - Dodawanie nowego zespołu");
        teamAddWindow.setResizable(false);
        teamAddWindow.setScene(scene);
        teamAddWindow.setX(20);
        teamAddWindow.setY(20);
        teamAddWindow.show();
    }
    
    public void onEditTeamClick() throws IOException {
        Stage teamEditWindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddOrEditTeam.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");

        teamEditWindow.setTitle("DevMan - Edycja zespołu");
        teamEditWindow.setResizable(false);
        teamEditWindow.setScene(scene);
        teamEditWindow.setX(20);
        teamEditWindow.setY(20);
        teamEditWindow.show();
    }
    
    public void onErrorsButtonClick() throws IOException {
        Stage errorsWindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/TimeErrors.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");

        errorsWindow.setTitle("DevMan - Błędy");
        errorsWindow.setResizable(false);
        errorsWindow.setScene(scene);
        errorsWindow.setX(20);
        errorsWindow.setY(20);
        errorsWindow.show();
    }
    
    public void onTaskAssignButtonClick() throws IOException {
        Stage taskAssignWindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/TaskAssign.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");

        taskAssignWindow.setTitle("DevMan - Przydział zadań");
        taskAssignWindow.setResizable(false);
        taskAssignWindow.setScene(scene);
        taskAssignWindow.setX(20);
        taskAssignWindow.setY(20);
        taskAssignWindow.show();
    }
    
    public void onEmployeeAssignClick() throws IOException {
        Stage employeeAssign = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/EmployeeAssign.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");

        employeeAssign.setTitle("DevMan - Przydział pracowników");
        employeeAssign.setResizable(false);
        employeeAssign.setScene(scene);
        employeeAssign.setX(20);
        employeeAssign.setY(20);
        employeeAssign.show();
    }
    
    public void onTeamsAssignClick() throws IOException {
        Stage teamsAssign = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/TeamsAssign.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");

        teamsAssign.setTitle("DevMan - Przydział drużyn");
        teamsAssign.setResizable(false);
        teamsAssign.setScene(scene);
        teamsAssign.setX(20);
        teamsAssign.setY(20);
        teamsAssign.show();
    }
    
    public void onNewProjectButtonClick() throws IOException {
        Stage newProject = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddOrEditProject.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");

        newProject.setTitle("DevMan - Dodaj projekt");
        newProject.setResizable(false);
        newProject.setScene(scene);
        newProject.setX(20);
        newProject.setY(20);
        newProject.show();
    }
    
    public void onEditProjectButtonClick() throws IOException {
        Stage editProject = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddOrEditProject.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");

        editProject.setTitle("DevMan - Edytuj projekt");
        editProject.setResizable(false);
        editProject.setScene(scene);
        editProject.setX(20);
        editProject.setY(20);
        editProject.show();
    }
    
    public void onNewTaskButtonClick() throws IOException {
        Stage newTask = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/NewOrEditTask.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");

        newTask.setTitle("DevMan - Nowe zadanie");
        newTask.setResizable(false);
        newTask.setScene(scene);
        newTask.setX(20);
        newTask.setY(20);
        newTask.show();
    }
    
    public void onEditTaskButtonClick() throws IOException {
        Stage editTask = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/NewOrEditTask.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");

        editTask.setTitle("DevMan - Edytuj zadanie");
        editTask.setResizable(false);
        editTask.setScene(scene);
        editTask.setX(20);
        editTask.setY(20);
        editTask.show();
    }
}
