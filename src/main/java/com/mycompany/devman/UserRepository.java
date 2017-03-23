/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.devman;

import com.mycompany.devman.domain.AccountType;
import com.mycompany.devman.domain.User;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author jakub
 */
public class UserRepository {

    public static void addUserToDatabase(User user) throws Exception {
            Session session = MainApp.getDatabaseSession();
            Transaction transaction = session.beginTransaction();
            /*
            Validator validator = MainApp.getEntityValidator();
            Set<ConstraintViolation<User>> users = validator.validate(user, User.class);
            if(users.size() > 0)
                throw new Exception();
            */
            session.save(user);
            transaction.commit();
            session.close();
        
    }

    public static Optional<User> findByLoginAndPassword(String login, String password) {
        List users = null;
        try {
            Session session = MainApp.getDatabaseSession();
            Transaction transaction = session.beginTransaction();
            users = session.createQuery("FROM User AS u WHERE u.login=" + login + " AND u.password=" + password).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (users.size() != 1) {
            return Optional.empty();
        } else {
            return Optional.of((User) users.get(0));
        }
    }

    public static List findManagers() {
        Session session = MainApp.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        List users = session.createQuery("FROM User AS u WHERE u.accountType='MANAGER'").list();
        transaction.commit();
        session.close();
        return users;
    }

    public static Optional<User> findByNameAndLastName(String name, String lastName) {
        List users = null;
        try {
            Session session = MainApp.getDatabaseSession();
            Transaction transaction = session.beginTransaction();
            users = session.createQuery("FROM User AS u WHERE u.name=" + name + " AND u.lastName=" + lastName).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (users.size() != 1) {
            return Optional.empty();
        } else {
            return Optional.of((User) users.get(0));
        }
    }
}
