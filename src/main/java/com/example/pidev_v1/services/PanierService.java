package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Panier;
import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.tools.MyDataBase;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public void createPanierAll( int id_user, int id_Produit, int QuantitéParProduit,double PrixPanierUnitaire) {
        String sql = "INSERT INTO panier (id_user, Id_Produit, QuantiteParProduit,PrixPanierUnitaire) VALUES (?, ?, ?,?)";

        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id_user); // Utilisation d'un paramètre pour définir la valeur de id_user
            ste.setInt(2,id_Produit);
            ste.setInt(3,QuantitéParProduit);
            ste.setDouble(4,PrixPanierUnitaire);
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

    @Override
    public double afficherSuma() {
        String requete = "SELECT SUM(PrixPanierUnitaire) AS total FROM panier"; // Utilisation de SUM() pour calculer la somme
        double total = 0; // Initialisation de la variable total

        try (PreparedStatement pst = cnx.prepareStatement(requete)) {
            ResultSet rs = pst.executeQuery();

            if (rs.next()) { // Vérification s'il y a des résultats
                total = rs.getDouble("total"); // Récupération de la somme depuis la colonne total
            }
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total; // Retourner la somme calculée
    }


    @Override
    public void truncatePanier() {
        try {
            Statement ste = cnx.createStatement();
            String requete = "truncate table panier";
            ste.execute(requete);
            System.out.println("Panier vide !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }




    }

    @Override
    public void suprimer(Panier t) {
        try {
            Statement ste = cnx.createStatement();
            String requetedelete = "delete from panier where Id_Produit='" + t.getId_Produit()+"'";
            ste.execute(requetedelete);
            System.out.println("Produit supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }




    }

    @Override
    public List<Panier> afficherCotenuPanier() {
        List<Panier> list = new ArrayList<>();

        try {
//            System.out.println("************************Liste des panier************************");
            String requete = "SELECT * FROM panier";
            PreparedStatement pst =cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Panier(rs.getInt(3),rs.getInt(4) ,rs.getDouble(5)  )   );
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
/****************************************************/















}