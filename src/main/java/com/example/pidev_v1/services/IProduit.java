package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Produit;
import javafx.collections.ObservableList;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface IProduit  {

    void addProduct(Produit produit);

    void DeleteProduct(int idP);
    void UpdateProduct(int idP , int Idcat, String nomPP, float prixp, int qtep, int qteSp , String imagePP );
     List<Produit> DisplayProduct ();

     String DeleteProductByName(String nameProduct );

 void UpdateProductByName(String oldProudctName , String newProductName);

    public List<Produit> getByCategory(String category);

    public Produit GetProductByName(String NameChosen);
    public void modifyProduct(Produit p);
    public ObservableList<Produit> getProductList();

    public boolean isProductNameExists(String productName);

    public boolean isProductImageExists(String productImage);

    //public String translateProduct (String s,String l) throws IOException, JSONException;


}
