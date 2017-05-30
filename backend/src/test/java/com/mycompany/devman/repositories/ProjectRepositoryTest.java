package com.mycompany.devman.repositories;

import com.mycompany.devman.BackendSetup;
import com.mycompany.devman.domain.*;
import org.h2.engine.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.criteria.internal.expression.ParameterExpressionImpl;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by bloczek on 17.05.2017.
 */
public class ProjectRepositoryTest {
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
    public void addProject() throws Exception {
        org.hibernate.Session session = BackendSetup.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        Project project = new Project();
        project.setName("Jan");
        project.setId(789L);
        project.setProjectState(Project.ProjectState.FINISHED);
        session.save(project);
        transaction.commit();
    }

    @Test (expected = Exception.class)
    public void updateProject() throws Exception {
        org.hibernate.Session session = BackendSetup.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        Project project = new Project();
        project.setName("Jan");
        project.setId(789L);
        project.setProjectState(Project.ProjectState.FINISHED);
        session.update(project);
        transaction.commit();
    }

    @Test (expected = Exception.class)
    public void findById() throws Exception {
        org.hibernate.Session session = BackendSetup.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        ProjectRepository.findById(52L);



    }

    @Test (expected = Exception.class)
    public void deleteById() throws Exception {
        org.hibernate.Session session = BackendSetup.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        ProjectRepository.deleteById(34L);
    }

    @Test (expected = Exception.class)
    public void findByName() throws Exception {
        org.hibernate.Session session = BackendSetup.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        ProjectRepository.findByName("Jan Kowalski");
    }

    @Test (expected = Exception.class)
    public void findProjectsInProgress() throws Exception {
        ProjectRepository.findProjectsInProgress();
    }

    @Test (expected = Exception.class)
    public void findProjectsByUser() throws Exception {
        User user = new User();
        ProjectRepository.findProjectsInProgressByUser(user);
    }

    @Test (expected = Exception.class)
    public void findAllProjects() throws Exception {
        org.hibernate.Session session = BackendSetup.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        findAllProjects();
    }

}