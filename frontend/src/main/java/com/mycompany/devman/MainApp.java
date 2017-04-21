package com.mycompany.devman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.hibernate.HibernateException;


public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        try {
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
        BackendSetup.closeDatabaseSession();
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

    public static void main(String[] args) {
        launch(args);
    }
}
