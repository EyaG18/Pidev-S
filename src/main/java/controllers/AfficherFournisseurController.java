package controllers;

import entities.Fournisseur;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.FournisseurService;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
public class AfficherFournisseurController implements Initializable {

    @FXML
    private TableColumn<Fournisseur, String> adresse;

    @FXML
    private TableColumn<Fournisseur, Integer> id;

    @FXML
    private TableColumn<Fournisseur, String> nom;

    @FXML
    private TableColumn<Fournisseur, String> num;

    @FXML
    private TableView<Fournisseur> table;

    @FXML
    private TextField adrTF;
    private Fournisseur selectedFournisseur; // To store the selected Fournisseur
    private final FournisseurService FS = new FournisseurService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedFournisseur = newSelection;
                adrTF.setText(selectedFournisseur.getAdresse_fournisseur());
            }
        });

        refreshTable();
    }

    public void addFournisseur(Fournisseur fournisseur) {
        // Process the fournisseur object, for example, add it to the table
        table.getItems().add(fournisseur);
    }

    public void updateTableWithFournisseur(Fournisseur fournisseur) {
        // Add the fournisseur to the table
        table.getItems().add(fournisseur);
    }

    public void refreshTable() {
        ObservableList<Fournisseur> list = FS.readAll();
        table.setItems(list);

        id.setCellValueFactory(new PropertyValueFactory<>("Id_Produit"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom_fournisseur"));
        num.setCellValueFactory(new PropertyValueFactory<>("num_fournisseur"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse_fournisseur"));
    }

    @FXML
    void accepter(ActionEvent event) {
        Fournisseur selectedFournisseur = table.getSelectionModel().getSelectedItem();

        if (selectedFournisseur != null) {
            // Add the selected fournisseur to the database
            FS.add(selectedFournisseur);

            // Show a success message
            showAlert(Alert.AlertType.INFORMATION, "Success", "Fournisseur Accepted", "Fournisseur has been accepted and added to the database.");
        } else {
            // If no fournisseur is selected, show an error message
            showAlert(Alert.AlertType.ERROR, "Error", "No Fournisseur Selected", "Please select a fournisseur to accept.");
        }

    }

    @FXML
    void rejeter(ActionEvent event) {
        Fournisseur selectedFournisseur = table.getSelectionModel().getSelectedItem();
        if (selectedFournisseur != null) {
            // Delete the selected Fournisseur from the database
            FS.delete(selectedFournisseur);

            // Remove the selected Fournisseur from the table view
            table.getItems().remove(selectedFournisseur);

            showAlert(Alert.AlertType.INFORMATION, "Success", "Fournisseur Rejected", "Fournisseur has been rejected and removed from the database.");
        } else {
            // If no fournisseur is selected, show an error message
            showAlert(Alert.AlertType.ERROR, "Error", "No Fournisseur Selected", "Please select a fournisseur to reject.");
        }
    }


    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
    @FXML
    void modifier(ActionEvent event) {
        if (selectedFournisseur != null) {
            String newAdresse = adrTF.getText(); // Get the modified adresse from the TextField
            selectedFournisseur.setAdresse_fournisseur(newAdresse); // Update the adresse of the selected Fournisseur
            table.refresh(); // Refresh the TableView to reflect the changes
        }

    }
}
