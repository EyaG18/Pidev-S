package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Catégorie;
import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.collections.FXCollections;
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
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev2", "root", "");
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
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev2", "root", "")) {
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

    @Override
    public int getCategoryIdFromName2(String selectedCategoryNameProduct) {
        int categoryId = -1; // Valeur par défaut si la catégorie n'est pas trouvée

        String query = "SELECT Id_Catégorie FROM catégorie WHERE NomCatégorie = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev2?useUnicode=true&characterEncoding=utf-8", "root", "");
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, selectedCategoryNameProduct);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    categoryId = rs.getInt("Id_Catégorie");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée dans votre application
        }
        return categoryId;
    }

    @Override
    public String getCategoryNameById(int categoryId) {
        String catName ="";
        String query = "SELECT NomCatégorie FROM catégorie WHERE Id_Catégorie = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev2?useUnicode=true&characterEncoding=utf-8", "root", "");
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, catName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    catName = rs.getString("NomCatégorie");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée dans votre application
        }
        return catName;
    }
//////////////////////////////////////////////////////////////////
    @Override
    public Catégorie getCategoryById(int categoryId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Catégorie categorie = null;

        try {
            // Connexion à la base de données
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev2", "root", "");

            // Requête SQL pour récupérer la catégorie par son ID
            String query = "SELECT * FROM catégorie WHERE Id_Catégorie = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, categoryId);

            // Exécution de la requête
            rs = stmt.executeQuery();

            // Traitement du résultat
            if (rs.next()) {
                // Création de l'objet Categorie à partir des données récupérées
                categorie = new Catégorie();
                categorie.setId_Catégorie(rs.getInt("Id_Categorie"));
                categorie.setNomCatégorie(rs.getString("NomCategorie"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermeture des ressources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return categorie;
    }




    @Override
    public Catégorie getCategoryBName(String chosenNameC) {
        Catégorie catégorie = null;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev2", "root", "")) {
            String query = "SELECT * FROM catégorie WHERE NomCatégorie = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, chosenNameC);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        catégorie = new Catégorie();
                        catégorie.setId_Catégorie(resultSet.getInt("Id_Catégorie"));
                        catégorie.setNomCatégorie(resultSet.getString("NomCatégorie"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return catégorie;
    }

    List<Catégorie> categories =getCategoryList();

    @Override
    public boolean isCategoryNameExists(String nameCat) {
        for (Catégorie catégorie : categories) {
            if (catégorie.getNomCatégorie().equals(nameCat)) {
                System.out.println("Une catégorie du meme nom existe Déja !Try Again ! ");
                return true;
                // Le produit avec le même nom existe déjà
            }
        }
        System.out.println("Nom catégorie n'existe pas");
        return false; // Aucun produit avec le même nom n'a été trouvé
    }

    @Override
    public ObservableList<Catégorie> getCategoryList() {
        ObservableList<Catégorie> ListCat = FXCollections.observableArrayList();
        MyDataBase db = new MyDataBase();
        Connection cnx = db.getCnx();
        //String query = "SELECT p.*, c.NomCatégorie FROM produit p JOIN catégorie c ON p.Id_Catégorie = c.Id_Catégorie"; // Utilisation de la jointure pour récupérer le nom de la catégorie
        // String query = " SELECT * FROM VueProduit";
        String query = "SELECT * FROM catégorie";
        Statement st;
        ResultSet rs;
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                int idCategorie = rs.getInt("Id_Catégorie");
                String nomProduit = rs.getString("NomCatégorie");
Catégorie cat = new Catégorie(idCategorie,nomProduit);

                ListCat.add(cat);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListCat;









    }


}






