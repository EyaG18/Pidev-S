package com.example.pidev_v1;

import com.example.pidev_v1.entities.Catégorie;
import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.services.CategorieService;
import com.example.pidev_v1.services.ProduitService;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        MyDataBase Db = new MyDataBase();
        Db.getCnx();
        ProduitService ps = new ProduitService();
        CategorieService Cs = new CategorieService();
        Catégorie categorie1 =new Catégorie("Lunettes");
        Catégorie cat2 = new Catégorie("Veste");
        Cs.addCategory(categorie1);
        Cs.addCategory(cat2);
        int a= cat2.getId_CatégorieC();

        Produit pr1 = new Produit(2, "Lunette Dior",125,85,5,"/ressources/MediaEya/VesteCuireZaraNoire.jpg");
        Produit pr = new Produit(1,"Lunette Dior",125,85,5,"/ressources/MediaEya/LunetteDiorNoire.jpg");
       ps.addProduct(pr1);
       ps.UpdateProduct(9,2,"Veste Zara Noire",260,30,5,"/ressources/MediaEya/VesteCuireZaraNoire.jpg");
        Produit pr3 = new Produit(4, "Robe Noire",180,15,5,"/ressources/MediaEya/RobeZaraNoire.jpg");

        Catégorie cat3= new Catégorie("Robe");
        Cs.addCategory(cat3);

        System.out.println("|-------------------------------|");
        ps.addProduct(pr3);
        System.out.println("|-------------------------------|");

        List<Produit> L1 = ps.DisplayProduct();
        for (Produit produit : L1) {
            System.out.println(produit);
        }

        List<Catégorie> C1 = Cs.DisplayCategories();
        for (Catégorie c : C1) {
            System.out.println(c);
        }
        System.out.println("|-------------------------------|");
        ps.DeleteProduct(6);
        System.out.println("|-------------------------------|");
        Cs.DeleteCategory(5);
        System.out.println("|-------------------------------|");
       //Cs.UpdateCategory(2,"Leather Jacket");



        launch();
    }
}