package registerTests.registerTests;

import com.mycompany.devman.BackendSetup;
import com.mycompany.devman.domain.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.*;

/**
 * Created by bloczek on 30.05.2017.
 */
public class WorkTimeRepositoryTest {
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
    public void addNewWorkTime() throws Exception {
        WorkTime workTime = new WorkTime();
        workTime.setId(78L);
        workTime.setTask(new Task());
        workTime.setUser(new User());

    }

    @Test (expected = Exception.class)
    public void findWorkTimeByTask() throws Exception {
        Task task = new Task();
        findWorkTimeByTask();

    }

    @Test (expected = Exception.class)
    public void findByUser() throws Exception {
        findByUser();

    }

    @Test (expected = Exception.class)
    public void updateWorkTime() throws Exception {
        updateWorkTime();

    }

}