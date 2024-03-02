package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Produit;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageTableCell extends TableCell<Produit,String> {

    private final ImageView imageView;

    public ImageTableCell() {
        this.imageView = new ImageView();
        this.imageView.setFitWidth(100); // Réglez la largeur de l'image (ajustez selon vos besoins)
        this.imageView.setPreserveRatio(true);
        setGraphic(imageView);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    protected void updateItem(String imagePath, boolean empty) {
        super.updateItem(imagePath, empty);

        if (empty || imagePath == null) {
            imageView.setImage(null);
        } else {
            // Chargez l'image depuis le chemin d'accès
            Image image = new Image(imagePath);
            imageView.setImage(image);
        }
    }





















}
