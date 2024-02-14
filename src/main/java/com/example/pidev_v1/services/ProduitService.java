package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Catégorie;
import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.tools.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class ProduitService implements IProduit {

    Connection cnx= null;
    Statement statement= null;
    private PreparedStatement ste;
    MyDataBase connect = new MyDataBase();

    public ProduitService() {
        cnx = connect.getCnx();
    }

    @Override
    public void addProduct(com.example.pidev_v1.entities.Produit produit) {

    }

    @Override
    public void DeleteProduct(int idP) {

    }

    @Override
    public void UpdateProduct(int idP, Catégorie cat, String nomPP, float prixp, int qtep, int qteSp, String imagePP) {

    }

    @Override
    public List<com.example.pidev_v1.entities.Produit> DisplayProduct() {
        return null;
    }
}
