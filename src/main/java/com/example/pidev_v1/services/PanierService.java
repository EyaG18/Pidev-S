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
    /****************************************************/
    private void calculateTotalPanier() {

    }

    /****************************************************/
    @Override
    public void supprimerProduitDuPanier(Produit produit) {

    }

    /****************************************************/
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
/****************************************************/
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

    @Override
    public void createPanierAll( int id_user, int id_Produit, int QuantitéParProduit) {
        String sql = "INSERT INTO panier (id_user, Id_Produit, QuantiteParProduit) VALUES (?, ?, ?)";

        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id_user); // Utilisation d'un paramètre pour définir la valeur de id_user
            ste.setInt(2,id_Produit);
            ste.setInt(3,QuantitéParProduit);
            ste.executeUpdate();
            System.out.println("Panier créé pour l'utilisateur avec l'ID : " + id_user);
            System.out.println("Panier est mis à jours aves les produits selectionnes");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /****************************************************/
    /****************************************************/
    @Override
    public Double GetTotalPanier() {
        return null;
    }

    @Override
    public void CreerPanierByEntities(User user, Produit produit, int Quantite) {
        String sql = "INSERT INTO panier id_user,Id_Produit, QuantiteParPorduit VALUES (?,?,?)";

        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, user.getId_user()); // Utilisation d'un paramètre pour définir la valeur de id_user
            ste.setInt(2,produit.getId_Produit());
            ste.setInt(3,Quantite);
            ste.executeUpdate();
            System.out.println("Panier créé pour l'utilisateur avec l'ID : " + user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
/****************************************************/















}
