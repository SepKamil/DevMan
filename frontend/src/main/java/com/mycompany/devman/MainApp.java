package com.mycompany.devman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.hibernate.HibernateException;
import org.ini4j.Ini;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;


public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        try {
            String system = System.getProperty("os.name");
            System.out.println("System: "+system);
            Map<String, String> env = System.getenv();
            env.forEach((s, s2) -> System.out.println("Key: "+s+" value: "+s2));
            File file = new File(env.get("HOME")+"/.devman/config.ini");
            if(!file.exists()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd!");
                alert.setHeaderText("Brak pliku konfiguracyjnego!");
                alert.setContentText("plik ~/.devman/config.ini nie istnieje!");
                alert.showAndWait();
                return;
            }
            else {
                Ini config = new Ini(file);
                DatabaseSetup setup = new DatabaseSetup();
                setup.setHost(config.get("Database", "database.host"));
                setup.setName(config.get("Database", "database.name"));
                setup.setPort(config.get("Database", "database.port"));
                setup.setLogin(config.get("Database", "database.username"));
                setup.setPassword(config.get("Database", "database.password"));
                BackendSetup.setupDatabaseConnection(setup);
            }
            BackendSetup.getDatabaseSession();
        }catch(Exception e) {
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

    public void showLoginWindow(Stage stage) throws java.io.IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/main/login.css");
        stage.setResizable(false);
        stage.setTitle("DevMan - Logowanie");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
