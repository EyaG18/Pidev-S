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
import com.example.pidev_v1.API.TwilioSMS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.pidev_v1.API.TwilioSMS.sendSMS;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AfficherProduitCoteClient.fxml"));
       //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AfficherProduitBack.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DetailsCategorie.fxml"));
       //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("FeedProduitsCoteClients.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("authentification.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboardAdminFormUser.fxml"));
        Scene scene = new Scene(fxmlLoader.<Parent>load(), 1056, 785);
        stage.setTitle("Arya :Store Management Tool :Authentification");
        stage.setScene(scene);
        stage.show();
    }
    public static <List> void main(String[] args) {

        MyDataBase Db = new MyDataBase();
        Db.getCnx();

        PanierService panierService = new PanierService();
        Panier panier = new Panier();

        User user = new User();
        user.setNomuser("Eya");
        user.setPrenomuser("Saoud");
        user.setAdrUser("Menzah 6 ");
        user.setEmailUsr("Eyasaoud@yahoo.com");
        user.setPassword("zedhdbhez");
        user.setNumtel(27487994);
        user.setRole("Client");

        Produit produit = new Produit();
        produit.setId_Catégorie(10);
        produit.setNomP("Mom Jeans");
        produit.setPrixP(289);
        produit.setQteP(14);
        produit.setQteSeuilP(5);
        ProduitService ps = new ProduitService();
        //ps.addProduct(produit);


        //UserService us = new UserService();
        //us.add(user);

        //panierService.CreerPanierByEntities(user,produit,2);
        //panierService.createPanierAll(user.getId_user(),produit.getId_Produit(),3);
        //panierService.createPanierAll(9,22,1);

        /*sendSMS(23067230,
                2324);*/
        launch();
    }
}