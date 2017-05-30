package com.mycompany.devman.repositories;

import com.mycompany.devman.BackendSetup;
import com.mycompany.devman.domain.*;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by bloczek on 30.05.2017.
 */
public class TaskRepositoryTest {
    @Before
    public void setup() {
        SessionFactory sessionFactory = new Configuration().addAnnotatedClass(User.class)
                .addAnnotatedClass(Leave.class)
                .addAnnotatedClass(Message.class)
                .addAnnotatedClass(Project.class)
                .addAnnotatedClass(Task.class)
                .addAnnotatedClass(Team.class)
                .addAnnotatedClass(WorkTime.class)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                .setProperty("hibernate.connection.driver_class", "org.h2.Driver")
                .setProperty("hibernate.connection.url", "jdbc:h2:mem:")
                .setProperty("hibernate.hbm2ddl.auto", "update")
                .buildSessionFactory();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        BackendSetup.setSessionFactory(sessionFactory);
        BackendSetup.setValidatorFactory(validatorFactory);
    }

    @Test (expected = Exception.class)
    public void addTask() throws Exception {
        org.hibernate.Session session = BackendSetup.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        Task task = new Task();
        task.setId(45L);
        task.setName("zadanie");
        session.save(task);
        transaction.commit();


    }

    @Test (expected = Exception.class)
    public void updateTask() throws Exception {
        org.hibernate.Session session = BackendSetup.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        Task task = new Task();
        TaskRepository.updateTask(task);
    }

    @Test (expected = Exception.class)
    public void findById() throws Exception {
        org.hibernate.Session session = BackendSetup.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        TaskRepository.findById(45L);

    }

    
    @Test (expected = Exception.class)
    public void findAllTasks() throws Exception {
        org.hibernate.Session session = BackendSetup.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        findAllTasks();

    }

    @Test (expected = Exception.class)
    public void findTasksByTeam() throws Exception {
        org.hibernate.Session session = BackendSetup.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        findCompletedTasksByTeam();

    }

    @Test (expected = Exception.class)
    public void findTasksByProject() throws Exception {
        org.hibernate.Session session = BackendSetup.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        findTasksByProject();
    }

    @Test (expected = Exception.class)
    public void findTasksInProgress() throws Exception {
        org.hibernate.Session session = BackendSetup.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        findTasksInProgress();
    }

    @Test(expected = Exception.class)
    public void findCompletedTasks() throws Exception {
        org.hibernate.Session session = BackendSetup.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        findCompletedTasks();
    }

    @Test (expected = Exception.class)
    public void findTasksInProgressByUser() throws Exception {
        org.hibernate.Session session = BackendSetup.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        findTasksInProgressByUser();
    }

    @Test (expected = Exception.class)
    public void findCompletedTasksByUser() throws Exception {
        org.hibernate.Session session = BackendSetup.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        TaskRepository.findCompletedTasksByUser(new User());
    }

    @Test (expected = Exception.class)
    public void findCompletedTasksByTeam() throws Exception {
        org.hibernate.Session session = BackendSetup.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        Team team = new Team ();
        TaskRepository.findCompletedTasksByTeam(team);


    }

    @Test (expected = Exception.class)
    public void findCompletedTasksByProject() throws Exception {
        org.hibernate.Session session = BackendSetup.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        findTasksByProject();

    }

}