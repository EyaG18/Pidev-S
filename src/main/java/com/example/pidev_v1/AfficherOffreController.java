package com.example.pidev_v1;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.pidev_v1.entities.Offre;
import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.services.OffreService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AfficherOffreController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane rlist;
    private final OffreService OS = new OffreService();
    @FXML
    void initialize() {
        OffreService offreService = new OffreService();

        // Fetch data from the database using OffreService
        List<Offre> offreList = offreService.readAll();

        // Populate the UI components with the fetched data
        populateTableView(offreList);
    }
    private User currentUser;
    public void setUser(User user)
    {
        currentUser =user;
        System.out.println("nomcurrentUser"+currentUser.getId_user());
    }
    private void populateTableView(List<Offre> offreList) {
        // Clear existing content from the GridPane
        rlist.getChildren().clear();

        // Add column headers
        Font headerFont = Font.font("System", FontWeight.BOLD, 14); // You can adjust the font size as needed

        // Add column headers
        Label dateDebutLabel = new Label("Date Debut");
        dateDebutLabel.setFont(headerFont);
        rlist.add(dateDebutLabel, 1, 0);

        Label dateFinLabel = new Label("Date Fin");
        dateFinLabel.setFont(headerFont);
        rlist.add(dateFinLabel, 2, 0);

        Label reductionLabel = new Label("Reduction");
        reductionLabel.setFont(headerFont);
        rlist.add(reductionLabel, 3, 0);

        Label titreOffreLabel = new Label("Titre Offre");
        titreOffreLabel.setFont(headerFont);
        rlist.add(titreOffreLabel, 4, 0);

        Label prixAfterReductionLabel = new Label("Prix After Reduction");
        prixAfterReductionLabel.setFont(headerFont);
        rlist.add(prixAfterReductionLabel, 5, 0); // Add a new column for Prix After Reduction

        int row = 1;
        for (Offre offre : offreList) {
            // Add data to the GridPane
            rlist.add(new Label(offre.getDate_debut().toString()), 1, row);
            rlist.add(new Label(offre.getDate_fin().toString()), 2, row);
            rlist.add(new Label(offre.getReduction()), 3, row);
            rlist.add(new Label(offre.getTitre_Offre()), 4, row);

            // Calculate and display Prix After Reduction
            float prixAfterReduction = OS.getPrixAfterReduction(offre.getId_Produit()); // Assuming OffreService method is implemented correctly
            Label prixAfterReductionValueLabel = new Label(String.valueOf(prixAfterReduction));
            rlist.add(prixAfterReductionValueLabel, 5, row);

            row++;
        }
    }


}