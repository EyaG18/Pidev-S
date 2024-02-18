package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Catégorie;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieService implements ICategorie{


    Connection cnx= null;
    Statement statement= null;
    private PreparedStatement ste;
    MyDataBase connect = new MyDataBase();

    public CategorieService() {
        cnx = connect.getCnx();
    }
/**************************************************************/
    @Override
    public void addCategory(Catégorie categorie) {
        String sql = "INSERT INTO catégorie (Id_Catégorie, NomCatégorie) VALUES ('" + categorie.getId_CatégorieC() + "', '" + categorie.getNomCatégorie() + "')";
        try
        {
            PreparedStatement ste = cnx.prepareStatement(sql);
            // ste= cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Categorie Ajoutée avec success !");
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    /**************************************************************/
    @Override
    public void UpdateCategory(int idC, String namec) {
        String sql = "UPDATE catégorie SET NomCatégorie = ?  WHERE Id_Catégorie = ?";

        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setString(1, namec);
            preparedStatement.setInt(2, idC);
            preparedStatement.executeUpdate();

            System.out.println("Modification de la categorie effectuee avec succes !");
        } catch (SQLException e) {
            // Gestion de l'exception
            e.printStackTrace();
        }
    }
    /**************************************************************/
    @Override
    public void SearchCategoryById(int idC) {

    }
    /**************************************************************/
    @Override
    public void SearchCategoryByName(String namec) {

    }
/********************************************************************************/
    @Override
    public Catégorie GetCategoryById(int idC) {
        String query = "SELECT NomCategorie FROM Catégorie WHERE Id_Categorie = ?";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/esprit", "root", "");
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, idC);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String nomCategorie = rs.getString("NomCatégorie");
                    return new Catégorie(nomCategorie);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; // Si la catégorie n'est pas trouvée, retourner null
    }
/******************************************************************************/
    @Override
    public void DeleteCategory(int idC) {
        String sql = "DELETE FROM catégorie WHERE Id_Catégorie = ?";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setInt(1, idC);
            preparedStatement.executeUpdate();
            System.out.println("Catégorie supprimée !");
        } catch(SQLException e) {
            // Gérer l'exception ici
            e.printStackTrace();

        }
    }
/******************************************************/
    @Override
    public ObservableList<Catégorie> DisplayCategories() {

        String req = "select * from catégorie";
        List<Catégorie> categories = new ArrayList<>();
        Statement st = null;
        try {
            st = cnx.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet rs = null;
        try {
            rs = st.executeQuery(req);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (true){
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Catégorie c = new Catégorie();
            try {
                c.setId_Catégorie(rs.getInt("Id_Catégorie"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                c.setNomCatégorie(rs.getString("NomCatégorie"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            categories.add(c);
        }
        return (ObservableList<Catégorie>) categories;
    }
    /*********************************************************************/
    @Override
    public void UpdateCategoryByName(String oldName, String newName) {
        String sql = "UPDATE catégorie SET NomCatégorie = ? WHERE NomCatégorie = ?";

        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, oldName);
            preparedStatement.executeUpdate();

            System.out.println("Modification de la catégorie effectuée avec succès !");
        } catch (SQLException e) {
            // Gestion de l'exception
            e.printStackTrace();
        }
    }

    @Override
    public void DeleteCategoryByName(String namec) {
        // Connexion à la base de données
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/esprit", "root", "")) {
            // Requête SQL pour supprimer la catégorie par son nom
            String sql = "DELETE FROM Catégorie WHERE NomCatégorie = ?";
            // Création de la requête préparée
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Assignation du nom de la catégorie à supprimer
                statement.setString(1, namec);
                // Exécution de la requête
                int rowsDeleted = statement.executeUpdate();
                // Vérification si une ligne a été supprimée
                if (rowsDeleted > 0) {
                    System.out.println("La catégorie a été supprimée avec succès.");
                } else {
                    System.out.println("Aucune catégorie avec ce nom n'a été trouvée.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la catégorie: " + e.getMessage());
        }




    }


}



