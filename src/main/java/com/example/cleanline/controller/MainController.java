package com.example.cleanline.controller;

import java.io.File;
import java.io.IOException;

import com.example.cleanline.service.FileCleaner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


public class MainController {

    FileCleaner fileCleaner = new FileCleaner();
    File file = null; 

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onChooseFileButtonClick() {
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        chooseFile(stage);
        //Load new FXML
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cleanline/functionality-view.fxml"));
            Parent root = loader.load();
            FunctionalityController functionalityController = loader.getController();
            functionalityController.setSelectedFile(file);
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void chooseFile(Stage mainStage) {
        FileChooser fileChooser = new FileChooser(); 
        fileChooser.setTitle("Open file");
        fileChooser.getExtensionFilters().addAll(
            new ExtensionFilter("Text Files", "*.txt"),
            new ExtensionFilter("CSV Files", "*.csv"));

        File selectedFile = fileChooser.showOpenDialog(mainStage); 
        System.out.println(selectedFile.getAbsolutePath());
        if (selectedFile != null) {
            welcomeText.setText("Selected file: " + selectedFile.getName());
            file = selectedFile; 
        }
    }
}