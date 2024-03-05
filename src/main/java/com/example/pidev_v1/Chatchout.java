package com.example.pidev_v1;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
public class Chatchout {
    @FXML
    private WebView webView;

    public void initialize() {
        WebEngine engine = webView.getEngine();
        engine.load("https://console.dialogflow.com/api-client/demo/embedded/62dd377b-9d00-4819-a57f-29b688745f1b");
    }
}