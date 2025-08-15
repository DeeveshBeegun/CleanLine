package com.example.cleanline.service;

import java.io.*;
import java.nio.charset.Charset;

public class FileCleaner {

    public static void main(String[] args) {
        FileCleaner fileCleaner = new FileCleaner();
        fileCleaner.removeEmptyLines(null);
    }

    /*
     * Remove empty lines from a text file
     */
    public String removeEmptyLines(String textFile) {
        Charset utf8 = Charset.forName("UTF-8");
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(textFile)));
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(textFile)))) {
                String line = null; 
                while ((line = bufferedReader.readLine()) != null) {
                    printWriter.println(line);
                }

        } catch(IOException e) {
            e.printStackTrace();
        }

        return "text";
    }
}
