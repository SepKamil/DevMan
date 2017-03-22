package com.mycompany.devman.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kuba3
 */
public class EmployeePanelController implements Initializable {

    @FXML
    MenuBar menuBar;

    @FXML
    TabPane tabPanel;
    
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
        Stage window = (Stage) menuBar.getScene().getWindow();
        window.close();
    }
    
    public void onTaskManagementButtonClick() {
        tabPanel.getSelectionModel().select(tabPanel.getTabs().filtered(tab -> tab.getText().equals("Zadania")).get(0));
    }
   
    
    public void onTeamInfoButtonClick() {
        tabPanel.getSelectionModel().select(tabPanel.getTabs().filtered(tab -> tab.getText().equals("Zespół")).get(0));
    }
    
    public void onWorkTimeButtonClick() {
        tabPanel.getSelectionModel().select(tabPanel.getTabs().filtered(tab -> tab.getText().equals("Czas pracy")).get(0));
    }
    
    public void onLeaveRequestsButtonClick() {
        tabPanel.getSelectionModel().select(tabPanel.getTabs().filtered(tab -> tab.getText().equals("Urlopy")).get(0));
    }
    
    public void onNewWorkTimeButtonClick() throws IOException {
        Stage newWorkTimeWindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/NewWorkTime.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");

        newWorkTimeWindow.setTitle("DevMan - Logowanie czasu pracy");
        newWorkTimeWindow.setResizable(false);
        newWorkTimeWindow.setScene(scene);
        newWorkTimeWindow.setX(20);
        newWorkTimeWindow.setY(20);
        newWorkTimeWindow.show();
    }
    
}
