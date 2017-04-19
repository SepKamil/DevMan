
package com.mycompany.devman.controllers.managerPanel;

import com.mycompany.devman.repositories.LeaveRepository;
import com.mycompany.devman.domain.Leave;
import com.mycompany.devman.domain.LeaveRequestStatus;
import com.mycompany.devman.domain.User;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author jakub
 */
public class LeaveRequestController implements Initializable {

    @FXML
    private TableView<Leave> leaveTable;
    
    private User currentUser;
    
    public LeaveRequestController(User user) {
        this.currentUser = user;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn employee = new TableColumn("Pracownik");
        employee.setMinWidth(150);
        employee.setCellValueFactory(new PropertyValueFactory<Leave, LeaveRequestStatus>("employee"));
 
        TableColumn startDate = new TableColumn("Data rozpoczęcia");
        startDate.setMinWidth(150);
        startDate.setCellValueFactory(
                new PropertyValueFactory<Leave, LocalDate>("startDate"));
 
        TableColumn numberOfDays = new TableColumn("ilość dni");
        numberOfDays.setMinWidth(200);
        numberOfDays.setCellValueFactory(
                new PropertyValueFactory<Leave, Integer>("numberOfDays"));
 
        leaveTable.getItems().addAll(LeaveRepository.findPendingLeavesByManager(currentUser));
        leaveTable.getColumns().clear();
        leaveTable.getColumns().addAll(employee, startDate, numberOfDays);
    }

    public void onAcceptButtonClick() {
        System.out.println("Akceptowanie:"+leaveTable.getSelectionModel().getSelectedItems().size());
        List<Leave> selected = leaveTable.getSelectionModel().getSelectedItems();
        selected.forEach(LeaveRepository::acceptLeaveRequest);
        selected.forEach(leave -> leaveTable.getItems().remove(leave));
    }
    
    public void onRejectButtonClick() {
        System.out.println("Odrzucanie:"+leaveTable.getSelectionModel().getSelectedItems().size());
        List<Leave> selected = leaveTable.getSelectionModel().getSelectedItems();
        selected.forEach(leave -> LeaveRepository.rejectLeaveRequest(leave));
        selected.forEach(leave -> leaveTable.getItems().remove(leave));
    }
}
