package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import service.FournisseurService;
import service.OffreService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import entities.Offre; // Import the Offre class if it's in a separate package
import java.util.List;



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

        rlist.add(new Label("Date Debut"), 1, 0);
        rlist.add(new Label("Date Fin"), 2, 0);
        rlist.add(new Label("Reduction"), 3, 0);
        rlist.add(new Label("Titre Offre"), 4, 0);

        int row = 1;
        for (Offre offre : offreList) {
            // Add data to the GridPane

            rlist.add(new Label(offre.getDate_debut().toString()), 1, row);
            rlist.add(new Label(offre.getDate_fin().toString()), 2, row);
            rlist.add(new Label(offre.getReduction()), 3, row);
            rlist.add(new Label(offre.getTitre_Offre()), 4, row);
            row++;
        }
    }
}
