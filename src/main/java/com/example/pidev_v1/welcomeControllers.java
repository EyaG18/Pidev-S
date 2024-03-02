package com.example.pidev_v1;

import com.example.pidev_v1.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class welcomeControllers {
    @FXML
    private BorderPane borderpane;


    @FXML
    void gestionAvis(ActionEvent event) throws IOException {
        AnchorPane view= FXMLLoader.load(getClass().getResource("interfaceAvis.fxml"));

        borderpane.setCenter(view);

    }

    @FXML
    void gestionReclamation(ActionEvent event) throws IOException {

        StackPane view= FXMLLoader.load(getClass().getResource("interfaceReclamation.fxml"));

        borderpane.setCenter(view);

    }
}
