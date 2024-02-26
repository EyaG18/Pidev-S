package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Panier;
import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class PanierService implements IPanier{

    Connection cnx= null;
    Statement statement= null;
    private PreparedStatement ste;
    MyDataBase connect = new MyDataBase();
    private Map<Produit, Integer> mapProduitsDansPanier = new HashMap<>();
    private double totalPanier;

    UserService Us = new UserService();



    public PanierService() {
        cnx = connect.getCnx();
    }




    @Override
    public void ajouterProduitDansPanier(Produit produit, int quantite) {
        if (produit. getQteP() >= quantite) { // Vérifie si la quantité demandée est disponible dans le stock
            mapProduitsDansPanier.put(produit, quantite);
            produit. setQteP(produit. getQteP() - quantite); // Réduit la quantité disponible dans le stock
            calculateTotalPanier();
        } else {
            System.out.println("La quantité demandée n'est pas disponible dans le stock.");
        }
    }

    private void calculateTotalPanier() {
        double total = 0;
        for (Map.Entry<Produit, Integer> entry : mapProduitsDansPanier.entrySet()) {
            Produit produit = entry.getKey();
            int quantite = entry.getValue();
            total += produit.getPrixP() * quantite;
        }
        this.totalPanier = total;
        System.out.println(totalPanier);
    }
    @Override
    public void supprimerProduitDuPanier(Produit produit) {
        mapProduitsDansPanier.remove(produit);
        calculateTotalPanier();
    }

    @Override
    public void creerPanier(User p, double tot) {

    }

    @Override
    public void CreatePanierSsTotal(User p, Map<Produit, Integer> mapProduitsDansPanier) {


    }

    @Override
    public void CreatePanierAvecTotal(User p, Map<Produit, Integer> mapProduitsDansPanier) {

    }

    @Override
    public void CreateAllPanier(User p, Map<Produit, Integer> mapProduitsDansPanier) {

    }

    @Override
    public void creerPanierById(int i, double tot) {



    }

    @Override
    public void CreatePanierForuserOnly(User p) {
        String sql = "INSERT INTO panier (id_user) VALUES ('" + p.getId_user() + "')";

        try
        {
            PreparedStatement ste = cnx.prepareStatement(sql);
            // ste= cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Panier crée pour le user : " + p.getNomuser() + "" + p.getPrenomuser());
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void creerPanierbyIdUser(int idc) {
        String sql = "INSERT INTO panier (id_user) VALUES (?)";

        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, idc); // Utilisation d'un paramètre pour définir la valeur de id_user
            ste.executeUpdate();
            System.out.println("Panier créé pour l'utilisateur avec l'ID : " + idc);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
