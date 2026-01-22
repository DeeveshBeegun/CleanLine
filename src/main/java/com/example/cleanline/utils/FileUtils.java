package com.example.cleanline.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;

public class FileUtils {

    private static final Logger logger = Logger.getLogger(FileUtils.class.getName());

    public String readFileContent(File inputFile) {
        String fileContent = null;
        if (inputFile != null) {
            try {
                logger.log(java.util.logging.Level.INFO, "Reading file Content");
                fileContent = Files.readString(inputFile.toPath());
            } catch(IOException e) {
                logger.log(java.util.logging.Level.SEVERE, "Failed to read file content: {}", e.getMessage());
            }
        }
        return fileContent;
    }

    public void writeFileContentToFile(File outputFile, String content) {
        try {
            Files.writeString(outputFile.toPath(), content, StandardCharsets.UTF_8, 
            StandardOpenOption.CREATE, 
            StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            logger.log(java.util.logging.Level.SEVERE, "Failed to write content to file: {}", e.getMessage());
        }
    }
}
