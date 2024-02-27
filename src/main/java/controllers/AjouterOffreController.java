package controllers;

import entities.Offre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.sql.*;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.OffreService;
import utils.DataSource;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;

import javafx.collections.FXCollections;


public class AjouterOffreController {

    private final OffreService OS = new OffreService();

    @FXML
    private TextField id_produitTF;

    @FXML
    private DatePicker date_debutTF;

    @FXML
    private DatePicker date_finTF;

    @FXML
    private TableColumn<Offre, LocalDate> datedebutaff;

    @FXML
    private TableColumn<Offre, LocalDate> datefinaff;

    @FXML
    private TableColumn<Offre, Integer> idproduitaff;

    @FXML
    private TextField reductionTF;

    @FXML
    private TableColumn<Offre, String> reductionaff;

    @FXML
    private TextField titre_offreTF;

    @FXML
    private TableColumn<Offre, String> titreaff;

    @FXML
    private Button ajoutbutt;

    @FXML
    private TableView<Offre> tableoffre;
    @FXML
    private Button modifierButton;

    @FXML
    private Button nextbutt;
    @FXML
    private AnchorPane window;





    @FXML
    void ajouter(ActionEvent event) {
        LocalDate dateDebut = date_debutTF.getValue();
        LocalDate dateFin = date_finTF.getValue();

        // Check if the dates are selected
        if (dateDebut == null || dateFin == null) {
            showAlert(AlertType.ERROR, "Error", "Invalid Date", "Please select valid start and end dates.");
            return;
        }
        String reduction = reductionTF.getText();
        if (!reduction.matches("\\d{1,2}%")) {
            showAlert(AlertType.ERROR, "Error", "Invalid Reduction", "The reduction must be in the format 'XX%' where XX is a number with maximum 2 digits.");
            return;
        }

        int idProduit;
        try {
            idProduit = Integer.parseInt(id_produitTF.getText());
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Erreur", "ID Produit Incorrect", "L'ID produit doit être un entier.");
            return;
        }

        // Check if the ID is valid
        if (!isIdProduitValid(idProduit)) {
            showAlert(AlertType.ERROR, "Error", "Invalid ID Produit", "ID Produit does not exist.");
            return;
        }
        // Create a new Offre object with user input
        Offre newOffre = new Offre(
                Integer.parseInt(id_produitTF.getText()), // Assuming Id_Produit is an integer
                dateDebut,
                dateFin,
                reductionTF.getText(),
                titre_offreTF.getText()
        );

        // Add the new Offre to the database
        OS.add(newOffre);

        // Clear input fields
        clearInputFields();

        // Refresh the table view to display the updated data
        AfficherOffre();
    }
    private Connection conn;

    public boolean isIdProduitValid(int idProduit) {
        String query = "SELECT * FROM produit WHERE Id_Produit = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idProduit);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Returns true if the Id_Produit exists, false otherwise
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return false;
        }

    }


    public ObservableList<Offre> getOffreList() {
        ObservableList<Offre> offreList = FXCollections.observableArrayList();
        DataSource db = new DataSource();
        Connection cnx = db.getCnx();
        String query = "SELECT Id_Produit, date_debut, date_fin,titre_offre,reduction FROM Offre"; // Adjust the SQL query according to your table structure
        Statement st;
        ResultSet rs;
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                int idProduit = rs.getInt("Id_Produit");
                LocalDate dateDebut = rs.getDate("date_debut").toLocalDate();
                LocalDate dateFin = rs.getDate("date_fin").toLocalDate();
                String titreOffre = rs.getString("titre_offre");
                String reduction = rs.getString("reduction");

                // Create Offre object and add it to the list
                Offre offre = new Offre(idProduit, dateDebut, dateFin, reduction, titreOffre);
                offreList.add(offre);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return offreList;
    }
    public void AfficherOffre() {
        ObservableList<Offre> listOffres = getOffreList();
        idproduitaff.setCellValueFactory(new PropertyValueFactory<>("Id_Produit"));
        datedebutaff.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        datefinaff.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        reductionaff.setCellValueFactory(new PropertyValueFactory<>("reduction"));
        titreaff.setCellValueFactory(new PropertyValueFactory<>("titre_Offre"));

        tableoffre.getItems().setAll(listOffres);
    }






    private void clearInputFields() {
        id_produitTF.clear();
        date_debutTF.setValue(null);
        date_finTF.setValue(null);
        reductionTF.clear();
        titre_offreTF.clear();
    }

    @FXML
    void showAlert(AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void modifier(ActionEvent event) {
        try {
            // Get the selected offer from the table
            Offre selectedOffre = tableoffre.getSelectionModel().getSelectedItem();
            if (selectedOffre == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "No Offer Selected", "Please select an offer to modify.");
                return;
            }

            // Get the new attribute values
            String newTitreOffre = titre_offreTF.getText();
            LocalDate newDateDebut = date_debutTF.getValue(); // Assuming you have a DatePicker named date_debutPicker
            LocalDate newDateFin = date_finTF.getValue(); // Assuming you have a DatePicker named date_finPicker
            String newReduction = reductionTF.getText(); // Assuming you have a TextField named reductionTF
            int newIdProduit = Integer.parseInt(id_produitTF.getText()); // Assuming you have a TextField named id_produitTF for the new Id_Produit value

            // Get the old attribute values
            String oldTitreOffre = selectedOffre.getTitre_Offre();

            // Update the offre with the new attribute values
            OS.updateOffreByTitreOffre(oldTitreOffre, newTitreOffre, newDateDebut, newDateFin, newReduction, newIdProduit);

            // Show success message
            showAlert(Alert.AlertType.INFORMATION, "Succès !", "Offre modifiée avec succès !", "");

            // Refresh the table view
            AfficherOffre();

            // Clear the text field
            titre_offreTF.clear();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur !", "Une erreur s'est produite : " + e.getMessage(), "");
        }
    }


    @FXML
    public void initialize() {


        idproduitaff.setCellValueFactory(new PropertyValueFactory<>("id_Produit"));
        datedebutaff.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        datefinaff.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        reductionaff.setCellValueFactory(new PropertyValueFactory<>("reduction"));
        titreaff.setCellValueFactory(new PropertyValueFactory<>("titre_Offre"));
        AfficherOffre();


    }





    @FXML
    void supprimer(ActionEvent event) {
        // Get the selected Offre from the TableView
        Offre selectedOffre = tableoffre.getSelectionModel().getSelectedItem();

        // Check if an Offre is selected
        if (selectedOffre == null) {
            showAlert(AlertType.ERROR, "Error", "No Offre Selected", "Please select an Offre to delete.");
            return;
        }

        // Confirm deletion with a dialog
        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation Dialog");
        confirmationAlert.setHeaderText("Delete Offre");
        confirmationAlert.setContentText("Are you sure you want to delete the selected Offre?");

        // Get the result of the confirmation dialog
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        // Check if the user confirmed deletion
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Call the delete method in OffreService to delete the selected Offre
            OS.delete(selectedOffre);

            // Refresh the TableView after deletion
            AfficherOffre();

            // Show a success message
            showAlert(AlertType.INFORMATION, "Success", "Offre Deleted", "The selected Offre");



        }
    }
    @FXML
    void front(ActionEvent event) {
        try {
            // Load the FXML file for the "AfficherOffre" window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherOffre.fxml"));
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
        }
    }

}
