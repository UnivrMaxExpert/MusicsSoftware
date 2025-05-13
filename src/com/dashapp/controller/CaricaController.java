package com.dashapp.controller;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class CaricaController {

    @FXML
    private Button btn;  // FXML ci consente di legare il bottone dal file .fxml a questa variabile

    // Metodo per aprire il selettore di file quando il bottone viene premuto
    @FXML
    public void openFileChooser() {
        // Crea il FileChooser
        FileChooser fileChooser = new FileChooser();

        // Aggiungi i filtri per i file (mp3, mp4, pdf, jpg, jpeg)
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("File Audio", "*.mp3"),
                new ExtensionFilter("File Video", "*.mp4"),
                new ExtensionFilter("Documenti PDF", "*.pdf"),
                new ExtensionFilter("Immagini JPG", "*.jpg"),
                new ExtensionFilter("Immagini JPEG", "*.jpeg")
        );

        // Mostra la finestra di dialogo per la selezione del file
        Stage stage = (Stage) btn.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        // Verifica se un file è stato selezionato
        //La parte di scelta del file deve essere gestita da un model richiamato in questa classe DA MODIFICARE
        if (selectedFile != null) {
            try {
                // Ottieni il percorso della cartella user_files/file1
                Path baseDir = Path.of("src/com/dashapp/user_files/file1");

                // Crea le cartelle se non esistono
                if (!Files.exists(baseDir)) {
                    Files.createDirectories(baseDir);
                }

                // Costruisci il percorso di destinazione
                Path destination = baseDir.resolve(selectedFile.getName());

                // Copia il file
                Files.copy(selectedFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("File copiato in: " + destination.toAbsolutePath());

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Errore nella copia del file.");
            }
        } else {
            System.out.println("Nessun file selezionato.");
        }

    }
}

