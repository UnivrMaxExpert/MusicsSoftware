package com.dashapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import com.dashapp.model.UtenteBean;
import com.dashapp.model.AccessoDao;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private void handleLogin(ActionEvent event) throws SQLException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        errorLabel.setVisible(false);

        if (username.isEmpty() || password.isEmpty()) {
            showError("Compila tutti i campi.");
            return;
        }

        UtenteBean utente = new UtenteBean(username, password);
        AccessoDao acc = new AccessoDao();
        if(acc.loginControllo(utente))
            System.out.println("Login successful");
        else
            System.out.println("Login failed");
    }

    @FXML
    private void handleGoToRegister(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/registration.fxml"));
        Parent registrationRoot = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene registrationScene = new Scene(registrationRoot);
        registrationScene.getStylesheets().add(getClass().getResource("/resources/css/style.css").toExternalForm());
        stage.setScene(registrationScene);
        stage.setTitle("Registrazione");
        stage.show();
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }
}
