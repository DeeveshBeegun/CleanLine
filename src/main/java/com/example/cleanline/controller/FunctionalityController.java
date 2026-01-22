package com.example.cleanline.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import java.io.File;
import java.nio.file.Files;
import com.example.cleanline.service.FileCleaner;
import com.example.cleanline.utils.FileUtils;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class FunctionalityController {

    @FXML
    private CheckBox duplicatesCheckBox;

    @FXML
    private CheckBox emptyLinesCheckBox;

    @FXML 
    private CheckBox lineBreaksCheckBox;

    @FXML 
    private CheckBox uppercaseCheckBox;

    @FXML
    private CheckBox lowercaseCheckBox;

    @FXML 
    private CheckBox whiteSpaceCheckBox;

    @FXML
    private TextArea unprocessedFilePreview;

    @FXML
    private TextArea processedFilePreview;

    @FXML
    private Button chooseFileButton;

    @FXML
    private Button downloadButton;

    private File selectedFile;

    private String unprocessedFileContent;

    private String processedFileContent = null;

    FileUtils fileUtils = new FileUtils();

    private boolean isFileChosen = false;

    @FXML
    public void onProcessFileButtonClick() {
        if (unprocessedFileContent == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "No file selected. Please choose a file to process.");
            alert.showAndWait();
        } else {
            executeActions();
        }
    }

    @FXML
    public void onClearInputButtonClick() {
        if (unprocessedFilePreview != null) {
            unprocessedFilePreview.clear();
        }
        selectedFile = null;
        if (chooseFileButton != null) {
            chooseFileButton.setVisible(true);
            chooseFileButton.setManaged(true);
            chooseFileButton.toFront();
            chooseFileButton.requestFocus();
        }
    }

    @FXML
    public void onClearOutputButtonClick() {
        if (processedFilePreview != null) {
            processedFilePreview.clear();
        }
    }

    @FXML
    public void onDownloadOutputButtonClick() {
        String content = processedFileContent != null ? processedFileContent : "";

        Scene scene = processedFilePreview != null ? processedFilePreview.getScene() : null; 
        Stage stage = scene != null ? (Stage) scene.getWindow() : null;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Text Files", "*.txt"),
            new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
            new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        File fileDest = fileChooser.showSaveDialog(stage); 
        if (fileDest != null) {
            fileUtils.writeFileContentToFile(fileDest, content);
        }
    }   

    @FXML
    private void onChooseFileButtonClick() {
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
        unprocessedFileContent = fileUtils.readFileContent(file);
        }
    }

    public void executeActions() {
        FileCleaner fileCleaner = new FileCleaner();

        boolean isDuplicateChecked = false; 
        boolean isEmptyLineChecked = false; 
        boolean isLineBreaksChecked = false; 
        boolean isConvertToUpperCaseChecked = false; 
        boolean isConvertToLowerCaseChecked = false; 
        boolean isRemoveWhiteSpaceChecked = false; 

        isDuplicateChecked = duplicatesCheckBox.isSelected();
        isEmptyLineChecked = emptyLinesCheckBox.isSelected();
        isLineBreaksChecked = lineBreaksCheckBox.isSelected();
        isConvertToLowerCaseChecked = lowercaseCheckBox.isSelected();
        isConvertToUpperCaseChecked = uppercaseCheckBox.isSelected();
        isRemoveWhiteSpaceChecked = whiteSpaceCheckBox.isSelected();

        processedFileContent = unprocessedFileContent;
        
        if (isDuplicateChecked) {
            processedFileContent = fileCleaner.removeDuplicateLines(processedFileContent);
        } 
        if (isEmptyLineChecked) {
            processedFileContent = fileCleaner.removeEmptyLines(processedFileContent);
        }
        if (isLineBreaksChecked) {
            processedFileContent = fileCleaner.removeLineBreaks(processedFileContent);
        } 
        if (isConvertToLowerCaseChecked) {
            processedFileContent = fileCleaner.convertToLowercase(processedFileContent);
            uppercaseCheckBox.setDisable(true);
        }
        if (isConvertToUpperCaseChecked) {
            processedFileContent = fileCleaner.convertToUppercase(processedFileContent);
            lowercaseCheckBox.setDisable(true);
        }
        if (isRemoveWhiteSpaceChecked) {
            processedFileContent = fileCleaner.removeWhiteSpace(processedFileContent);
        }

        previewContent(processedFilePreview, processedFileContent);

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

    private void previewContent(TextArea filePreview, String fileContent) {
        downloadButton.setDisable(false);
        try {
            filePreview.setText(fileContent);
        } catch(Exception e) {
            filePreview.setText("Error reading content: " + e.getMessage());
        }
        
    }
}