package com.dashapp.controller;

import com.dashapp.model.BranoBean;
import com.dashapp.model.CatalogoDao;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.List;

public class CatalogoController {

    @FXML
    private VBox sidebar;

    @FXML
    private Button filterButton;

    @FXML
    private BorderPane root;

    @FXML
    private VBox braniContainer;

    private boolean sidebarVisible = false;

    @FXML
    public void initialize() {
        // Sidebar non visibile
        root.setLeft(null);

        CatalogoDao cat  = new CatalogoDao();
        List<BranoBean> lists = cat.getBrani();
        lists.forEach(branoBean -> aggiungiBrano(branoBean.getTitolo(), branoBean.getAutori(), branoBean.getGenere().toString(), branoBean.getAnno(), branoBean.getFile()));
    }

    @FXML
    private void toggleSidebar() {
        if (sidebarVisible) {
            // Anima e poi rimuovi
            TranslateTransition transition = new TranslateTransition(Duration.millis(200), sidebar);
            transition.setFromX(0);
            transition.setToX(-sidebar.getWidth());
            transition.setOnFinished(e -> {
                root.setLeft(null); // Rimuove la sidebar completamente dal BorderPane
            });
            transition.play();
        } else {
            // Riaggiungi la sidebar e anima
            root.setLeft(sidebar); // Aggiunge la sidebar di nuovo nel BorderPane

            sidebar.setTranslateX(-sidebar.getWidth()); // Preparazione per animazione
            TranslateTransition transition = new TranslateTransition(Duration.millis(200), sidebar);
            transition.setFromX(-sidebar.getWidth());
            transition.setToX(0);
            transition.play();
        }

        sidebarVisible = !sidebarVisible;
    }

    public void aggiungiBrano(String titolo, String autore, String genere, int anno, String path) {
        // Contenitore principale per ogni brano
        HBox hbox = new HBox(10);
        hbox.getStyleClass().add("brano-card");
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPrefHeight(100);
        hbox.setPrefWidth(600);
        hbox.setPadding(new Insets(10));

        // Info brano (VBox)
        VBox vbox = new VBox(5);
        Text titoloText = new Text(titolo);
        titoloText.getStyleClass().add("text");

        Text autoreText = new Text("Autore: " + autore);
        autoreText.getStyleClass().add("label");

        Text dettagliText = new Text(genere + " • " + anno);
        dettagliText.getStyleClass().add("label");

        vbox.getChildren().addAll(titoloText, autoreText, dettagliText);

        // Spacer
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Pulsante play
        Button playButton = new Button("▶");
        playButton.getStyleClass().add("circular-button");
        playButton.setOnAction(e -> {
            System.out.println("Riproduzione: " + path);
            // TODO: integrazione audio
        });

        // Assembla
        hbox.getChildren().addAll(vbox, spacer, playButton);

        // Aggiungi al contenitore principale
        braniContainer.getChildren().add(hbox);
    }
}
