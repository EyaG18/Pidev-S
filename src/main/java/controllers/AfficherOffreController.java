package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import service.FournisseurService;
import service.OffreService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import entities.Offre; // Import the Offre class if it's in a separate package
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


        int row = 1;
        for (Offre offre : offreList) {
            // Add data to the GridPane
            rlist.add(new Label(offre.getDate_debut().toString()), 1, row);
            rlist.add(new Label(offre.getDate_fin().toString()), 2, row);
            rlist.add(new Label(offre.getReduction()), 3, row);
            rlist.add(new Label(offre.getTitre_Offre()), 4, row);

            // Fetch image path for the current Offre


            row++;
        }
    }

}
