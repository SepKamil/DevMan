package com.mycompany.devman.controllers.managerPanel;

import com.mycompany.devman.LeaveRepository;
import com.mycompany.devman.ProjectRepository;
import com.mycompany.devman.TeamRepository;
import com.mycompany.devman.domain.Team;
import com.mycompany.devman.domain.User;
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
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.event.HyperlinkEvent;

/**
 * FXML Controller class
 *
 * @author kuba3
 */
public class ManagerPanelController implements Initializable {

    @FXML
    private TabPane tabPanel;

    @FXML
    private MenuBar menuBar;
    
    @FXML
    private TableView projectsTable;
    
    @FXML
    private TableView teamsTable;
    
    private User currentUser;

    public ManagerPanelController(User user) {
        this.currentUser = user;
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
        TableColumn firstNameCol = new TableColumn("ID");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("id"));
 
        TableColumn lastNameCol = new TableColumn("Nazwa");
        lastNameCol.setMinWidth(150);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));
 
        projectsTable.getItems().addAll(ProjectRepository.findAll());
        projectsTable.getColumns().clear();
        projectsTable.getColumns().addAll(firstNameCol, lastNameCol);
        
        TableColumn idCol = new TableColumn("ID");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
 
        TableColumn nameCol = new TableColumn("Nazwa");
        nameCol.setMinWidth(150);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));
 
        TableColumn projectCol = new TableColumn("Projekt");
        projectCol.setMinWidth(100);
        projectCol.setCellValueFactory(new PropertyValueFactory<>("project"));
        
        teamsTable.getItems().addAll(TeamRepository.findAllTeams());
        teamsTable.getColumns().clear();
        teamsTable.getColumns().addAll(idCol, nameCol, projectCol);
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
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/managerPanel/EmployeeVerify.fxml"));

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/managerPanel/LeaveRequest.fxml"));
        LeaveRequestController controller = new LeaveRequestController(currentUser);
        loader.setController(controller);
        Parent root = (Parent)loader.load();

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
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/managerPanel/AddOrEditEmployee.fxml"));

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
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/managerPanel/AddOrEditEmployee.fxml"));

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
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/managerPanel/AddOrEditTeam.fxml"));

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
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/managerPanel/AddOrEditTeam.fxml"));

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
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/managerPanel/TimeErrors.fxml"));

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
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/managerPanel/TaskAssign.fxml"));

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
        if(teamsTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Błąd!");
            alert.setContentText("Nie wybrano zespołu!");
            alert.showAndWait();
            return;
        }
        Stage employeeAssign = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/managerPanel/EmployeeAssign.fxml"));
        
        EmployeeAssignController controller = new EmployeeAssignController((Team)teamsTable.getSelectionModel().getSelectedItem());
        loader.setController(controller);
        Parent root = loader.load();
        
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
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/managerPanel/TeamsAssign.fxml"));

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
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/managerPanel/AddOrEditProject.fxml"));

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
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/managerPanel/AddOrEditProject.fxml"));

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
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/managerPanel/NewOrEditTask.fxml"));

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
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/managerPanel/NewOrEditTask.fxml"));

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
