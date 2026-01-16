package com.example.cleanline.service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.cleanline.utils.FileUtils;

public class FileCleaner {

    public static final Charset UTF8 = Charset.forName("UTF-8");
    FileUtils fileUtils = new FileUtils();

    public String removeDuplicateLines(String unprocessedFileContent) {
        System.out.println("Removing deduplicate lines");
        String dedup = unprocessedFileContent.lines()
        .collect(Collectors.collectingAndThen(Collectors.toCollection(LinkedHashSet::new),
        set -> String.join(System.lineSeparator(), set)));
        System.out.println("Lines deduplicated");

        return dedup;
    }

    public String removeEmptyLines(String unprocessedFileContent) {
        System.out.println("Removing empty lines");
        String nonEmpty = unprocessedFileContent.lines()
        .filter(s -> !s.isEmpty())
        .collect(Collectors.joining(System.lineSeparator()));
        System.out.println("Empty lines removed");

        return nonEmpty;
    }

    public String removeLineBreaks(String unprocessedFileContent) {
        System.out.println("Removing line breaks");
        String noBreaks = unprocessedFileContent.lines()
        .map(String::trim)
        .filter(s -> !s.isEmpty())
        .collect(Collectors.joining(" "));
        System.out.println("Line break removed");

        return noBreaks;
    }

    public String convertToUppercase(String unprocessedFileContent) {
        System.out.println("Converting to uppercase"); 
        String uppercaseString = unprocessedFileContent.lines()
        .map(String::toUpperCase)
        .collect(Collectors.joining(System.lineSeparator()));
        System.out.println("Line converted to uppercase");

        return uppercaseString;
    }

    public String convertToLowercase(String unprocessedFileContent) {
        System.out.println("Converting to lowercase");
        String lowercaseString = unprocessedFileContent.lines()
        .map(String::toLowerCase)
        .collect(Collectors.joining(System.lineSeparator()));
        System.out.println("Line converted to lowercase");

        return lowercaseString; 
    }

    public String removeWhiteSpace(String unprocessedFileContent) {
        System.out.println("Removing white space");
        String nonWhiteSpace = unprocessedFileContent.lines()
        .map(String::trim)
        .collect(Collectors.joining());
        System.out.println("White space removed");
        
        return nonWhiteSpace;
    }

    /*
     * Backup file
     */
    public boolean backupFile(File selectedFile) {

        boolean isBackedUp = false; 

        if (selectedFile != null) {
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
        }
        else {
            System.out.println("Selected file is null");
        }

        return isBackedUp;
    }


}
