package com.dashapp.controller;

import com.dashapp.util.PasswordHasher;
import com.dashapp.view.ViewNavigator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

import com.dashapp.model.UtenteBean;
import com.dashapp.model.AccessoDao;

public class RegistrationController
{
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;


    @FXML
    private void handleRegistration(ActionEvent event) throws IOException, SQLException {
        String usernames = username.getText();
        String passwords = password.getText();

        /*if (username.isEmpty() || password.isEmpty()) {
            showError("Compila tutti i campi.");
            return;
        }*/

        UtenteBean utente = new UtenteBean(usernames, passwords);
        AccessoDao acc = new AccessoDao();
        if(acc.registrazioneControllo(utente))
        {
            ViewNavigator.changeTitle("Login applicazione");
            ViewNavigator.navigateToLogin();
        }
        else
            System.out.println("Errore nella registrazione");
    }
}
