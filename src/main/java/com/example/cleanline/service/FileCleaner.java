package com.example.cleanline.service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.example.cleanline.utils.FileUtils;

public class FileCleaner {

    public static final Charset UTF8 = StandardCharsets.UTF_8;
    FileUtils fileUtils = new FileUtils();

    private static Logger logger = Logger.getLogger(FileCleaner.class.getName());

    public String removeDuplicateLines(String unprocessedFileContent) {
        logger.log(java.util.logging.Level.INFO, "Removing deduplicate lines");
        String dedup = unprocessedFileContent.lines()
        .collect(Collectors.collectingAndThen(Collectors.toCollection(LinkedHashSet::new),
        set -> String.join(System.lineSeparator(), set)));
        logger.log(java.util.logging.Level.INFO, "Lines deduplicated");

        return dedup;
    }

    public String removeEmptyLines(String unprocessedFileContent) {
        logger.log(java.util.logging.Level.INFO, "Removing empty lines");
        String nonEmpty = unprocessedFileContent.lines()
        .filter(s -> !s.isEmpty())
        .collect(Collectors.joining(System.lineSeparator()));
        logger.log(java.util.logging.Level.INFO, "Empty lines removed");

        return nonEmpty;
    }

    public String removeLineBreaks(String unprocessedFileContent) {
        logger.log(java.util.logging.Level.INFO, "Removing line breaks");
        String noBreaks = unprocessedFileContent.lines()
        .map(String::trim)
        .filter(s -> !s.isEmpty())
        .collect(Collectors.joining(" "));
        logger.log(java.util.logging.Level.INFO, "Line break removed");

        return noBreaks;
    }

    public String convertToUppercase(String unprocessedFileContent) {
        logger.log(java.util.logging.Level.INFO, "Converting to uppercase");
        String uppercaseString = unprocessedFileContent.lines()
        .map(String::toUpperCase)
        .collect(Collectors.joining(System.lineSeparator()));
        logger.log(java.util.logging.Level.INFO, "Line converted to uppercase");

        return uppercaseString;
    }

    public String convertToLowercase(String unprocessedFileContent) {
        logger.log(java.util.logging.Level.INFO, "Converting to lowercase");
        String lowercaseString = unprocessedFileContent.lines()
        .map(String::toLowerCase)
        .collect(Collectors.joining(System.lineSeparator()));
        logger.log(java.util.logging.Level.INFO, "Line converted to lowercase");

        return lowercaseString; 
    }

    public String removeWhiteSpace(String unprocessedFileContent) {
        logger.log(java.util.logging.Level.INFO, "Removing white space");
        String nonWhiteSpace = unprocessedFileContent.lines()
        .map(String::trim)
        .collect(Collectors.joining());
        logger.log(java.util.logging.Level.INFO, "White space removed");
        
        return nonWhiteSpace;
    }

}
