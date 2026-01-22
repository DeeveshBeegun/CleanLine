package com.example.cleanline.loader;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CleanLinePreLoader extends Preloader {

    private Stage preloaderStage;
    private Scene scene;

    public CleanLinePreLoader() {

    }

    @Override
    public void init() throws Exception {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.preloaderStage = primaryStage;

        FXMLLoader loader = new FXMLLoader(CleanLinePreLoader.class.getResource("/com/example/cleanline/preloader-view.fxml"));

        Parent root = loader.load();

        Scene scene = new Scene(root, 1000, 1000);

        preloaderStage.setScene(scene);
        preloaderStage.initStyle(StageStyle.UNDECORATED);
        preloaderStage.show();
    }

    @Override
    public void handleApplicationNotification(Preloader.PreloaderNotification info) {
//        if (info instanceof ProgressNotification) {
//            FXMLDocumentController.label.setText("Loading " + ((ProgressNotification) info).getProgress() + "%");
//        }
    }

    @Override
    public void handleStateChangeNotification(Preloader.StateChangeNotification info) {
        StateChangeNotification.Type type = info.getType();
        switch (type) {
            case BEFORE_START:
                preloaderStage.hide();
                break;
        }
    }
}
