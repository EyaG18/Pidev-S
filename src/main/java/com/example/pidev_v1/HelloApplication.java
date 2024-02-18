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
import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DetailsCategorie.fxml"));
        Scene scene = new Scene(fxmlLoader.<Parent>load(), 940, 588);
        stage.setTitle("Arya :Store Management Tool");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {

        MyDataBase Db = new MyDataBase();
        Db.getCnx();
        Catégorie c= new Catégorie("Vernis");
        CategorieService cs = new CategorieService();
       // cs.addCategory(c);
        //cs.UpdateCategoryByName("Vernis","Vernis à ongles");
        //cs.DeleteCategoryByName("Blazer de Jour");
        ProduitService ps = new ProduitService();
        //ps.UpdateProductByName("Lunette Dior","Lunettes Gucci");
       // ps.DeleteProductByName("Robe Noire");
        launch();
    }
}