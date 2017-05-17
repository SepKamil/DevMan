package com.mycompany.devman.repositories;

import com.mycompany.devman.BackendSetup;
import com.mycompany.devman.domain.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by bloczek on 17.05.2017.
 */
public class LeaveRepositoryTest {
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



    @Test(expected =Exception.class)
    public void addNewLeaveRequest() throws Exception {
        Leave leave = new Leave();
        User user = new User();
        user.setLogin("l");
        user.setPassword("s");
        user.setPesel("11111111111");
        user.setEmail("mail@mail.pl");
        user.setAccountType(AccountType.EMPLOYEE);
        user.setId(1l);
        leave.setEmployee(user);
        leave.setNumberOfDays(12);
        LeaveRepository.addNewLeaveRequest(leave);
    }

    @Test
    public void findLeavesByUser() throws Exception {
      User user= new User();
        user.setId(69l);
        user.setLastName("Kowalska");
        user.setName("Marta");
        user.setEmail("sample@email.pl");
        user.setLogin("samplelogin");
        user.setPassword("samplepassword");

        assertEquals(0,LeaveRepository.findLeavesByUser(user).size());
    }

    @Test
    public void findPendingLeavesByManager() throws Exception {
        User user = new User();
        user.setId(789l);
        user.setLogin("login");
        user.setPassword("haslo");
        user.setEmail("mail@sample.com");
        user.setName("Andrzej");
        user.setLastName("Marek");
        user.setAccountType(AccountType.MANAGER);

        assertEquals(LeaveRepository.findPendingLeavesByManager(user).size(), 0);
    }

    @Test(expected =Exception.class)
    public void acceptLeaveRequest() throws Exception {
        Leave leave = new Leave();
        User user = new User();
        user.setId(123l);
        user.setLogin("login");
        user.setName("Jan");
        user.setLastName("Kowalski");
        user.setPesel("78978978978");
        user.setEmail("mail@mail.com");
        user.setLogin("login");
        user.setPassword("password");
        user.setAccountType(AccountType.MANAGER);
        leave.setEmployee(user);
        leave.setId(789l);
        leave.setNumberOfDays(12);
        LeaveRepository.acceptLeaveRequest(leave);

    }

    @Test(expected = Exception.class)
    public void rejectLeaveRequest() throws Exception {
        Leave leave = new Leave();
        User user = new User();
        user.setId(123l);
        user.setLogin("login");
        user.setName("Jan");
        user.setLastName("Kowalski");
        user.setPesel("78978978978");
        user.setEmail("mail@mail.com");
        user.setLogin("login");
        user.setPassword("password");
        user.setAccountType(AccountType.MANAGER);
        leave.setEmployee(user);
        leave.setId(789l);
        leave.setNumberOfDays(12);
        LeaveRepository.rejectLeaveRequest(leave);

    }

}