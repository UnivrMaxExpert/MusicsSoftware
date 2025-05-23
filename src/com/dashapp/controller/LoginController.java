package com.dashapp.controller;

import com.dashapp.model.AccessoDao;
import com.dashapp.model.UtenteBean;
import com.dashapp.view.AnimationUtil;
import com.dashapp.view.ViewNavigator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;
    @FXML private VBox loginBox;
    @FXML private Button loginButton;
    @FXML private Hyperlink registerLink;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Animazione all'avvio
        AnimationUtil.fadeIn(loginBox, 600);
        AnimationUtil.slideInFromBottom(loginBox, 600, 300);
        AnimationUtil.pulse(loginButton, 1000); // Effetto loop
    }

    @FXML
    private void handleLogin(ActionEvent event) throws SQLException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        errorLabel.setVisible(false);

        if (username.isEmpty() || password.isEmpty()) {
            showError("Compila tutti i campi.");
            AnimationUtil.shake(loginBox, 10);
            return;
        }

        UtenteBean utente = new UtenteBean(username, password);
        AccessoDao acc = new AccessoDao();

        if (acc.loginControllo(utente)) {
            ViewNavigator.setAuthenticatedUser(username);
            ViewNavigator.changeTitle("Home applicazione");
            ViewNavigator.navigateToHome();
        } else {
            showError("Credenziali errate.");
            AnimationUtil.shake(loginBox, 300);
        }
    }

    @FXML
    private void handleGoToRegister(ActionEvent event) throws IOException {
        ViewNavigator.navigateToRegister();
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
        AnimationUtil.fadeIn(errorLabel, 10);
    }
}
