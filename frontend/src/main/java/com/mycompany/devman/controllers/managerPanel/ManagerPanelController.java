package com.mycompany.devman.controllers.managerPanel;

import com.mycompany.devman.repositories.ProjectRepository;
import com.mycompany.devman.repositories.TaskRepository;
import com.mycompany.devman.repositories.TeamRepository;
import com.mycompany.devman.domain.Project;
import com.mycompany.devman.domain.Task;
import com.mycompany.devman.domain.Team;
import com.mycompany.devman.domain.User;
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
import javafx.scene.control.Alert;
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
public class ManagerPanelController implements Initializable {

    @FXML
    private TabPane tabPanel;

    @FXML
    private MenuBar menuBar;
    
    @FXML
    private TableView<Project> projectsTable;
    
    @FXML
    private TableView<Team> teamsTable;
    
    @FXML
    private TableView<Task> taskTable;

    //@FXML
   // private TableView<User> employeeTable;
    
    private User currentUser;

    public ManagerPanelController(User user) {
        this.currentUser = user;
    }

    private void showInfoWindow() {
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
            setUpProjectTable();
            setUpTeamTable();
            setUpTaskTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpTaskTable() throws Exception {
        TableColumn name = new TableColumn("Nazwa");
        name.setMinWidth(150);
        name.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        TableColumn startDate = new TableColumn("Data rozpoczęcia");
        startDate.setMinWidth(150);
        startDate.setCellValueFactory(
                new PropertyValueFactory<>("startDate"));

        TableColumn endDate = new TableColumn("Data zakończenia");
        endDate.setMinWidth(150);
        endDate.setCellValueFactory(
                new PropertyValueFactory<>("endDate"));

        TableColumn predictedTime = new TableColumn("Przewidywany czas");
        predictedTime.setMinWidth(150);
        predictedTime.setCellValueFactory(
                new PropertyValueFactory<>("predictedTime"));

        TableColumn taskState = new TableColumn("Status");
        taskState.setMinWidth(150);
        taskState.setCellValueFactory(
                new PropertyValueFactory<>("taskState"));

        taskTable.getItems().addAll(TaskRepository.findAllTasks());
        taskTable.getColumns().clear();
        taskTable.getColumns().addAll(name, startDate, endDate, predictedTime, taskState);
    }

    private void setUpTeamTable() throws Exception {
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

    private void setUpProjectTable() throws Exception {
        TableColumn idCol = new TableColumn("ID");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn nameCol = new TableColumn("Nazwa");
        nameCol.setMinWidth(150);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        projectsTable.getItems().addAll(ProjectRepository.findAll());
        projectsTable.getColumns().clear();
        projectsTable.getColumns().addAll(idCol, nameCol);
    }

    public void addTask(Task task) {
        taskTable.getItems().add(task);
    }
    
    public void addTeam(Team team) {
        teamsTable.getItems().add(team);
    }
    
    public void addProject(Project project) {
        projectsTable.getItems().add(project);
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
        showEditEmployeeWindow();
    }

    private void showEditEmployeeWindow() throws IOException {
        showAddOrEditEmployeeWindow();
    }

    private void showAddOrEditEmployeeWindow() throws IOException {
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
        showAddOrEditEmployeeWindow();
    }
    
    public void onAddTeamClick() throws IOException {
        showAddOrEditTeamWindow();
    }

    private void showAddOrEditTeamWindow() throws IOException {
        Stage teamAddWindow = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/managerPanel/AddOrEditTeam.fxml"));
        AddOrEditTeamController controller = new AddOrEditTeamController(this);
        loader.setController(controller);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");

        teamAddWindow.setTitle("DevMan - Zespół");
        teamAddWindow.setResizable(false);
        teamAddWindow.setScene(scene);
        teamAddWindow.setX(20);
        teamAddWindow.setY(20);
        teamAddWindow.show();
    }

    public void onEditTeamClick() throws IOException {
        showAddOrEditTeamWindow();
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
        if (checkIfTeamSelected()) return;
        Stage taskAssignWindow = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/managerPanel/TaskAssign.fxml"));

        TaskAssignController controller = new TaskAssignController((Team)teamsTable.getSelectionModel().getSelectedItem());
        loader.setController(controller);
        Parent root = loader.load();
        
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
        if (!checkIfTeamSelected()) return;
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

    /*public void onDeleteEmployeeButtonClick() throws Exception {
        for(User employee : employeeTable.getSelectionModel().getSelectedItems()) {
            TaskRepository.deleteById(employee.getId());
            employeeTable.getItems().remove(employee);
        }
    }*/

    private boolean checkIfTeamSelected() {
        if(teamsTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Błąd!");
            alert.setContentText("Nie wybrano zespołu!");
            alert.showAndWait();
            return false;
        }
        return true;
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

    public void onDeleteTeamButtonClick() throws Exception {
        for(Team team : teamsTable.getSelectionModel().getSelectedItems()) {
            TaskRepository.deleteById(team.getId());
            teamsTable.getItems().remove(team);
        }
    }
    
    public void onNewProjectButtonClick() throws IOException {
        showAddOrEditProjectWindow();
    }

    private void showAddOrEditProjectWindow() throws IOException {
        Stage newProject = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/managerPanel/AddOrEditProject.fxml"));

        AddOrEditProjectController controller = new AddOrEditProjectController(this);
        loader.setController(controller);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");

        newProject.setTitle("DevMan - projekt");
        newProject.setResizable(false);
        newProject.setScene(scene);
        newProject.setX(20);
        newProject.setY(20);
        newProject.show();
    }

    public void onEditProjectButtonClick() throws IOException {
        showAddOrEditProjectWindow();
    }
    
    public void onNewTaskButtonClick() throws IOException {
        showAddOrEditTaskWindow();
    }

    private void showAddOrEditTaskWindow() throws IOException {
        Stage newTask = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/managerPanel/NewOrEditTask.fxml"));
        NewOrEditTaskController controller = new NewOrEditTaskController(this);
        loader.setController(controller);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/register.css");

        newTask.setTitle("DevMan - zadanie");
        newTask.setResizable(false);
        newTask.setScene(scene);
        newTask.setX(20);
        newTask.setY(20);
        newTask.show();
    }

    public void onEditTaskButtonClick() throws IOException {
        showAddOrEditTaskWindow();
    }
    public void onDeleteTaskButtonClick() throws Exception {
        for(Task task : taskTable.getSelectionModel().getSelectedItems()) {
            TaskRepository.deleteById(task.getId());
            taskTable.getItems().remove(task);
        }
    }
}
