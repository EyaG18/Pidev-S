package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Catégorie;
import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.tools.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProduitService implements IProduit {

    Connection cnx= null;
    Statement statement= null;
    private PreparedStatement ste;
    MyDataBase connect = new MyDataBase();

    List<Produit> Lp = new ArrayList<>();

    public ProduitService() {
        cnx = connect.getCnx();
    }

    CategorieService cs = new CategorieService();
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










    /*****************************************************************/
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
                        resultSet.getInt("Id_Catégorie"),
                        resultSet.getString("NomP"),
                        resultSet.getFloat("PrixP"),
                        resultSet.getInt("QteP"),
                        resultSet.getInt("QteSeuilP"),
                        resultSet.getString("ImageP")
                );

                produits.add(p);
               // System.out.println("Catégorie: " + nomCategorie + ", Produit: " + p);
            }
            resultSet.close();
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return produits;
        }

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



 /*********************************************************************/
    @Override
    public String DeleteProductByName(String nameProduct) {
        // Connexion à la base de données
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev2", "root", "")) {
            // Requête SQL pour supprimer la catégorie par son nom
            String sql = "DELETE FROM Produit WHERE NomP = ?";
            // Création de la requête préparée
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Assignation du nom de la catégorie à supprimer
                statement.setString(1, nameProduct);
                // Exécution de la requête
                int rowsDeleted = statement.executeUpdate();
                // Vérification si une ligne a été supprimée
                if (rowsDeleted > 0) {
                    System.out.println("La Produit a été supprimée avec succès.");
                } else {
                    System.out.println("Aucun Produit avec ce nom n'a été trouvée.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du produit: " + e.getMessage());
        }
        return nameProduct;
    }


    @Override
    public void UpdateProductByName(String oldProudctName, String newProductName) {
        String sql = "UPDATE produit SET NomP = ? WHERE NomP = ?";

        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setString(1, newProductName);
            preparedStatement.setString(2, oldProudctName);
            preparedStatement.executeUpdate();

            System.out.println("Modification du produit effectuée avec succès !");
        } catch (SQLException e) {
            // Gestion de l'exception
            e.printStackTrace();
        }
    }
/*************************************************/
    @Override
    public List<Produit> getByCategory(String category) {
        List<Produit> filteredProducts = new ArrayList<>();
        try {
            // Récupérer tous les produits
            List<Produit> allProducts = DisplayProduct();

            // Filtrer les produits par catégorie
            for (Produit produit : allProducts) {
                Catégorie categorie = cs.getCategoryById(produit.getId_Catégorie());
               String chosenCat= cs.getCategoryNameById(categorie.getId_CatégorieC());
                if (categorie != null && chosenCat.equals(category)) {
                    filteredProducts.add(produit);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return filteredProducts;
    }


}
