package com.example.pidev_v1;

import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.services.MyListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

public class ItemProduct {

    @FXML
    private ImageView ImgProduct;

    @FXML
    private Label NameProductItemLabel;

    @FXML
    private Label PriceProductItem;


    private Produit produit;
    private MyListener myListener;


    public void setDataProducts(Produit produit, MyListener myListener) throws FileNotFoundException {
        this.produit = produit;
        this.myListener = myListener;
        InputStream imageStream = new FileInputStream(produit.getImageP());
        Image image = new Image(imageStream);
        // Redimensionner l'image à une largeur de 153 pixels et une hauteur de 148 pixels
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(153);
        imageView.setFitHeight(148);
        ImgProduct.setImage(imageView.snapshot(null, null));
        NameProductItemLabel.setText(produit.getNomP());
        PriceProductItem.setText(String.valueOf(produit.getPrixP()));
    }

    @FXML
    void btnAjouterauPanier(MouseEvent event) {

    }









}