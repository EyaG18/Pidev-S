package com.example.pidev_v1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class dashboardEmployee {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane BigAnchorPane;

    @FXML
    private Label LabelUser;

    @FXML
    private TextField SearchProductByAllNorml;

    @FXML
    private AnchorPane filter;

    @FXML
    void initialize() {
        assert BigAnchorPane != null : "fx:id=\"BigAnchorPane\" was not injected: check your FXML file 'dashboardEmployee.fxml'.";
        assert LabelUser != null : "fx:id=\"LabelUser\" was not injected: check your FXML file 'dashboardEmployee.fxml'.";
        assert SearchProductByAllNorml != null : "fx:id=\"SearchProductByAllNorml\" was not injected: check your FXML file 'dashboardEmployee.fxml'.";
        assert filter != null : "fx:id=\"filter\" was not injected: check your FXML file 'dashboardEmployee.fxml'.";

    }

}
