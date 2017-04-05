/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.devman;

import com.mycompany.devman.domain.AccountType;
import com.mycompany.devman.domain.Project;
import com.mycompany.devman.domain.Team;
import com.mycompany.devman.domain.User;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author jakub
 */
public class UserRepository {

    public static User addUserToDatabase(User user) throws Exception {
        Session session = MainApp.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        Validator validator = MainApp.getEntityValidator();
        Set<ConstraintViolation<User>> users = validator.validate(user);
        String message = "";
        if (users.size() > 0) {
            Iterator iterator = users.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<User> userError = (ConstraintViolation<User>) iterator.next();
                message += " " + userError.getMessage();
            }
            throw new Exception(message);
        }
        Long id = (Long) session.save(user);
        user.setId(id);
        transaction.commit();
        session.close();
        return user;
    }

    public static User findByLoginAndPassword(String login, String password) throws Exception {
        List users = null;
        try {
            Session session = MainApp.getDatabaseSession();
            Transaction transaction = session.beginTransaction();
            users = session.createQuery("FROM User u WHERE u.login=:login AND u.password=:password")
                    .setParameter("login", login).setParameter("password", password).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (users.size() == 1) {
            return (User) users.get(0);
        }

        throw new Exception("Zły login lub hasło!");
    }

    public static void deleteById(Long id) throws Exception {

        try {
            Session session = MainApp.getDatabaseSession();
            Transaction transaction = session.beginTransaction();
          Query query = session.createQuery("Delete from User where id=:id").setParameter("id",id);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public static List findManagers() {
        Session session = MainApp.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        List users = session.createQuery("FROM User u WHERE u.accountType='MANAGER'").list();
        transaction.commit();
        session.close();
        return users;
    }

    public static List<User> findEmployees() {
        Session session = MainApp.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User u WHERE u.accountType='EMPLOYEE'").list();
        transaction.commit();
        session.close();
        return users;
    }
    
    public static User UpdateUser(User user) throws Exception {
        Session session = MainApp.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        Validator validator = MainApp.getEntityValidator();
        Set<ConstraintViolation<User>> users = validator.validate(user);
        String message = "";
        if (users.size() > 0) {
            Iterator iterator = users.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<User> userError = (ConstraintViolation<User>) iterator.next();
                message += " " + userError.getMessage();
            }
            throw new Exception(message);
        }
        session.update(user);
        transaction.commit();
        session.close();
        return user;
    }
    
    public static List<User> findUsersByTeam(Team team) {
        Session session = MainApp.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("SELECT u FROM User u JOIN u.teams t WHERE t=:team").setParameter("team", team).list();
        transaction.commit();
        session.close();
        return users;
    }
    
    public static List<User> findUsersByProject(Project project) {
        Session session = MainApp.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("SELECT u FROM User u JOIN u.teams t WHERE t.project=:project").setParameter("project", project).list();
        transaction.commit();
        session.close();
        return users;
    }
    
    public static List<User> findAnotherUsersInTeams(User user) {
        Session session = MainApp.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("SELECT u FROM User u JOIN u.teams t WHERE t in (SELECT t FROM User u JOIN u.teams t WHERE u=:user)").setParameter("user", user).list();
        transaction.commit();
        session.close();
        return users;
    }
}
