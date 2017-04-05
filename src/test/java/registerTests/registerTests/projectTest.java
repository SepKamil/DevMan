package registerTests.registerTests;

import com.mycompany.devman.MainApp;
import com.mycompany.devman.ProjectRepository;
import com.mycompany.devman.UserRepository;
import com.mycompany.devman.domain.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Type;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by bloczek on 04.04.2017.
 */
@RunWith(JUnit4.class)
public class projectTest {


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
        MainApp.setSessionFactory(sessionFactory);
        MainApp.setValidatorFactory(validatorFactory);
    }

    @Test
    public void addTest() throws Exception {
        Project project = new Project();
        project.setName("addProjekt");
        project.setStartDate(LocalDate.of(1969, 06, 9));
        project.setEndDate(LocalDate.of(2001, 06, 9));
        Session session = MainApp.getDatabaseSession();
        ProjectRepository.addProject(project);
        assertEquals(project, session.createQuery("FROM Project").list().get(0));
    }

    @Test
    public void findProjectById() throws Exception {
        Project project = new Project();
        project.setName("addProjekt");
        project.setStartDate(LocalDate.of(1969, 06, 9));
        project.setEndDate(LocalDate.of(2001, 06, 9));
        ProjectRepository.addProject(project);
        Project testProject = ProjectRepository.findById(project.getId());
        assertEquals(project, testProject);
    }

    @Test(expected = Exception.class)
    public void findProjectByIdIfNotExist() throws Exception {
        Project testProject = ProjectRepository.findById(new Long(0));
    }

    @Test(expected = Exception.class)
    public void deleteProjectIfNotExist() throws Exception {
        ProjectRepository.deleteById(new Long(0));
    }

   @Test (expected = Exception.class)
    public void addProjectIfIncorrect() throws Exception {
       Project project= new Project();
       ProjectRepository.addProject(project);



   }


}
