package com.mycompany.devman;

import com.mycompany.devman.domain.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.mail.PasswordAuthentication;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Properties;


public class BackendSetup {
    
    private static SessionFactory sessionFactory;
    private static ValidatorFactory validatorFactory;
    private static javax.mail.Session session;
    
    public static javax.mail.Session getMailSession() {
        if(session == null) {
            setupMailSession();
        }
        return session;
    }
    
    public static void closeDatabaseSession() {
        if(sessionFactory != null)
            sessionFactory.close();
    }

    private static void setupMailSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.localhost", "localhost");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        String userName = "devmanmailer@gmail.com";
        String password = "devman2017";

        session = javax.mail.Session.getInstance(props,
        new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
        }
      });
      session.setDebug(true);
    }

    public static Session getDatabaseSession() throws Exception {
        if(sessionFactory == null)
            throw new Exception("Database connection is not set!");
        return sessionFactory.openSession();
    }
    
    public static Validator getEntityValidator() {
        return validatorFactory.getValidator();
    }
    
    public static void setSessionFactory(SessionFactory sessionFactory) {
        BackendSetup.sessionFactory = sessionFactory;
    }
    
    public static void setValidatorFactory(ValidatorFactory validatorFactory) {
        BackendSetup.validatorFactory = validatorFactory;
    }

    public static void setupDatabaseConnection(DatabaseSetup databaseSetup) {
        sessionFactory = new Configuration().addAnnotatedClass(User.class)
                    .addAnnotatedClass(Leave.class)
                    .addAnnotatedClass(Message.class)
                    .addAnnotatedClass(Project.class)
                    .addAnnotatedClass(Task.class)
                    .addAnnotatedClass(Team.class)
                    .addAnnotatedClass(WorkTime.class)
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
                    .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                    .setProperty("hibernate.connection.url", databaseSetup.getUrl())
                    .setProperty("hibernate.connection.username", databaseSetup.getLogin())
                    .setProperty("hibernate.connection.password", databaseSetup.getPassword())
                    .buildSessionFactory();
        validatorFactory = Validation.buildDefaultValidatorFactory();
    }
}
