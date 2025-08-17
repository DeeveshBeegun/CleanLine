package com.example.cleanline.service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class FileCleaner {

    public static final Charset UTF8 = Charset.forName("UTF-8");

    public static void main(String[] args) {
        FileCleaner fileCleaner = new FileCleaner();
        fileCleaner.removeDuplicateLines("/Users/deeveshbeegun/Developer/Projects/cleanLine/test.txt", 
        "/Users/deeveshbeegun/Developer/Projects/cleanLine/outputText.txt");

    }

    /*
     * Remove empty lines from a text file
     */
    public void removeEmptyLines(String inputFile, String outputFile) {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), UTF8));
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outputFile), UTF8))) {
                String line = null; 
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.isEmpty()) 
                        continue; 
                    printWriter.println(line);
                }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Remove duplicate lines from a text file
     */
    public void removeDuplicateLines(String inputFile, String outputFile) {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), UTF8));
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outputFile), UTF8))) {
                String line = null; 
                Set<String> lines = new HashSet<String>();
                while ((line = bufferedReader.readLine()) != null) {
                    lines.add(line);
                }
                for (String nonEmptyLine : lines) {
                    printWriter.println(nonEmptyLine);
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
    }

    /*
     * Backup file
     */
    public boolean backupFile(File selectedFile) {

        boolean isBackedUp = false; 

        long timestamp = System.currentTimeMillis();
        Date date = new Date(timestamp);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String formattedDate = simpleDateFormat.format(date);

        Path sourceFile = Paths.get(selectedFile.getAbsolutePath());
        Path backupFile = Paths.get(selectedFile.getAbsolutePath() + "_" + formattedDate);

         try {
            Files.copy(sourceFile, backupFile);
            System.out.println("Backup file successfully created");
            isBackedUp = true; 
        } catch (IOException e) {
            System.out.println("Error while creating backup file: " + e.getMessage());
            isBackedUp = false; 
        }
        
        return isBackedUp;
    }
}
