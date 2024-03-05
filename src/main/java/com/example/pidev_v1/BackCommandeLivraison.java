package com.example.pidev_v1;

import com.example.pidev_v1.HelloApplication;
import com.example.pidev_v1.entities.Commande;
import com.example.pidev_v1.entities.Status;
import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.services.CommandeService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BackCommandeLivraison implements Initializable {

    private User currentUser;


    @FXML
    private BorderPane borderpane;

    public void setUser(User user)
    {
        currentUser =user;
        System.out.println("nomcurrentUser"+currentUser.getId_user());
    }

    @FXML
    private TextField Recherche;


    @FXML
    private TableColumn<Commande, Float> prix_total;

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

    @FXML
    private TableColumn<Commande, LocalDate> Date;

    @FXML
    private TableColumn<Commande, Boolean> ModeLivraison;

    @FXML
    private TableColumn<Commande, Integer> Reference;

    @FXML
    private MenuButton StatusModifier;

    @FXML
    private TableColumn<Commande, Status> Status_com;

    @FXML
    private TableView<Commande> Table_commande;

    @FXML
    private Button UsersBoutons;

    @FXML
    private TableColumn<Commande, String> client;
    @FXML
    private Button stats;


    @FXML
    private Button supprimer;

    private PreparedStatement pre;
    private Connection cnx;

    private CommandeService cs = new CommandeService();

    @FXML
    void actualiser(ActionEvent event) {
        refreshTable();
    }

    public void refreshTable() {
        ObservableList<Commande> list = cs.Afficher();
        Table_commande.setItems(list);

        Reference.setCellValueFactory(new PropertyValueFactory<>("Reference"));
        client.setCellValueFactory(cellData -> {
            Commande commande = cellData.getValue();
            User user = cs.getUserById(commande.getId_user());
            return new SimpleStringProperty(user.toString());
        });
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        Status_com.setCellValueFactory(new PropertyValueFactory<>("statut"));
        prix_total.setCellValueFactory(new PropertyValueFactory<>("Prix_total"));
        ModeLivraison.setCellValueFactory(new PropertyValueFactory<>("livrable"));
        ModeLivraison.setCellFactory(column -> {
            return new TableCell<Commande, Boolean>() {
                @Override
                protected void updateItem(Boolean livrable, boolean empty) {
                    super.updateItem(livrable, empty);

                    if (empty || livrable == null) {
                        setText("");
                    } else {
                        if (livrable) {
                            setText("À domicile");
                        } else {
                            setText("Retrait en magasin");
                        }
                    }
                }
            };
        });
    }

    @FXML
    void handleSearchInput(KeyEvent event) {
        rechercher(null);
    }

    @FXML
    void rechercher(ActionEvent event) {
        // Get the search term from the TextField
        String searchTerm = Recherche.getText().trim().toLowerCase();

        // Create a filtered list to hold the filtered items
        FilteredList<Commande> filteredList = new FilteredList<>(Table_commande.getItems());

        // Set a predicate to filter items based on the search term
        filteredList.setPredicate(commande -> {
            if (searchTerm.isEmpty()) {
                // If the search term is empty, show all items
                return true;
            } else {
                // Otherwise, check if the commande's status contains the search term
                return commande.getStatut().toString().toUpperCase().contains(searchTerm);
            }
        });

        // Wrap the filtered list in a SortedList to enable sorting
        SortedList<Commande> sortedList = new SortedList<>(filteredList);

        // Bind the sorted list to the TableView
        sortedList.comparatorProperty().bind(Table_commande.comparatorProperty());
        Table_commande.setItems(sortedList);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private void ModifierStatus(String newStatus) {
        Commande commande = Table_commande.getSelectionModel().getSelectedItem();

        if (commande != null) {
            try {
                // Modifier le statut de la commande dans la base de données à l'aide du service
                cs.Modify(commande, newStatus);

                // Convert the String status to Status enum
                Status statusEnum = Status.valueOf(newStatus);

                // Modifier le statut de la commande dans la TableView
                Table_commande.getItems().get(Table_commande.getSelectionModel().getSelectedIndex()).setStatut(statusEnum);
                // Refresh the TableView to reflect the changes
                Table_commande.refresh();
                // Afficher un message de succès
                showAlert("Le statut de la commande " + commande.getReference() + " a été modifié en " + newStatus + ".");
            } catch (Exception ex) {
                Logger.getLogger(BackCommandeLivraison.class.getName()).log(Level.SEVERE, null, ex);
                // Afficher un message d'erreur
                showAlert("Erreur lors de la modification du statut de la commande.");
            }
        } else {
            showAlert("Veuillez sélectionner une commande pour modifier son statut.");
        }
    }



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
    @FXML
    void Ajouter(ActionEvent event) {

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



    @FXML
    void Supprimer(ActionEvent event) {
        Commande commande = Table_commande.getSelectionModel().getSelectedItem();

        if (commande != null) {
            try {
                // Supprimer la commande de la base de données à l'aide du service
                cs.Delete(commande);
                // Retirer la commande de la TableView
                Table_commande.getItems().remove(Table_commande.getSelectionModel().getSelectedIndex());

                // Afficher un message de succès
                showAlert("Commande " + commande.getReference() + " supprimée avec succès!");
            } catch (Exception ex) {
                Logger.getLogger(BackCommandeLivraison.class.getName()).log(Level.SEVERE, null, ex);
                // Afficher un message d'erreur
                showAlert("Erreur lors de la suppression de la commande.");
            }
        } else {
            showAlert("Veuillez sélectionner une commande à supprimer.");
        }
    }




    @FXML
    void modiferStatusChoisi(ActionEvent event) {

    }

    @FXML
    void reclamer(MouseEvent event) {

    }


    // Méthode initialize() dans le contrôleur
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Commande> list = cs.Afficher();

        Table_commande.setItems(list);

        Reference.setCellValueFactory(new PropertyValueFactory<>("Reference"));
        client.setCellValueFactory(cellData -> {
            Commande commande = cellData.getValue();
            User user = cs.getUserById(commande.getId_user());
            return new SimpleStringProperty(user.toString());
        });
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        Status_com.setCellValueFactory(new PropertyValueFactory<>("statut"));
        prix_total.setCellValueFactory(new  PropertyValueFactory<>("Prix_total"));
        ModeLivraison.setCellValueFactory(new PropertyValueFactory<>("livrable"));
        ModeLivraison.setCellFactory(column -> {
            return new TableCell<Commande, Boolean>() {
                @Override
                protected void updateItem(Boolean livrable, boolean empty) {
                    super.updateItem(livrable, empty);

                    if (empty || livrable == null) {
                        setText("");
                    } else {
                        if (livrable) {
                            setText("À domicile");
                        } else {
                            setText("Retrait en magasin");
                        }
                    }
                }
            };
        });


        // Add a listener to the TableView's selection model
        Table_commande.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Print the ID of the selected item
                System.out.println("Selected Commande ID: " + newSelection.getId_commande());
            }
        });

        // Check if the MenuItems have already been added
        if (StatusModifier.getItems().isEmpty()) {
            // Add event handlers for the menu items to change status
            MenuItem attenteMenuItem = new MenuItem("EN ATTENTE");
            MenuItem traiteeMenuItem = new MenuItem("TRAITEE");
            MenuItem annuleeMenuItem = new MenuItem("ANNULEE");

            attenteMenuItem.setOnAction(event -> ModifierStatus("EN ATTENTE"));
            traiteeMenuItem.setOnAction(event -> ModifierStatus("TRAITEE"));
            annuleeMenuItem.setOnAction(event -> ModifierStatus("ANNULEE"));

            StatusModifier.getItems().addAll(attenteMenuItem, traiteeMenuItem, annuleeMenuItem);
        }


    }
    @FXML
    void stats(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PieChart.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) Table_commande.getScene().getWindow();
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }



}