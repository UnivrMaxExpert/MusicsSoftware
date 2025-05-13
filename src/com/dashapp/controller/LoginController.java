package com.dashapp.controller;

import com.dashapp.view.ViewNavigator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import com.dashapp.model.UtenteBean;
import com.dashapp.model.AccessoDao;
/*Migliorare il codice per permettere di muoversi tra gli fxml
* 1-implementare i textfield e menu a discesa per l'inserimento di un brano
* 2-implementare nuova tabella db con autori come TEXT e tutte le informazioni con anche quale utente l'ha inserito
* 3-implementare query al db dove si scelgono tutti i brani e si stampano sul catalogo con i loro dati*/
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
        if(acc.loginControllo(utente)) {
            System.out.println("Login successful");
            ViewNavigator.setAuthenticatedUser(username);
        }
        else
            System.out.println("Login failed");
    }

    @FXML
    private void handleGoToRegister(ActionEvent event) throws IOException {
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/registration.fxml"));
        Parent registrationRoot = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene registrationScene = new Scene(registrationRoot);
        registrationScene.getStylesheets().add(getClass().getResource("/resources/css/style.css").toExternalForm());
        stage.setScene(registrationScene);
        stage.setTitle("Registrazione");
        stage.show();*/
        ViewNavigator.navigateToRegister();
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }
}
