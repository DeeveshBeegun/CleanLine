package com.example.cleanline.controller;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import com.example.cleanline.service.FileCleaner;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class FunctionalityController {

    @FXML
    private CheckBox duplicates; 

    @FXML
    private CheckBox emptyLines;

    @FXML
    private TextArea unprocessedFilePreview;

    @FXML
    private TextArea processedFilePreview;

    @FXML
    private Button chooseFileButton;

    private File selectedFile;

    @FXML
    public void onProcessFileButtonClick() throws IOException {
        executeActions();
    }

    @FXML
    private void onChooseFileButtonClick() {
        // Determine stage from any node in the scene
        Scene scene = unprocessedFilePreview != null ? unprocessedFilePreview.getScene() : null;
        Stage stage = scene != null ? (Stage) scene.getWindow() : null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Text Files", "*.txt"),
            new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            setSelectedFile(file);
            if (chooseFileButton != null) {
                chooseFileButton.setVisible(false);
                chooseFileButton.setManaged(false);
            }
        }
    }

    public void executeActions() throws IOException {
        FileCleaner fileCleaner = new FileCleaner();

        boolean isDuplicateChecked = false; 
        boolean isEmptyLineChecked = false; 

        isDuplicateChecked = duplicates.isSelected();
        isEmptyLineChecked = emptyLines.isSelected();
        
        if (isDuplicateChecked && (selectedFile != null)) {
            boolean isBackedUp = fileCleaner.backupFile(selectedFile);
            if (isBackedUp) {
                fileCleaner.removeDuplicateLines(selectedFile);
            } 
            else {
                System.out.println("Could not process file. Backup failed");
            }
        }
        if (isEmptyLineChecked && (selectedFile != null)) {
            boolean isBackedUp = fileCleaner.backupFile(selectedFile);
            if (isBackedUp) {
               fileCleaner.removeEmptyLines(selectedFile);
            } 
        }

        previewSelectedFile(processedFilePreview, selectedFile);
    }

    public void setSelectedFile(File file) {
        this.selectedFile = file; 
        previewSelectedFile(unprocessedFilePreview, file);
    }

    private void previewSelectedFile(TextArea filePreview, File selectedFile) {
        if (selectedFile != null && filePreview != null) {
            try {
                String content = Files.readString(selectedFile.toPath());
                filePreview.setText(content);
            } catch(Exception e) {
                filePreview.setText("Error reading file: " + e.getMessage());
            }
        }
    }
}