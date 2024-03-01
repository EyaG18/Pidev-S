package com.example.pidev_v1;

import com.example.pidev_v1.entities.Panier;
import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.services.MyListener;
import com.example.pidev_v1.services.PanierService;
import com.example.pidev_v1.services.ProduitService;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.pidev_v1.tools.MyDataBase.user;

public class CardProduct implements Initializable {

    @FXML
    private AnchorPane card_form;

    @FXML
    private Button prod_addBtn;

    @FXML
    private ImageView prod_imageView;

    @FXML
    private Label prod_name;

    @FXML
    private Label prod_price;

    @FXML
    private Spinner<Integer> prod_spinner;

    private SpinnerValueFactory<Integer> spin;

    private Produit produit;
    private Image image;

    @FXML
    private Label idP;

    private int prodID;
    private String prod_image;
    private float price;

    private int ProdID;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private Alert alert;

    private int qty;
    private double totalP;
    private double pr;

    private int newStock;

    private MyListener myListener;
    User user;
    User currentUser=new User();

    public void setCurrentUser(User user) {
     currentUser = user;
     System.out.println(user);
    }
    Panier panier = new Panier();
    PanierService panierService = new PanierService();
    ProduitService produitService = new ProduitService();

    public void setDat(Produit produit, MyListener myListener) throws FileNotFoundException {
        this.produit = produit;
        prodID = produit.getId_Produit();
        this.myListener = myListener;
        InputStream imageStream = new FileInputStream(produit.getImageP());
        Image image = new Image(imageStream);
        ImageView imageView = new ImageView(image);
        prod_imageView.setImage(imageView.snapshot(null, null));
        prod_name.setText(produit.getNomP());
        prod_price.setText(String.valueOf(produit.getPrixP()) + "DT");
        //idP.setText(String.valueOf(prodID));
    }
    public void setQuantity() {
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        prod_spinner.setValueFactory(spin);
    }
    @FXML
    void addBtnP(ActionEvent event) {
       /* FeedProduitsCoteClientsController feedProduitsCoteClientsController = new FeedProduitsCoteClientsController();
        int Ident_User;
        Ident_User = feedProduitsCoteClientsController.userID(user);*/
        int Ident_User = currentUser.getId_user();
        qty = prod_spinner.getValue();
        System.out.println(prodID);
        System.out.println("valeur choisie est :" + qty);
        System.out.println("Le identifiant du user actuel est : "+ Ident_User);
        System.out.println("Le produit selectionne est :"+ produit );
        Panier panier1 = new Panier(currentUser,produit,qty);

       //panierService.createPanierAll(panier1.getUtilisateurPan().getId_user(),prodID,qty);

       connect = MyDataBase.getInstance().getCnx();
        try {
            int currentStock = 0;
            int stockThreshold =0;
            // Récupération de la quantité en stock (QteP) et de la quantité seuil (QteSeuilP) pour le produit spécifié (prodID)
            String stockQuery = "SELECT QteP, QteSeuilP FROM produit WHERE Id_Produit = ?";
            prepare = connect.prepareStatement(stockQuery);
            prepare.setInt(1,prodID);
            result = prepare.executeQuery();
            if (result.next()) {
                currentStock = result.getInt("QteP");
                stockThreshold = result.getInt("QteSeuilP");
            }
            if ((currentStock == stockThreshold)) {
                System.out.println(currentStock);
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Une Erreur est Survenue ! ");
                alert.setHeaderText(null);
                alert.setContentText("Desolée! Ce Produit sera Bientot en Stock ! ");
                alert.showAndWait();
            } else if (currentStock < qty)
                {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Desolée ! La Quantité que Vous demandez dépasse Le Stock Actuel !");
                    alert.showAndWait();
                }
            else {
                panierService.createPanierAll(panier1.getUtilisateurPan().getId_user(),prodID,qty);
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Panier de l'utilisateur "+ currentUser.getNomuser() + ""+currentUser.getPrenomuser());
                alert.setHeaderText("");
                alert.setContentText("Produit Ajouté au Panier !  ");
                alert.showAndWait();
                newStock= produit.getQteP()-qty;
                produitService.UpdateProduct(prodID,produit.getId_Catégorie(),produit.getNomP(),produit.getPrixP(),newStock,produit.getQteSeuilP(),produit.getImageP());
                System.out.println("le nouveau stock du produit" + produit.getNomP() + "est : " + newStock);
                totalP = (qty * produit.getPrixP());
                System.out.println("Le le total du panier avec le produit "+ prodID + "est : "+totalP + " du client "+ Ident_User);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
/********************Yasmine******************/
        @FXML
        void commenter (ActionEvent event){

        }

        @FXML
        void noteA (ActionEvent event){

        }

        @FXML
        void noteB (ActionEvent event){

        }

        @FXML
        void noteC (ActionEvent event){

        }

        @FXML
        void noteD (ActionEvent event){

        }

        @FXML
        void noteE (ActionEvent event){

        }
        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            setQuantity();
        }
    }

