package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Catégorie;
import com.example.pidev_v1.tools.MyDataBase;

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
    public List<Catégorie> DisplayCategories() {
       /* ArrayList<Catégorie> categories = new ArrayList<>();
        try {
            String sql = "SELECT * FROM catégorie";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Catégorie catégorie = new Catégorie(resultSet.getInt("Id_Catégorie"), resultSet.getString("NomCatégorie"));
                categories.add(catégorie);
            }

            resultSet.close();
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return categories;*/
        /*ArrayList<Catégorie> categories = new ArrayList<>();
        try {
            String sql = "SELECT * FROM catégorie";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Catégorie cate = new Catégorie(resultSet.getInt("Id_Catégorie"), resultSet.getString("NomCatégorie"));
                categories.add(cate);
            }
            resultSet.close();
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return categories;*/

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
        return categories;
    }














}



