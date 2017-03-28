/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registerTests.registerTests;

import com.mycompany.devman.MainApp;
import com.mycompany.devman.UserRepository;
import com.mycompany.devman.domain.AccountType;
import com.mycompany.devman.domain.User;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 *
 * @author jakub
 */

@RunWith(JUnit4.class)
public class registerTests {
   
    @Before
    public void setup() {
        SessionFactory sessionFactory = new Configuration().addAnnotatedClass(User.class)
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
    public void registerTest() throws Exception {
        User manager = new User();
        manager.setLogin("admin");
        manager.setPassword("devman2017");
        manager.setName("Jan");
        manager.setLastName("Kowalski");
        manager.setPesel("11111111111");
        manager.setEmail("admin@devman.pl");
        manager.setAccountType(AccountType.MANAGER);
        
        manager = UserRepository.addUserToDatabase(manager);
        
        User user = new User();
        user.setLogin("kuba3351");
        user.setPassword("devman2017");
        user.setName("Kuba");
        user.setLastName("Sierżęga");
        user.setPesel("11111111111");
        user.setEmail("kuba3351@gmail.com");
        user.setAccountType(AccountType.EMPLOYEE);
        user.setManager(manager);
        UserRepository.addUserToDatabase(user);
        assertEquals(UserRepository.findByNameAndLastName("Kuba", "Sierżęga").size(), 1);
    }
    
    @Test(expected = Exception.class)
    public void registerWithPeselTooShortTest() throws Exception {
        User manager = new User();
        manager.setLogin("admin");
        manager.setPassword("devman2017");
        manager.setName("Jan");
        manager.setLastName("Kowalski");
        manager.setPesel("11111111111");
        manager.setEmail("admin@devman.pl");
        manager.setAccountType(AccountType.MANAGER);
        
        manager = UserRepository.addUserToDatabase(manager);
        
        User user = new User();
        user.setLogin("kuba3351");
        user.setPassword("devman2017");
        user.setName("Kuba");
        user.setLastName("Sierżęga");
        user.setPesel("111");
        user.setEmail("kuba3351@gmail.com");
        user.setAccountType(AccountType.EMPLOYEE);
        user.setManager(manager);
        UserRepository.addUserToDatabase(user);
        assertEquals(UserRepository.findByNameAndLastName("Kuba", "Sierżęga").size(), 1);
    }
    
}
