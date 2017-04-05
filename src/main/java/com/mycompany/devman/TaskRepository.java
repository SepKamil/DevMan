package com.mycompany.devman;

import com.mycompany.devman.MainApp;
import com.mycompany.devman.domain.Project;
import com.mycompany.devman.domain.Task;
import com.mycompany.devman.domain.Team;
import com.mycompany.devman.domain.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by bloczek on 29.03.2017.
 */
public class TaskRepository {

    public static Task addTask(Task task) throws Exception {
        Session session = MainApp.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        Validator validator = MainApp.getEntityValidator();
        Set<ConstraintViolation<Task>> tasks = validator.validate(task);
        String message = "";
        if (tasks.size() > 0) {
            Iterator iterator = tasks.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<Task> taskError = (ConstraintViolation<Task>) iterator.next();
                message += " " + taskError.getMessage();
            }
            throw new Exception(message);
        }
        if(task.getStartDate().isAfter(task.getEndDate())) {
            throw new Exception("Data rozpoczęcia nie może być po dacie zakończenia.");
        }
        session.save(task);
        transaction.commit();
        session.close();
        return task;
    }
    
    public static Task updateTask(Task task) throws Exception {
        Session session = MainApp.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        Validator validator = MainApp.getEntityValidator();
        Set<ConstraintViolation<Task>> tasks = validator.validate(task);
        String message = "";
        if (tasks.size() > 0) {
            Iterator iterator = tasks.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<Task> taskError = (ConstraintViolation<Task>) iterator.next();
                message += " " + taskError.getMessage();
            }
            throw new Exception(message);
        }
        if(task.getStartDate().isAfter(task.getEndDate())) {
            throw new Exception("Data rozpoczęcia nie może być po dacie zakończenia.");
        }
        session.update(task);
        transaction.commit();
        session.close();
        return task;
    }

    public static Task findById(Long id) throws Exception {
        List tasks = null;
        try {
            Session session = MainApp.getDatabaseSession();
            Transaction transaction = session.beginTransaction();
            tasks = session.createQuery("FROM Tasks u WHERE u.id=:id")
                    .setParameter("id", id).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (tasks.size() == 1) {
            return (Task) tasks.get(0);
        }

        throw new Exception("Nie istniejace zadanie");
    }

    public static void deleteById(Long id) throws Exception {

        try {
            Session session = MainApp.getDatabaseSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("Delete from tasks where id=:id").setParameter("id",id);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static List findByName(String name) {
        List tasks = null;
        try {
            Session session = MainApp.getDatabaseSession();
            Transaction transaction = session.beginTransaction();
            tasks = session.createQuery("FROM tasks u WHERE u.name=:name")
                    .setParameter("name", name).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public static List findByTeamId(Long id) throws Exception {
        List tasks = null;
        try {
            Session session = MainApp.getDatabaseSession();
            Transaction transaction = session.beginTransaction();
            tasks = session.createQuery("FROM Tasks u WHERE u.team_id=:id")
                    .setParameter("id", id).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
            return tasks;
    }
    
    public static List<Task> findAllTasks() {
        Session session = MainApp.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        List<Task> tasks = session.createQuery("FROM Task").list();
        transaction.commit();
        session.close();
        return tasks;
    }
    
    public static List<Task> findTasksByTeam(Team team) {
        Session session = MainApp.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        List<Task> tasks = session.createQuery("FROM Task t WHERE t.team=:team").setParameter("team", team).list();
        transaction.commit();
        session.close();
        return tasks;
    }
    
    public static List<Task> findTasksByProject(Project project) {
        Session session = MainApp.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        List<Task> tasks = session.createQuery("FROM Task t WHERE t.team.project=:project").setParameter("project", project).list();
        transaction.commit();
        session.close();
        return tasks;
    }
    
    public static List<Task> findTasksByUser(User user) {
        Session session = MainApp.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        List<Task> tasks = session.createQuery("FROM Task t WHERE t.team in (SELECT t FROM User u JOIN u.teams t WHERE u=:user)").setParameter("user", user).list();
        transaction.commit();
        session.close();
        return tasks;
    }
}