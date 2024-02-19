package com.example.pidev_v1;

import com.example.pidev_v1.entities.Catégorie;
import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.services.CategorieService;
import com.example.pidev_v1.services.ProduitService;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.pidev_v1.DetailsCategorie;
import com.example.pidev_v1.AjouterProduit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AjouterProduit.fxml"));
        Scene scene = new Scene(fxmlLoader.<Parent>load(), 940, 588);
        stage.setTitle("Arya :Store Management Tool");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {

        MyDataBase Db = new MyDataBase();
        Db.getCnx();
        //CategorieService cs = new CategorieService();
        //int id = cs.getCategoryIdFromName2("Eya");
        //System.out.println("le identifiant unqiue pour la catégorie Leather Jacket est :"+ id);
        ProduitService ps = new ProduitService();

        List<Produit> produits =  ps.DisplayProduct();
        for (Produit produit : produits) {
            System.out.println("ID du Produit: " + produit.getId_Produit());
            System.out.println("Nom du Produit: " + produit.getNomP());
            System.out.println("Prix du Produit: " + produit.getPrixP());
            System.out.println("Quantité en stock: " + produit.getQteP());
            System.out.println("Quantité Seuil: " + produit.getQteSeuilP());
            System.out.println("Image du Produit: " + produit.getImageP());
            System.out.println("-----------------------------------");
        }


        launch();
    }
}