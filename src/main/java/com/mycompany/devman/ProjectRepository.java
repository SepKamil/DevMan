package com.mycompany.devman;

import com.mycompany.devman.domain.Project;
import com.mycompany.devman.domain.Task;
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
public class ProjectRepository {

    public static Project addProject(Project project) throws Exception {
        Session session = MainApp.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        Validator validator = MainApp.getEntityValidator();
        Set<ConstraintViolation<Project>> projects = validator.validate(project);
        String message = "";
        if (projects.size() > 0) {
            Iterator iterator = projects.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<Project> projectError = (ConstraintViolation<Project>) iterator.next();
                message += " " + projectError.getMessage();
            }
            throw new Exception(message);
        }
        if(project.getStartDate().isAfter(project.getEndDate()))
            throw new Exception("Data rozpoczęcia musi być przed datą zakończenia prjektu.");
        session.save(project);
        transaction.commit();
        session.close();
        return project;
    }

    public static Project findById(Long id) throws Exception {
        List projects = null;
        try {
            Session session = MainApp.getDatabaseSession();
            Transaction transaction = session.beginTransaction();
            projects = session.createQuery("FROM Projects AS u WHERE u.id=:id")
                    .setParameter("id", id).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (projects.size() == 1) {
            return (Project) projects.get(0);
        }

        throw new Exception("Nie istniejace zadanie");
    }

    public static void deleteById(Long id) throws Exception {

        try {
            Session session = MainApp.getDatabaseSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("Delete from projects where id=:id").setParameter("id",id);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static List findByName(String name) {
        List projects = null;
        try {
            Session session = MainApp.getDatabaseSession();
            Transaction transaction = session.beginTransaction();
            projects = session.createQuery("FROM projects AS u WHERE u.name=:name")
                    .setParameter("name", name).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projects;
    }
    
    public static List<Project> findAll() {
        Session session = MainApp.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        List<Project> projects = session.createQuery("FROM Project").list();
        transaction.commit();
        session.close();
        return projects;
    }
}
