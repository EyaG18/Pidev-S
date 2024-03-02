package com.example.pidev_v1;

import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.services.MyListener;
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

    private MyListener myListener;
    User user;
    User currentUser=new User();

    public void setCurrentUser(User user) {
     currentUser = user;
    }

    public void setDat(Produit produit, MyListener myListener) throws FileNotFoundException {
        prodID = produit.getId_Produit();
        this.produit = produit;
        this.myListener = myListener;
        InputStream imageStream = new FileInputStream(produit.getImageP());
        Image image = new Image(imageStream);
        ImageView imageView = new ImageView(image);
        prod_imageView.setImage(imageView.snapshot(null, null));
        prod_name.setText(produit.getNomP());
        prod_price.setText(String.valueOf(produit.getPrixP()) + "DT");
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
        System.out.println("valeur choisie est :" + qty);
        System.out.println("Le identifiant du user actuel est : "+ Ident_User);

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
            if ((currentStock <= stockThreshold)) {
                System.out.println(currentStock);
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Une Erreur est Survenue ! ");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez Réessayez Plus Tard ! ");
                alert.showAndWait();
            } else if (currentStock < qty)
                {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Desolée ! Ce Produit est pour le moment en rupture de Stock");
                    alert.showAndWait();
                }
            else {
                String InsertData = " INSERT INTO panier "
                        +"(id_user,Id_Produit,QuantiteParProduit)"
                        +"VALUES(?,?,?)";

                prepare = connect.prepareStatement(InsertData);
                prepare.setInt(1,Ident_User);
                prepare.setInt(2,prodID);
                prepare.setInt(3,qty);

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

