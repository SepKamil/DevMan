package com.mycompany.devman;

import com.mycompany.devman.domain.Leave;
import com.mycompany.devman.domain.Message;
import com.mycompany.devman.domain.Project;
import com.mycompany.devman.domain.Task;
import com.mycompany.devman.domain.Team;
import com.mycompany.devman.domain.User;
import com.mycompany.devman.domain.WorkTime;
import java.util.Properties;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javax.mail.PasswordAuthentication;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class MainApp extends Application {
    
    private static SessionFactory sessionFactory;
    private static ValidatorFactory validatorFactory;
    
    private static javax.mail.Session session;
    
    public static javax.mail.Session getMailSession() {
        if(session == null) {
            setupMailSession();
        }
        return session;
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

    public static Session getDatabaseSession() {
        return sessionFactory.openSession();
    }
    
    public static Validator getEntityValidator() {
        return validatorFactory.getValidator();
    }
    
    public static void setSessionFactory(SessionFactory sessionFactory) {
        MainApp.sessionFactory = sessionFactory;
    }
    
    public static void setValidatorFactory(ValidatorFactory validatorFactory) {
        MainApp.validatorFactory = validatorFactory;
    }


    @Override
    public void start(Stage stage) throws Exception {
        try {
        setupDatabaseConnection();
        }catch(HibernateException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Błąd połączenia z bazą danych!");
            alert.setContentText("Sprawdź, czy konfiguracja bazy danych w pliku hibernate.properties jest prawidłowa!");
            e.printStackTrace();
            alert.showAndWait();
            return;
        }

        showLoginWindow(stage);
    }

    private void showLoginWindow(Stage stage) throws java.io.IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/login.css");
        stage.setResizable(false);
        stage.setTitle("DevMan - Logowanie");
        stage.setScene(scene);
        stage.show();
    }

    private void setupDatabaseConnection() throws Exception {
        sessionFactory = new Configuration().addAnnotatedClass(User.class)
                    .addAnnotatedClass(Leave.class)
                    .addAnnotatedClass(Message.class)
                    .addAnnotatedClass(Project.class)
                    .addAnnotatedClass(Task.class)
                    .addAnnotatedClass(Team.class)
                    .addAnnotatedClass(WorkTime.class)
                    .buildSessionFactory();
        validatorFactory = Validation.buildDefaultValidatorFactory();
    }

    public static void main(String[] args) {
        launch(args);
        if(sessionFactory != null)
            sessionFactory.close();
    }
}
