package controllers;

import entities.Offre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.util.Optional;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import service.OffreService;
import utils.DataSource;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
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
    public void initialize() {

        AfficherOffre();
    }

    @FXML
    void ajouter(ActionEvent event) {
        LocalDate dateDebut = date_debutTF.getValue();
        LocalDate dateFin = date_finTF.getValue();

        // Check if the dates are selected
        if (dateDebut == null || dateFin == null) {
            showAlert(AlertType.ERROR, "Error", "Invalid Date", "Please select valid start and end dates.");
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
    void modifier(ActionEvent event) {
        // Get the selected offer from the table
        Offre selectedOffre = tableoffre.getSelectionModel().getSelectedItem();

        // Check if an offer is selected
        if (selectedOffre == null) {
            showAlert(AlertType.ERROR, "Error", "No Offer Selected", "Please select an offer to modify.");
            return;
        }

        // Get the existing ID of the selected offer
        int existingId = selectedOffre.getIdOffre();

        // Update the selected offer with new information
        selectedOffre.setId_Produit(Integer.parseInt(id_produitTF.getText())); // Assuming Id_ProduitTF contains the new Id_Produit value
        selectedOffre.setDate_debut(date_debutTF.getValue());
        selectedOffre.setDate_fin(date_finTF.getValue());
        selectedOffre.setReduction(reductionTF.getText());
        selectedOffre.setTitre_Offre(titre_offreTF.getText());

        // Set the existing ID back to the selected offer
        selectedOffre.setIdOffre(existingId);

        // Call the update method in OffreService
        OS.update(selectedOffre);

        // Clear input fields and refresh the table
        clearInputFields();
        AfficherOffre();

        // Optionally, display a success message
        showAlert(AlertType.INFORMATION, "Success", "Offer Modified", "The offer has been successfully modified.");
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
}
