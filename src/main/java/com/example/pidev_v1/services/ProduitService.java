package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Catégorie;
import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.tools.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitService implements IProduit {

    Connection cnx= null;
    Statement statement= null;
    private PreparedStatement ste;
    MyDataBase connect = new MyDataBase();

    public ProduitService() {
        cnx = connect.getCnx();
    }
    /**************************************************************/
    @Override
    public void addProduct(Produit produit) {
        String sql = "INSERT INTO produit (Id_Catégorie, NomP, PrixP, QteP, QteSeuilP, ImageP) VALUES ('" + produit.getId_Catégorie() + "', '" + produit.getNomP() + "', '" + produit.getPrixP() + "', '" + produit.getQteP() + "', '" + produit.getQteSeuilP() + "', '" + produit.getImageP() + "')";

        try
        {
            PreparedStatement ste = cnx.prepareStatement(sql);
            // ste= cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Personne Ajoutée avec success !");
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    /**************************************************************/
    @Override
    public void DeleteProduct(int idP) {
        String sql = "DELETE FROM produit WHERE Id_Produit = ?";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setInt(1, idP);
            preparedStatement.executeUpdate();
            System.out.println("Produit supprimé !");
        } catch(SQLException e) {
            // Gérer l'exception ici
            e.printStackTrace();

        }

    }
    /**************************************************************/
    @Override
    public void UpdateProduct(int idP, int Idcat, String nomPP, float prixp, int qtep, int qteSp, String imagePP) {
        String sql = "UPDATE produit SET Id_Catégorie = ?, NomP = ?, PrixP = ?, QteP = ?, QteSeuilP = ?, ImageP = ? WHERE Id_Produit = ?";

        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setInt(1, Idcat);
            preparedStatement.setString(2, nomPP);
            preparedStatement.setFloat(3, prixp);
            preparedStatement.setInt(4, qtep);
            preparedStatement.setInt(5, qteSp);
            preparedStatement.setString(6, imagePP);
            preparedStatement.setInt(7, idP);
            preparedStatement.executeUpdate();

            System.out.println("Modification effectuee avec succes !");
        } catch (SQLException e) {
            // Gestion de l'exception
            e.printStackTrace();
        }


    }
    /**************************************************************/
    @Override
    public List<Produit> DisplayProduct() {

        ArrayList<Produit> produits = new ArrayList<>();

        try {
            String sql = "SELECT * FROM produit";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int idCategorie = resultSet.getInt("Id_Catégorie");
                String nomCategorie = GetCategoryNameById(idCategorie); // Récupère le nom de la catégorie par l'id
                System.out.println("Nom de la catégorie: " + nomCategorie);

                Produit p = new Produit(
                        resultSet.getInt("Id_Produit"),
                        idCategorie,
                        resultSet.getString("NomP"),
                        resultSet.getFloat("PrixP"),
                        resultSet.getInt("QteP"),
                        resultSet.getInt("QteSeuilP"),
                        resultSet.getString("ImageP")
                );

                produits.add(p);
                System.out.println("Catégorie: " + nomCategorie + ", Produit: " + p);
            }
            resultSet.close();
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return produits;

        }
    /**************************************************************/
    private String GetCategoryNameById(int idCategorie) {
        String nomCategorie = "";
        try {
            String sql = "SELECT NomCatégorie FROM catégorie WHERE Id_Catégorie = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setInt(1, idCategorie);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                nomCategorie = resultSet.getString("NomCatégorie");
            }

            resultSet.close();
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return nomCategorie;
    }


}
