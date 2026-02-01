package com.example.cleanline;

import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class CleanLineApplication extends Application {

    @Override
    public void init() throws Exception {
        // Simulate some long initialization tasks
        for (int i = 1; i <= 10; i++) {
            Thread.sleep(100);
            notifyPreloader(new Preloader.ProgressNotification(i * 100));
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CleanLineApplication.class.getResource("functionality-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        stage.setTitle("CleanLine");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}