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
public class UserTest {


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
        User user = new User();
        user.setName("addUser");
        user.setAccountType(AccountType.EMPLOYEE);
        user.setEmail("sample@email.pl");
        user.setLastName("kowalski");
        user.setPassword("password1");
        user.setPesel("23232312345");
        Session session = MainApp.getDatabaseSession();
        UserRepository.addUserToDatabase(user);
        assertEquals(user, session.createQuery("FROM User").list().get(0));
    }

    @Test
    public void findUserById() throws Exception {
        User user = new User();
        user.setLogin("login");
        user.setName("jan");
        user.setAccountType(AccountType.EMPLOYEE);
        user.setEmail("sample@email.pl");
        user.setLastName("kowalski");
        user.setPassword("password1");
        user.setPesel("23232312345");
        UserRepository.addUserToDatabase(user);
        assertEquals(user,UserRepository.findByLoginAndPassword("login","password1"));
    }

    @Test(expected = Exception.class)
    public void findUserByLoginAndPasswordIfNotExist() throws Exception {
        User testUsers = UserRepository.findByLoginAndPassword("6","9");
    }

    @Test(expected = Exception.class)
    public void deleteUserIfNotExist() throws Exception {
        UserRepository.deleteById(new Long(0));
    }

    @Test (expected = Exception.class)
    public void addUserIfIncorrect() throws Exception {
        User user = new User();
        UserRepository.addUserToDatabase(user);



    }


}
