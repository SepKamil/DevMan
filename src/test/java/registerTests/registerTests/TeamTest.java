package registerTests.registerTests;

import com.mycompany.devman.MainApp;
import com.mycompany.devman.repositories.ProjectRepository;
import com.mycompany.devman.repositories.TeamRepository;
import com.mycompany.devman.domain.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
public class TeamTest {


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
        Team team = new Team();
        team.setName("test");
        Project project = new Project();
        project.setName("addTeam");
        project.setStartDate(LocalDate.of(1969, 06, 9));
        project.setEndDate(LocalDate.of(2001, 06, 9));
        team.setProject(project);
        Session session = MainApp.getDatabaseSession();
        ProjectRepository.addProject(project);
        TeamRepository.addTeam(team);
        assertEquals(team, session.createQuery("FROM Team").list().get(0));
    }





}
