module com.example.cleanline {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires java.logging;
    opens com.example.cleanline to javafx.fxml;
    exports com.example.cleanline;
    exports com.example.cleanline.controller;
    opens com.example.cleanline.controller to javafx.fxml;
    exports com.example.cleanline.utils;
    opens com.example.cleanline.utils to javafx.fxml;
    exports com.example.cleanline.service;
    opens com.example.cleanline.service to javafx.fxml;
    exports com.example.cleanline.loader to javafx.graphics;
    opens com.example.cleanline.loader to javafx.fxml;


}