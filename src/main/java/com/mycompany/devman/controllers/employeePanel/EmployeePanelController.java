package com.mycompany.devman.controllers.employeePanel;

import com.mycompany.devman.LeaveRepository;
import com.mycompany.devman.domain.Leave;
import com.mycompany.devman.domain.LeaveRequestType;
import com.mycompany.devman.domain.User;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kuba3
 */
public class EmployeePanelController implements Initializable {

    @FXML
    private MenuBar menuBar;

    @FXML
    private TabPane tabPanel;
    
    private User currentUser;
    
    @FXML
    private TableView<Leave> leaveTable;
    
    public EmployeePanelController(User user) {
        this.currentUser = user;
    }
    
    public void addNewLeaveRequest(Leave leave) {
        leaveTable.getItems().add(leave);
    }
    
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
                    root = FXMLLoader.load(getClass().getResource("/fxml/employeePanel/Info.fxml"));
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
        TableColumn firstNameCol = new TableColumn("Status");
        firstNameCol.setMinWidth(150);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Leave, LeaveRequestType>("type"));
 
        TableColumn lastNameCol = new TableColumn("Data rozpoczęcia");
        lastNameCol.setMinWidth(150);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Leave, LocalDate>("startDate"));
 
        TableColumn emailCol = new TableColumn("ilość dni");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Leave, Integer>("numberOfDays"));
 
        leaveTable.getItems().addAll(LeaveRepository.findLeavesByUser(currentUser));
        leaveTable.getColumns().clear();
        leaveTable.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
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
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/employeePanel/NewWorkTime.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");

        newWorkTimeWindow.setTitle("DevMan - Logowanie czasu pracy");
        newWorkTimeWindow.setResizable(false);
        newWorkTimeWindow.setScene(scene);
        newWorkTimeWindow.setX(20);
        newWorkTimeWindow.setY(20);
        newWorkTimeWindow.show();
    }
    
    public void onWorkTimeEditButtonClick() throws IOException {
        Stage workTimeEditWindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/employeePanel/editWorkTime.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");

        workTimeEditWindow.setTitle("DevMan - Edycja czasu pracy");
        workTimeEditWindow.setResizable(false);
        workTimeEditWindow.setScene(scene);
        workTimeEditWindow.setX(20);
        workTimeEditWindow.setY(20);
        workTimeEditWindow.show();
    }
    
    public void onNewTaskButtonClick() throws IOException {
        Stage newTaskWindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/employeePanel/TaskEditOrAdd.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");

        newTaskWindow.setTitle("DevMan - Dodawanie zadania");
        newTaskWindow.setResizable(false);
        newTaskWindow.setScene(scene);
        newTaskWindow.setX(20);
        newTaskWindow.setY(20);
        newTaskWindow.show();
    }
    
    public void onEditTaskButtonClick() throws IOException {
        Stage editTaskWindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/employeePanel/TaskEditOrAdd.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");

        editTaskWindow.setTitle("DevMan - Edycja zadania");
        editTaskWindow.setResizable(false);
        editTaskWindow.setScene(scene);
        editTaskWindow.setX(20);
        editTaskWindow.setY(20);
        editTaskWindow.show();
    }
    
    public void onAddLeaveRequestButtonClick() throws IOException {
        Stage leaveRequest = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employeePanel/AddLeaveRequest.fxml"));
        AddLeaveRequestController controller = new AddLeaveRequestController(currentUser, this);
        loader.setController(controller);
        Parent root = (Parent) loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");

        leaveRequest.setTitle("DevMan - Dodawanie wniosku o urlop");
        leaveRequest.setResizable(false);
        leaveRequest.setScene(scene);
        leaveRequest.setX(20);
        leaveRequest.setY(20);
        leaveRequest.show();
    }
    
}
