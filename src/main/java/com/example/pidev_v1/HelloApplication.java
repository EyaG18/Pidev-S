package com.example.pidev_v1;

import com.example.pidev_v1.entities.RoleUser;
import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.services.CategorieService;
import com.example.pidev_v1.services.ProduitService;
import com.example.pidev_v1.services.UserService;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AfficherProduitCoteClient.fxml"));
       FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AfficherProduitBack.fxml"));
        Scene scene = new Scene(fxmlLoader.<Parent>load(), 1000, 700);
        stage.setTitle("Arya :Store Management Tool");
        stage.setScene(scene);
        stage.show();




    }
    public static <List> void main(String[] args) {

        MyDataBase Db = new MyDataBase();
        Db.getCnx();
        //CategorieService cs = new CategorieService();
        //int id = cs.getCategoryIdFromName2("Eya");
        //System.out.println("le identifiant unqiue pour la catégorie Leather Jacket est :"+ id);
       // ProduitService ps = new ProduitService();

        //cs.DeleteCategoryByName("Vestes");
        UserService u = new UserService();
/*
        User u1 = new User("Yassmine","Hammami","Ariana","YassmineHammami@gmail.com","AZAZAZ",25010246);
        User u2 = new User("Tyalor","Swift","Melbounre","ContactPro@gmail.com","MeredithReader",91850241);

        u.add(u1);
        u.add(u2);

*/
        /*User u23 = new User("Eva","Swift","Melbounre","ContactPro@gmail.com","XXXYizA",91850241, "Client");
      u.add(u23);*/

        CategorieService cs =new CategorieService();
         String i = cs.getCategoryNameById(Integer.parseInt("8"));
         System.out.println(i);

        ProduitService produitService = new ProduitService();
        produitService.DeleteProductByName("Sungaze");






        launch();
    }
}