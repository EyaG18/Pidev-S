package com.example.pidev_v1;
 import com.example.pidev_v1.entities.Livraison;
 import com.example.pidev_v1.entities.User;
 import com.example.pidev_v1.services.LivraisonService;
 import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BackLivraison implements Initializable {

    @FXML
    private TableView<Livraison> Table_livraison;

    @FXML
    private TableColumn<Livraison, Integer> Reference;

    @FXML
    private TableColumn<Livraison, String> client;

    @FXML
    private TableColumn<Livraison, String> Date;

    @FXML
    private TableColumn<Livraison, Status> Status;

    @FXML
    private TableColumn<Livraison, String> ModeLivraison;
    @FXML
    private MenuButton StatusModifier;

    @FXML
    private Button Ajouter;

    @FXML
    private Button supprimer;
    @FXML
    private Button BrnFournisseursAppelsOffres;

    @FXML
    private Button BtnCRM;

    @FXML
    private Button BtnCategories;

    @FXML
    private Button BtnProduits;

    @FXML
    private Button CommandesLivraisosBTN;

    private LivraisonService lv = new LivraisonService();

    Connection cnx;
    PreparedStatement pre;

    @FXML
    private void changeStatusToAttente(ActionEvent event) {
        // Change the status of the selected commande to "EN ATTENTE"
        ModifierStatus("EN ATTENTE");
    }

    @FXML
    private void changeStatusToTraitee(ActionEvent event) {
        // Change the status of the selected commande to "TRAITEE"
        ModifierStatus("TRAITEE");
    }

    @FXML
    private void changeStatusToAnnulee(ActionEvent event) {
        // Change the status of the selected commande to "ANNULEE"
        ModifierStatus("ANNULEE");
    }

    private void ModifierStatus(String newStatus) {
        // Your logic to modify the status of a delivery
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    void Supprimer(ActionEvent event) {
        Livraison livraison = Table_livraison.getSelectionModel().getSelectedItem();

        if (livraison != null) {
            try {
                // Supprimer la commande de la base de données à l'aide du service
                lv.Delete(livraison);
                // Retirer la commande de la TableView
                Table_livraison.getItems().remove(Table_livraison.getSelectionModel().getSelectedIndex());

                // Afficher un message de succès
                showAlert("Livraison " + livraison.getReference() + " supprimée avec succès!");
            } catch (Exception ex) {
                Logger.getLogger(BackLivraison.class.getName()).log(Level.SEVERE, null, ex);
                // Afficher un message d'erreur
                showAlert("Erreur lors de la suppression de la commande.");
            }
        } else {
            showAlert("Veuillez sélectionner une commande à supprimer.");
        }
    }

    @FXML
    void Ajouter(ActionEvent event) {

    }

    @FXML
    void reclamer(MouseEvent event) {

    }

    @FXML
    void GoToCategories(MouseEvent event) {

    }

    @FXML
    void GoToCommandesLivraison(MouseEvent event) {

    }

    @FXML
    void GoToFournieusseursOffere(ActionEvent event) {

    }

    @FXML
    void GoToProducts(MouseEvent event) {

    }

    @FXML
    void GoToUsers(MouseEvent event) {

    }

    @FXML
    void GotoCRM(ActionEvent event) {

    }
    public ObservableList<Livraison> getlistelivraison() throws SQLException {
        ObservableList<Livraison> livraisons = FXCollections.observableArrayList();
        String requete = "SELECT * FROM livraison";
        PreparedStatement ste = cnx.prepareStatement(requete);
        try {
            ste= (PreparedStatement) cnx.createStatement();
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                Livraison l = new Livraison();
                l.setReference(rs.getInt("Reference"));
                l.setId_commande(rs.getInt("id_commande"));
                l.setId_user(rs.getInt("id_user"));
                l.setAdrUser(rs.getString("AdrUser"));
                //l.setStatus(Status(rs.getString("Status_livraison")));


                 l.setStatus(com.example.pidev_v1.entities.Status.valueOf(rs.getString("Status_livraison").valueOf(Status))); // Assuming you have Status as Enum
                l.setDate_livraison(rs.getDate("date_livraison").toLocalDate());
                l.setPrix_livraison(rs.getFloat("prix_livraison"));
                livraisons.add(l);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return livraisons;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Livraison> deliveries = lv.Afficher(); // Assuming lv provides deliveries
        Table_livraison.setItems(deliveries);

        // Set cell value factories for table columns
        Reference.setCellValueFactory(new PropertyValueFactory<>("Reference"));

        // Define a separate method to retrieve user name (assuming User has a toString() method)
        Function<Livraison, String> getUserName = delivery -> {
            User user = lv.getUserById(delivery.getId_user());
            return user != null ? user.toString() : "";
        };

        client.setCellValueFactory(cellData -> {
            Livraison livraison = cellData.getValue();
            User user = lv.getUserById(livraison.getId_user());
            return new SimpleStringProperty(((User) user).toString());
        });
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        Status.setCellValueFactory(new PropertyValueFactory<>("statut")); // Assuming "statut" is the correct property name
        ModeLivraison.setCellValueFactory(new PropertyValueFactory<>("livrable"));

        // Add listener to the TableView's selection model
        Table_livraison.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                System.out.println("Selected Delivery ID: " + newSelection.getId_livraison()); // Assuming id_livraison exists
            }
        });

        // Check if menu items have already been added
        if (StatusModifier.getItems().isEmpty()) {
            // Add menu items and event handlers
            MenuItem enAttenteMenuItem = new MenuItem("EN ATTENTE");
            MenuItem traiteeMenuItem = new MenuItem("TRAITEE");
            MenuItem annuleeMenuItem = new MenuItem("ANNULEE");

            enAttenteMenuItem.setOnAction(event -> ModifierStatus("EN ATTENTE"));
            traiteeMenuItem.setOnAction(event -> ModifierStatus("TRAITEE"));
            annuleeMenuItem.setOnAction(event -> ModifierStatus("ANNULEE"));

            StatusModifier.getItems().addAll(enAttenteMenuItem, traiteeMenuItem, annuleeMenuItem);
        }
    }



}

