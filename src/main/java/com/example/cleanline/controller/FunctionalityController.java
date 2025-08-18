package com.example.cleanline.controller;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import com.example.cleanline.service.FileCleaner;
import javafx.fxml.FXML;

public class FunctionalityController {

    @FXML
    private CheckBox duplicates; 

    @FXML
    private CheckBox emptyLines;

    @FXML
    private TextArea unprocessedFilePreview;

    @FXML
    private TextArea processedFilePreview;

    private File selectedFile;

    @FXML
    public void onProcessFileButtonClick() throws IOException {
        executeActions();
    }

    public void executeActions() throws IOException {
        FileCleaner fileCleaner = new FileCleaner();

        boolean isDuplicateChecked = false; 
        boolean isEmptyLineChecked = false; 

        isDuplicateChecked = duplicates.isSelected();
        isEmptyLineChecked = emptyLines.isSelected();
        
        if (isDuplicateChecked) {
            boolean isBackedUp = fileCleaner.backupFile(selectedFile);
            if (isBackedUp) {
                fileCleaner.removeDuplicateLines(selectedFile);
            } 
            else {
                System.out.println("Could not process file. Backup failed");
            }
        } 
        if (isEmptyLineChecked) {
            boolean isBackedUp = fileCleaner.backupFile(selectedFile);
            if (isBackedUp) {
               fileCleaner.removeEmptyLines(selectedFile);
            }
            else {
                System.out.println("Could not process file. Backup failed");
            }
            
        }

        previewSelectedFile(processedFilePreview, selectedFile);
    }

    public void setSelectedFile(File file) {
        this.selectedFile = file; 
        previewSelectedFile(unprocessedFilePreview, file);
    }

    public void previewSelectedFile(TextArea filePreview, File selectedFile) {
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