package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.application.Platform;
import java.io.IOException;

public class MainFx extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
                // Load and display the supplier form
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterFournisseur.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                primaryStage.setScene(scene);
                primaryStage.setTitle("Ajouter fournisseur");
                primaryStage.show();


    }}