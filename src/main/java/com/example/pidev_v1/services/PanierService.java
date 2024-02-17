package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.tools.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class PanierService implements IPanier{

    Connection cnx= null;
    Statement statement= null;
    private PreparedStatement ste;
    MyDataBase connect = new MyDataBase();
    Map<Produit, Integer> produitsQuantite = new HashMap<>();

    public PanierService() {
        cnx = connect.getCnx();
    }

    @Override
    public void addProduit(Produit produit, int quantite_panier) {

        produitsQuantite.put(produit, quantite_panier);
    }

    @Override
    public void removeProduit(Produit produit) {
        produitsQuantite.remove(produit);
    }

    @Override
    public void updateQuantiteProduit(Produit produit, int nouvelleQuantite) {
        produitsQuantite.put(produit, nouvelleQuantite);

    }
}
