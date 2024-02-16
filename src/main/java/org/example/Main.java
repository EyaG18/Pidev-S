package org.example;

import entities.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.UserService;
import utilties.DataSource;

import java.io.IOException;

public class Main extends Application {

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("authentification.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Authentification");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DataSource ds = DataSource.getInstance();
        UserService ps = new UserService();
        //User p = new User(0,"grissa","aziz","sousse","azizgrissa@gmail.com",54663263,"administrateur");
        //ps.add(p);
        //System.out.println(ps.afficher());

        launch();

    }
}