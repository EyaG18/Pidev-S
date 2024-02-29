package com.example.pidev_v1;


import com.example.pidev_v1.entities.Catégorie;
import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.services.MyListener;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.System.out;

public class AfficherProduitCoteClient implements Initializable {
    @FXML
    private GridPane GridDisplayProducts;

    @FXML
    private ScrollPane scrollPaneProduct;

    @FXML
    private AnchorPane anchorpane;


    ObservableList<Produit> ListProduit;
    private MyListener myListener;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            DisplayProducts();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /********************************************/

public void  DisplayProducts() throws FileNotFoundException {

    ListProduit = getProductsList();
    int column = 0;
    int row = 1;

    for (Produit produit : ListProduit) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ItemProduct.fxml"));
        AnchorPane anchorPane;
        try {
            anchorPane = fxmlLoader.load();
            ItemProduct itemProduit = fxmlLoader.getController();
            itemProduit.setDataProducts(produit, myListener);
            GridDisplayProducts.add(anchorPane, column++, row);
            if (column == 3) {
                column = 0;
                row++;
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}








    /********************************************/
    public ObservableList<Produit> getProductsList() {

        ObservableList<Produit> ListProducts = FXCollections.observableArrayList();
        MyDataBase ct = new MyDataBase();
        Connection cnx= ct.getCnx();
        String query = "SELECT * FROM produit"; // Assurez-vous de remplacer "books" par le nom de votre table approprié
        Statement st;
        ResultSet rs;
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Produit produit;
            while (rs.next()) {
                produit =new Produit(rs.getInt("Id_Catégorie"),rs.getString("NomP"),rs.getFloat("PrixP"),rs.getInt("QteP"),rs.getInt("QteSeuilP"),rs.getString("ImageP"));
                ListProducts.add(produit);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ListProducts;
    }




























}
