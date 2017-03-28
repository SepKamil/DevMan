package com.mycompany.devman;

import com.mycompany.devman.domain.Leaves;
import com.mycompany.devman.domain.Message;
import com.mycompany.devman.domain.Project;
import com.mycompany.devman.domain.Task;
import com.mycompany.devman.domain.Team;
import com.mycompany.devman.domain.User;
import com.mycompany.devman.domain.WorkTime;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
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
        sessionFactory = new Configuration().addAnnotatedClass(User.class)
                    .addAnnotatedClass(Leaves.class)
                    .addAnnotatedClass(Message.class)
                    .addAnnotatedClass(Project.class)
                    .addAnnotatedClass(Task.class)
                    .addAnnotatedClass(Team.class)
                    .addAnnotatedClass(WorkTime.class)
                    .buildSessionFactory();
        validatorFactory = Validation.buildDefaultValidatorFactory();
        }catch(HibernateException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Błąd połączenia z bazą danych!");
            alert.setContentText("Sprawdź, czy konfiguracja bazy danych w pliku hibernate.properties jest prawidłowa!");
            e.printStackTrace();
            alert.showAndWait();
            return;
        }
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/login.css");
        stage.setResizable(false);
        stage.setTitle("DevMan - Logowanie");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        if(sessionFactory != null)
            sessionFactory.close();
    }

}
