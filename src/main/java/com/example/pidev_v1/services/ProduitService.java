package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Catégorie;
import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.tools.MyDataBase;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.twiml.fax.Receive;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
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
/*********************************************************/
    @Override
    public Produit GetProductByName(String NameChosen) {
        Produit product = null;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev2", "root", "")) {
            String query = "SELECT * FROM produit WHERE NomP = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, NameChosen);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        product = new Produit();
                        product.setId_Produit(resultSet.getInt("Id_Produit"));
                        product.setId_Catégorie(resultSet.getInt("Id_Catégorie"));
                        product.setNomP(resultSet.getString("NomP"));
                        product.setPrixP(resultSet.getFloat("PrixP"));
                        product.setQteP(resultSet.getInt("QteP"));
                        product.setQteSeuilP(resultSet.getInt("QteSeuilP"));
                        product.setImageP(resultSet.getString("ImageP"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void modifyProduct(Produit p) {
        try {
            PreparedStatement pr = cnx.prepareStatement("UPDATE produit SET NomP=?, PrixP=?, QteP=?, QteSeuilP=?, ImageP=?, Id_Catégorie=? WHERE Id_Produit=?");
            pr.setString(1, p.getNomP());
            pr.setFloat(2, p.getPrixP());
            pr.setInt(3, p.getQteP());
            pr.setInt(4, p.getQteSeuilP());
            pr.setString(5, p.getImageP());
            pr.setInt(6, p.getId_Catégorie());
            pr.setInt(7, p.getId_Produit());

            pr.executeUpdate();
            System.out.println("Le produit avec l'ID : " + p.getId_Produit() + " a été modifié avec succès.");
        } catch (SQLException sqlEx) {
            System.out.println("Erreur lors de la modification du produit : " + sqlEx.getMessage());
        }
    }

    @Override
    public ObservableList<Produit> getProductList() {
        ObservableList<Produit> ListProduct = FXCollections.observableArrayList();
        MyDataBase db = new MyDataBase();
        Connection cnx = db.getCnx();
        //String query = "SELECT p.*, c.NomCatégorie FROM produit p JOIN catégorie c ON p.Id_Catégorie = c.Id_Catégorie"; // Utilisation de la jointure pour récupérer le nom de la catégorie
        // String query = " SELECT * FROM VueProduit";
        String query = "SELECT * FROM produit";
        Statement st;
        ResultSet rs;
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                int idProduit = rs.getInt("Id_Produit");
                int idCategorie = rs.getInt("Id_Catégorie");
                String nomProduit = rs.getString("NomP");
                float prixProduit = rs.getFloat("PrixP");
                int qteProduit = rs.getInt("QteP");
                int qteSeuilProduit = rs.getInt("QteSeuilP");
                String imageProduit = rs.getString("ImageP");
                Produit produit1= new Produit(idCategorie,nomProduit,prixProduit,qteProduit,qteSeuilProduit,imageProduit);
                ListProduct.add(produit1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListProduct;
    }



    List<Produit> produits = getProductList();

    @Override
    public boolean isProductNameExists(String productName) {
        for (Produit produit : produits) {
            if (produit.getNomP().equals(productName)) {
                System.out.println("Produit nom existe");
                return true;
               // Le produit avec le même nom existe déjà
            }
        }
        System.out.println("Produit nom existe pas");
        return false; // Aucun produit avec le même nom n'a été trouvé
    }

    @Override
    public boolean isProductImageExists(String productImage) {
        for (Produit produit : produits) {
            if (produit.getImageP().equals(productImage)) {
                return true; // Le produit avec la même photo existe déjà
            }
        }
        return false; // Au
    }


    }



/********************************/




















