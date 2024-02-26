package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import entities.Fournisseur;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import service.FournisseurService;





public class AjouterFournisseurController {


    private final FournisseurService FS =new FournisseurService();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField adresseTF;

    @FXML
    private TextField idproduitTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField numTF;


    @FXML
    void ajouterF(ActionEvent event) {
        // Get data from text fields
        int idProduit = Integer.parseInt(idproduitTF.getText());
        String nom = nomTF.getText();
        String num = numTF.getText();
        String adresse = adresseTF.getText();

        // Create a new Fournisseur object
        Fournisseur fournisseur = new Fournisseur(idProduit, nom, num, adresse);

        // Add the Fournisseur to the service
        FS.add(fournisseur);

        showSuccessMessage();

    }

    private void showSuccessMessage() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Your information has been successfully sent!");
        alert.showAndWait();
    }

    @FXML
    void afficher(ActionEvent event) {
        int idProduit = Integer.parseInt(idproduitTF.getText());
        String nom = nomTF.getText();
        String num = numTF.getText();
        String adresse = adresseTF.getText();

        // Create a new Fournisseur object
        Fournisseur fournisseur = new Fournisseur(idProduit, nom, num, adresse);

        // Call the method in AfficherFournisseurController to update the table
        AfficherFournisseurController afficherController = new AfficherFournisseurController();
        afficherController.updateTableWithFournisseur(fournisseur);
    }





    @FXML
    void initialize() {
        assert adresseTF != null : "fx:id=\"adresseTF\" was not injected: check your FXML file 'AjouterFournisseur.fxml'.";
        assert idproduitTF != null : "fx:id=\"idproduitTF\" was not injected: check your FXML file 'AjouterFournisseur.fxml'.";
        assert nomTF != null : "fx:id=\"nomTF\" was not injected: check your FXML file 'AjouterFournisseur.fxml'.";
        assert numTF != null : "fx:id=\"numTF\" was not injected: check your FXML file 'AjouterFournisseur.fxml'.";

    }

}
