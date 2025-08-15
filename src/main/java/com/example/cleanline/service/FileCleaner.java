package com.example.cleanline.service;

import java.io.*;
import java.nio.charset.Charset;
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
                for (String l : lines) {
                    printWriter.println(l);
                }
                
            } catch(IOException e) {
                e.printStackTrace();
            }
    }
}
