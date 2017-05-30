package com.mycompany.devman.repositories;

import com.mycompany.devman.BackendSetup;
import com.mycompany.devman.domain.Project;
import com.mycompany.devman.domain.User;
import org.h2.engine.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.query.criteria.internal.expression.ParameterExpressionImpl;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by bloczek on 17.05.2017.
 */
public class ProjectRepositoryTest {
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