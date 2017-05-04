package com.mycompany.devman.controllers.employeePanel;

import com.mycompany.devman.repositories.LeaveRepository;
import com.mycompany.devman.repositories.ProjectRepository;
import com.mycompany.devman.repositories.TaskRepository;
import com.mycompany.devman.repositories.TeamRepository;
import com.mycompany.devman.repositories.UserRepository;
import com.mycompany.devman.domain.Leave;
import com.mycompany.devman.domain.Project;
import com.mycompany.devman.domain.Task;
import com.mycompany.devman.domain.Team;
import com.mycompany.devman.domain.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuBar;
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
    
    @FXML
    private ChoiceBox<Object> projectBox;
    
    @FXML
    private ChoiceBox teamBox;
    
    @FXML
    private ChoiceBox<Object> project2Box;
    
    @FXML
    private ChoiceBox team2Box;
    
    @FXML
    private TableView<User> usersTable;
    
    @FXML
    private TableView<Task> tasksTable;
    
    public EmployeePanelController(User user) {
        this.currentUser = user;
    }
    
    public void addNewLeaveRequest(Leave leave) {
        leaveTable.getItems().add(leave);
    }

    private void showInfoWindow() {
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
                showInfoWindow();
            }


        });
        try {
            setUpLeavesTable();
            setUpUsersTable();
            setUpTasksTable();
            setUpProjectFiltering();
            setUpTeamFiltering();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setUpTeamFiltering() throws Exception {
        teamBox.getItems().clear();
        teamBox.getItems().add("Wszystkie");
        teamBox.getItems().addAll(TeamRepository.findTeamsByUser(currentUser));
        teamBox.getSelectionModel().selectFirst();


        team2Box.setItems(teamBox.getItems());
        team2Box.setSelectionModel(teamBox.getSelectionModel());
    }

    private void setUpProjectFiltering() throws Exception {
        projectBox.getItems().add("Wszystkie");
        projectBox.getItems().addAll(ProjectRepository.findProjectsByUser(currentUser));
        projectBox.getSelectionModel().selectFirst();

        projectBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                Object o = projectBox.getItems().get((Integer)t1);
                if(o instanceof String && o.equals("Wszystkie")) {
                    teamBox.getItems().clear();
                    teamBox.getItems().add("Wszystkie");
                    try {
                        teamBox.getItems().addAll(TeamRepository.findTeamsByUser(currentUser));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    teamBox.getSelectionModel().selectFirst();
                }
                else if(o instanceof Project) {
                    teamBox.getItems().clear();
                    teamBox.getItems().add("Wszystkie");
                    try {
                        teamBox.getItems().addAll(TeamRepository.findTeamsByProjectAndUser((Project)o, currentUser));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    teamBox.getSelectionModel().selectFirst();
                }
            }
        });
        project2Box.setItems(projectBox.getItems());
        project2Box.setSelectionModel(projectBox.getSelectionModel());
    }

    private void setUpTasksTable() throws Exception {
        TableColumn nameColumn = new TableColumn("Nazwa");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn startDate = new TableColumn("Data rozpoczęcia");
        startDate.setMinWidth(150);
        startDate.setCellValueFactory(
                new PropertyValueFactory<>("startDate"));

        TableColumn endDate = new TableColumn("Data zakończenia");
        endDate.setMinWidth(200);
        endDate.setCellValueFactory(
                new PropertyValueFactory<>("endDate"));

        TableColumn predictedTime = new TableColumn("Przewidywany czas");
        predictedTime.setMinWidth(200);
        predictedTime.setCellValueFactory(
                new PropertyValueFactory<>("predictedTime"));

        tasksTable.getItems().addAll(TaskRepository.findTasksByUser(currentUser));
        tasksTable.getColumns().clear();
        tasksTable.getColumns().addAll(nameColumn, startDate, endDate, predictedTime);
    }

    private void setUpUsersTable() throws Exception {
        TableColumn name = new TableColumn("Imię");
        name.setMinWidth(150);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn lastName = new TableColumn("Nazwisko");
        lastName.setMinWidth(150);
        lastName.setCellValueFactory(
                new PropertyValueFactory<>("lastName"));

        TableColumn email = new TableColumn("e-mail");
        email.setMinWidth(200);
        email.setCellValueFactory(
                new PropertyValueFactory<>("email"));

        usersTable.getItems().addAll(UserRepository.findAnotherUsersInTeam(currentUser));
        usersTable.getColumns().clear();
        usersTable.getColumns().addAll(name, lastName, email);
    }

    private void setUpLeavesTable() throws Exception {
        TableColumn status = new TableColumn("Status");
        status.setMinWidth(150);
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn startDate = new TableColumn("Data rozpoczęcia");
        startDate.setMinWidth(150);
        startDate.setCellValueFactory(
                new PropertyValueFactory<>("startDate"));

        TableColumn numberOfDays = new TableColumn("ilość dni");
        numberOfDays.setMinWidth(200);
        numberOfDays.setCellValueFactory(
                new PropertyValueFactory<>("numberOfDays"));

        leaveTable.getItems().addAll(LeaveRepository.findLeavesByUser(currentUser));
        leaveTable.getColumns().clear();
        leaveTable.getColumns().addAll(status, startDate, numberOfDays);
    }

    private void close() {
        Stage window = (Stage) menuBar.getScene().getWindow();
        window.close();
    }
    
    public void onTeamFilterClick() throws Exception {
        Object o = teamBox.getSelectionModel().getSelectedItem();
        if(o instanceof Team) {
            usersTable.getItems().clear();
            usersTable.getItems().addAll(UserRepository.findUsersByTeam((Team)o));
        }
        else if(o instanceof String && o.equals("Wszystkie")) {
            Object p = projectBox.getSelectionModel().getSelectedItem();
            if(p instanceof Project) {
                usersTable.getItems().clear();
                usersTable.getItems().addAll(UserRepository.findUsersByProject((Project)p));
            }
            else if(p instanceof String && p.equals("Wszystkie")) {
                usersTable.getItems().clear();
                usersTable.getItems().addAll(UserRepository.findAnotherUsersInTeam(currentUser));
            }
        }
    }
    
    public void onTaskFilterButtonClick() throws Exception {
        Object o = teamBox.getSelectionModel().getSelectedItem();
        if(o instanceof Team) {
            tasksTable.getItems().clear();
            tasksTable.getItems().addAll(TaskRepository.findTasksByTeam((Team)o));
        }
        else if(o instanceof String && o.equals("Wszystkie")) {
            Object p = projectBox.getSelectionModel().getSelectedItem();
            if(p instanceof Project) {
                tasksTable.getItems().clear();
                tasksTable.getItems().addAll(TaskRepository.findTasksByProject((Project)p));
            }
            else if(p instanceof String && p.equals("Wszystkie")) {
                tasksTable.getItems().clear();
                tasksTable.getItems().addAll(TaskRepository.findTasksByUser(currentUser));
            }
        }
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
