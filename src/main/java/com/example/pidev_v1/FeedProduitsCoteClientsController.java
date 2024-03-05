package com.example.pidev_v1;

//import com.gluonhq.charm.glisten.control.TextField;
import com.example.pidev_v1.entities.Catégorie;
import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.services.CategorieService;
import com.example.pidev_v1.services.MyListener;
import com.example.pidev_v1.services.ProduitService;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.scene.layout.BorderPane;

public class FeedProduitsCoteClientsController implements Initializable {

    @FXML
    private Label LabelUser;
    private User currentUser;
    private User selectedUser;

    @FXML
    private ComboBox<String> ComBoxCategorie;
    @FXML
    private AnchorPane FeedProd;

    @FXML
    private Label NomUserSession;

    @FXML
    private TextField SearchProductByAllNorml;


    @FXML
    private AnchorPane filter;

    @FXML
    private AnchorPane profil;

    @FXML
    private Text vousPouvezAimez;

    @FXML
    private GridPane GridDisplayProducts;

    @FXML
    private AnchorPane BigAnchorPane;

    //////////////////////////////////
    @FXML
    private GridPane menuP_gridpane;

    @FXML
    private AnchorPane menu_formP;

    @FXML
    private ScrollPane prod_scrollpane;

    ProduitService ps = new ProduitService();
    CategorieService cs = new CategorieService();




    private MyListener myListener;

    ObservableList<Produit> cardListDataProduits = FXCollections.observableArrayList();
    ObservableList<Catégorie> ListCategoryObservable = FXCollections.observableArrayList();

    List<Produit> l = new ArrayList<>();

    AjouterProduit AjProCont = new AjouterProduit();

    ComboBox<String> category = new ComboBox<>();


    Produit p= new Produit();
    Catégorie c = new Catégorie();

    AfficherProduitCoteClient afc = new AfficherProduitCoteClient();

    ModifierProduit mf = new ModifierProduit();



    /************************************************************************/
    public void DisplayCategoriesInComboBoxFront2() {

        MyDataBase ct = new MyDataBase();
        Connection cnx = ct.getCnx();
        String queryCategory = "SELECT NomCatégorie FROM catégorie";
        try {
            Statement statement = cnx.createStatement();
            ResultSet QueryOutput = statement.executeQuery(queryCategory);
            while (QueryOutput.next()) {
                String categoryName = QueryOutput.getString("NomCatégorie");
                ComBoxCategorie.getItems().add(categoryName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /******************************************/
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



    /******************************************/

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

    /******************************************************************/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Methode initaliaze est appelle ! ");
        DisplayCategoriesInComboBoxFront2();
        ComBoxCategorie.setOnAction((ActionEvent event) -> { mf.ReturnNewSelectedCategory();

        });

        AfficherProduitsClients();
    }
    /***********************************/


    public void setUser(User user)
    {
        currentUser =user;
        LabelUser.setText(currentUser.getNomuser()+" "+currentUser.getPrenomuser());
        System.out.println("nomcurrentUser"+currentUser.getId_user());
        AfficherProduitsClients();
    }

    /**********Bouton Reclamation**************/



    /*public ObservableList<Produit> getProductsList() {

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
    }*/
}