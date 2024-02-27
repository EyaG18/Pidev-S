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



import javafx.fxml.FXMLLoader;


import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.stage.Stage;
import java.io.IOException;


import javafx.scene.Parent;

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

        int idProduit;
        try {
            idProduit = Integer.parseInt(idproduitTF.getText());
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Erreur", "ID Produit Incorrect", "L'ID produit doit être un entier.");
            return;
        }

        String nom = nomTF.getText();
        String num = numTF.getText();
        String adresse = adresseTF.getText();

        // Validate the length and format of the supplier number
        if (num.length() != 8) {
            showAlert(AlertType.ERROR, "Erreur", "Numéro Fournisseur Incorrect", "Le numéro du fournisseur doit avoir une longueur de 7 chiffres.");
            return;
        }
        // Check if the number contains only digits
        if (!num.matches("\\d+")) {
            showAlert(AlertType.ERROR, "Erreur", "Numéro Fournisseur Incorrect", "Le numéro du fournisseur doit contenir uniquement des chiffres.");
            return;
        }
        if (nom.matches(".*\\d+.*")) {
            showAlert(AlertType.ERROR, "Erreur", "Nom Fournisseur Incorrect", "Le nom du fournisseur ne doit pas contenir de chiffres.");
            return;
        }

        // Create a new Fournisseur object
        Fournisseur fournisseur = new Fournisseur(idProduit, nom, num, adresse);

        // Add the Fournisseur to the service
        FS.add(fournisseur);

        showSuccessMessage();
    }

    public void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();}
    private void showSuccessMessage() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Your information has been successfully sent!");
        alert.showAndWait();
    }

    @FXML
    void afficher(ActionEvent event) {
        try {
            // Load the FXML file for the "Afficher Fournisseur" window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherFournisseur.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene scene = new Scene(root);

            // Get the stage (window) from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle loading error
        }}





    @FXML
    void initialize() {
        assert adresseTF != null : "fx:id=\"adresseTF\" was not injected: check your FXML file 'AjouterFournisseur.fxml'.";
        assert idproduitTF != null : "fx:id=\"idproduitTF\" was not injected: check your FXML file 'AjouterFournisseur.fxml'.";
        assert nomTF != null : "fx:id=\"nomTF\" was not injected: check your FXML file 'AjouterFournisseur.fxml'.";
        assert numTF != null : "fx:id=\"numTF\" was not injected: check your FXML file 'AjouterFournisseur.fxml'.";

    }

}
