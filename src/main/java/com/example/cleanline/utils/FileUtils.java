package com.example.cleanline.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileUtils {

    public void deleteFile(File sourceFile) {
         if (sourceFile.exists()) {
            if (sourceFile.delete()) {
                System.out.println("Original file deleted.");
            } else {
                System.out.println("Failed to delete original file.");
            }
        } else {
            System.out.println("File does not exists");
        }
    }

    public void moveFile(Path sourcePath, Path targetPath) throws IOException {
        System.out.println("Moving file from " + sourcePath.toString() + " to " + targetPath.toString());
        Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
    }

    public String readFileContent(File inputFile) {
        String fileContent = null;
        if (inputFile != null) {
            try {
                System.out.println("Reading file Content");
                fileContent = Files.readString(inputFile.toPath());
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return fileContent;
    }

    public void writeFileContentToFile(File outputFile) {
        
    }
}
