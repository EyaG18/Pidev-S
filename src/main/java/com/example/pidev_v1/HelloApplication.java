package com.example.pidev_v1;

import com.example.pidev_v1.entities.*;
import com.example.pidev_v1.services.CategorieService;
import com.example.pidev_v1.services.PanierService;
import com.example.pidev_v1.services.ProduitService;
import com.example.pidev_v1.services.UserService;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AfficherProduitCoteClient.fxml"));
       //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AfficherProduitBack.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DetailsCategorie.fxml"));
       //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("FeedProduitsCoteClients.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("authentification.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.<Parent>load(), 1000, 700);
        stage.setTitle("Arya :Store Management Tool :Authentification");
        stage.setScene(scene);
        stage.show();
    }
    public static <List> void main(String[] args) {

        MyDataBase Db = new MyDataBase();
        Db.getCnx();

        /*sendSMS(23067230,
                2324);*/
        launch();
    }
}