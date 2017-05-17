package com.mycompany.devman.repositories;

import com.mycompany.devman.domain.Project;
import org.h2.engine.Session;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bloczek on 17.05.2017.
 */
public class ProjectRepositoryTest {
    @Test (expected = Exception class)
    public void addProject() throws Exception {
        Session session = new Session();
        Project project = new Project();
        session.addLocalTempTable();
        session.addLocalTempTableConstraint();
        session.addLocalTempTableIndex();
        session.addLock();
        session.addLogPos();
        session.addProcedure();
        session.addTemporaryLob();
        session.addSavepoint();
        project.setEndDate();
        project.setStartDate();
        project.setName("Jan");
        project.setId(789);
        project.setProjectState();

    }

    @Test
    public void updateProject() throws Exception {

    }

    @Test
    public void findById() throws Exception {

    }

    @Test
    public void deleteById() throws Exception {

    }

    @Test
    public void findByName() throws Exception {

    }

    @Test
    public void findProjectsInProgress() throws Exception {

    }

    @Test
    public void findProjectsByUser() throws Exception {

    }

    @Test
    public void findAllProjects() throws Exception {

    }

}