package com.example.pidev_v1;

import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.services.MyListener;
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
import java.util.ResourceBundle;

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
    private Image image ;

    private int prodID;
    private String prod_image;
    private float price;

    private int ProdID;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private Alert alert;



    private int qty;
    private double totalP ;
    private double pr;
    private MyListener myListener;

    public void setDat(Produit produit ,MyListener myListener) throws FileNotFoundException {
        prodID=produit.getId_Produit();
        this.produit = produit;
        this.myListener=myListener;
        InputStream imageStream = new FileInputStream(produit.getImageP());
        Image image = new Image(imageStream);
        ImageView imageView = new ImageView(image);
        prod_imageView.setImage(imageView.snapshot(null, null));
        prod_name.setText(produit.getNomP());
        prod_price.setText( String.valueOf(produit.getPrixP()) + "DT");
    }
    public void setQuantity() {
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        prod_spinner.setValueFactory(spin);
    }
    @FXML
    void addBtnP(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setQuantity();
    }
}
