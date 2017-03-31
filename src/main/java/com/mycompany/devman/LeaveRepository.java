/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.devman;

import com.mycompany.devman.domain.AccountType;
import com.mycompany.devman.domain.Leave;
import com.mycompany.devman.domain.LeaveRequestType;
import com.mycompany.devman.domain.User;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author jakub
 */
public class LeaveRepository {
    public static Leave addNewLeaveRequest(Leave leave) throws Exception {
        Session session = MainApp.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        leave.setType(LeaveRequestType.OCZEKUJE);
        Validator validator = MainApp.getEntityValidator();
        Set<ConstraintViolation<Leave>> leaves = validator.validate(leave);
        String message = "";
        if (leaves.size() > 0) {
            Iterator iterator = leaves.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<User> userError = (ConstraintViolation<User>) iterator.next();
                message += " " + userError.getMessage();
            }
            throw new Exception(message);
        }
        if(!leave.getEmployee().getAccountType().equals(AccountType.EMPLOYEE)) {
            throw new Exception("Składający wniosek nie jest pracownikiem");
        }
        if(leave.getStartDate().isBefore(LocalDate.now())) {
            throw new Exception("Data rozpoczęcia musi byc z przyszlości.");
        }
        Long id = (Long) session.save(leave);
        leave.setId(id);
        transaction.commit();
        session.close();
        return leave;
    }
    public static List<Leave> findLeavesByUser(User user) {
        Session session = MainApp.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        List<Leave> leaves = session.createQuery("FROM Leave AS l WHERE l.employee=:user").setParameter("user", user).list();
        return leaves;
    }
}
