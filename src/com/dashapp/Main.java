package com.dashapp;

import com.dashapp.view.ViewNavigator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws Exception {
        try {
            // Carica il file FXML della schermata di login
            /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/login.fxml"));
            Parent root = loader.load();

            // Crea la scena e aggiunge il CSS se esiste
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/resources/css/style.css").toExternalForm());

            // Imposta e mostra la finestra principale
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.setResizable(false); // Opzionale, evita il ridimensionamento
            stage.show();*/

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/MainView.fxml"));
            Parent root = loader.load();

            // Crea la scena e aggiunge il CSS se esiste
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/resources/css/style.css").toExternalForm());

            // Imposta e mostra la finestra principale
            stage.setTitle("Caricamento brani");
            stage.setScene(scene);
            stage.setResizable(false); // Opzionale, evita il ridimensionamento
            stage.show();


        } catch (Exception e) {
            e.printStackTrace(); // Per debugging
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
