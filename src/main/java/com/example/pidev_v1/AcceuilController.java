package com.example.pidev_v1;

import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.services.MyListener;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AcceuilController implements Initializable {
    private User currentUser;
    public void setUser(User user)
    {
        currentUser =user;
        System.out.println("nomcurrentUser"+currentUser.getId_user());
        AfficherProduitsClients();
    }
    @FXML
    private GridPane menuP_gridpane;
    ObservableList<Produit> cardListDataProduits = FXCollections.observableArrayList();
    ModifierProduit mf = new ModifierProduit();


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

    public void AfficherProduitsClients()
    {
        System.out.println("Methode AFFICHERProduitsClient est appelle");


        //cardListDataProduits.clear();
        cardListDataProduits.addAll(getProductList());
        if ( cardListDataProduits != null)
        {
            System.out.println("Recuperation reussie ! ");
        } else { System.out.println("erreur de recuperation"); }

        //cardListDataProduits.addAll(getProductList());   //on stocke dans notre liste observable liste de sproduits recuperes depuis la base
        int row = 0;
        int column = 0;

        menuP_gridpane.getChildren().clear();
        menuP_gridpane.getRowConstraints().clear();
        menuP_gridpane.getColumnConstraints().clear();

        for(int q =0 ; q< cardListDataProduits.size() ; q ++)
        {
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("cardProduct.fxml"));
                AnchorPane pane = load.load();
                CardProduct cardProduct = load.getController();
                MyListener MyListener = null;
                cardProduct.setDat(cardListDataProduits.get(q) , MyListener);
                CardProduct controller = load.getController();
                controller.setUser(currentUser);

                if ( column ==3)
                {
                    column =0;
                    row += 1;
                }
                menuP_gridpane.add(pane, column++,row);
                GridPane.setMargin(pane, new Insets(10));


            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AfficherProduitsClients();
    }



}