package com.mycompany.devman.controllers.managerPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.devman.TaskRepository;
import com.mycompany.devman.UserRepository;
import com.mycompany.devman.domain.Task;
import com.mycompany.devman.domain.Team;
import com.mycompany.devman.domain.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jakub
 */
public class TaskAssignController implements Initializable {

    @FXML
    private Button cancel;
    
    @FXML
    private ListView allTasks;
    
    @FXML
    private ListView selectedTasks;
    
    private Team team;
    
    public TaskAssignController(Team team) {
        this.team = team;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Task> tasks = TaskRepository.findAllTasks();
        tasks.forEach(task -> {
            if(team.equals(task.getTeam()))
                selectedTasks.getItems().add(task);
            else
                allTasks.getItems().add(task);
        });
        
        allTasks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        selectedTasks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    } 
    
    private void close() {
        Stage window = (Stage) cancel.getScene().getWindow();
        window.close();
    }
    
    public void onOkButtonClick() {
        for(Object o : allTasks.getItems()) {
            Task task = (Task)o;
            if(team.equals(task.getTeam())) {
                task.setTeam(null);
                try {
                    TaskRepository.updateTask(task);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        for(Object o : selectedTasks.getItems()) {
            Task task = (Task)o;
            if(!team.equals(task.getTeam())) {
                task.setTeam(team);
                try {
                    TaskRepository.updateTask(task);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        close();
    }
    
    public void onCancelButtonClick() {
        close();
    }
    
    public void onRightButtonClick() {
        List<Task> selected = new ArrayList<>(allTasks.getSelectionModel().getSelectedItems());
        selected.forEach(selectedTasks.getItems()::add);
        selected.forEach(allTasks.getItems()::remove);
    }
    
    public void onLeftButtonClick() {
        List<Task> selected = new ArrayList<>(selectedTasks.getSelectionModel().getSelectedItems());
        selected.forEach(allTasks.getItems()::add);
        selected.forEach(selectedTasks.getItems()::remove);
    }
    
    public void onAllRightButtonClick() {
        allTasks.getItems().forEach(selectedTasks.getItems()::add);
        allTasks.getItems().clear();
    }
    
    public void onAllLeftButtonClick() {
        selectedTasks.getItems().forEach(allTasks.getItems()::add);
        selectedTasks.getItems().clear();
    }
}
