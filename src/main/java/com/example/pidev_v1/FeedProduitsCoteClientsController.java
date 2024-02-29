package com.example.pidev_v1;

//import com.gluonhq.charm.glisten.control.TextField;
import com.example.pidev_v1.entities.Catégorie;
import com.example.pidev_v1.entities.Produit;
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

public class FeedProduitsCoteClientsController implements Initializable {


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

    public void DisplayCategoriesInComboBoxFront() {
       /* MyDataBase ct = new MyDataBase();
        Connection cnx = ct.getCnx();
        String queryCategory = "SELECT * FROM catégorie";
        try {
            Statement statement = cnx.createStatement();
            ResultSet QueryOutput = statement.executeQuery(queryCategory);
            int rowCount = 0;
            while (QueryOutput.next()) {
                String queryCategoryName = QueryOutput.getString("NomCatégorie");
                System.out.println("Category Name: " + queryCategoryName); // Output for debugging
                // Add category name to ComboBox
                ComboBoxFilterCat.getItems().add(queryCategoryName);
                rowCount++;
            }

            System.out.println("Nombre de résultats : " + rowCount); // Output for debugging

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer la connexion à la base de données
            try {
                if (cnx != null) {
                    cnx.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/





    }
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
    public void loadproducts(List<Produit> l)
    {
        /*GridPane gp = new GridPane();
        gp.setPrefWidth(794);
        gp.setPrefHeight(400);
        gp.setHgap(60);
        FeedProd.getChildren().clear();
        float x = 20, y = 20;
        int k = 1;
        BorderStroke borderStroke = new BorderStroke(Color.DARKGREY, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(1, 1, 1, 1));
        Border border = new Border(borderStroke);
        for (int i = 0; i < l.size(); i++) {
            AnchorPane anchorpane = new AnchorPane();
            Image image = new Image("file:resources/MediaEya/" + l.get(i).getImageP(), 200, 200, false, false);
            ImageView iv = new ImageView(image);
            Label title = new Label(l.get(i).getNomP());
            String s = String.valueOf(l.get(i).getPrixP());
            Label value = new Label(s);
            anchorpane.setLayoutX(x);
            iv.setLayoutY(y);
            title.setLayoutY(y + 210);
            value.setLayoutY(y + 240);
            AnchorPane.setLeftAnchor(iv, 10.0);
            AnchorPane.setLeftAnchor(title, 10.0);
            AnchorPane.setLeftAnchor(value, 10.0);
            anchorpane.setBorder(border);
            anchorpane.setPrefSize(260, 300);
            Produit p = l.get(i);
            anchorpane.setOnMouseClicked(MouseEvent -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("productFXML.fxml"));
                    Parent product = loader.load();
                    ProductFXMLController prod = loader.getController();
                    prod.setproduct(p);
                    prod.setvisibility(Boolean.TRUE);
                    Scene secondScene = new Scene(product);
                    Stage secondStage = new Stage();
                    secondStage.setScene(secondScene);
                    secondStage.show();
                } catch (IOException ex) {
                    Logger.getLogger(DashboardproducFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            anchorpane.getChildren().addAll(iv, title,value);
            if (k == 3) {
                k = 0;
            }
            gp.addColumn(k, anchorpane);
            k++;
        }
        FeedProd.getChildren().addAll(gp);*/
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
