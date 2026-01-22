package com.example.cleanline.controller;

import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;

public class FXMLDocumentController {

    @FXML
    private WebView svgView;

    @FXML
    private Label label;

    @FXML
    public void initialize() {
        URL svgUrl = getClass().getResource("com/example/cleanline/cl.svg");
        if (svgUrl != null) {
            svgView.getEngine().load(svgUrl.toExternalForm());
        } else {
            label.setText("SVG not found");
        }
    }
}
