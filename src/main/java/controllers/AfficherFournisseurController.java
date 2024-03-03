package controllers;

import entities.Fournisseur;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.FournisseurService;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
public class AfficherFournisseurController implements Initializable {

    @FXML
    private TableColumn<Fournisseur, String> adresse;

    @FXML
    private TableColumn<Fournisseur, String> id;

    @FXML
    private TableColumn<Fournisseur, String> nom;

    @FXML
    private TableColumn<Fournisseur, String> num;

    @FXML
    private TableView<Fournisseur> table;


    @FXML
    void GoToCRM(MouseEvent event) {

    }

    @FXML
    void GoToCategories(MouseEvent event) {

    }

    @FXML
    void GoToCommandesLivraison(MouseEvent event) {

    }

    @FXML
    void GoToFournisseurs(MouseEvent event) {
        NavigationController.OpenInterfaceAfficherFournisseursBack(event,"/AfficherFournisseur.fxml");

    }

    @FXML
    void GoToOffres(MouseEvent event) {
        NavigationController.OpenInterfaceAjouterOffreBack(event,"/AjouterOffre.fxml");

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

        // Set column for fournisseur information
        nom.setCellValueFactory(new PropertyValueFactory<>("nom_fournisseur"));
        num.setCellValueFactory(new PropertyValueFactory<>("num_fournisseur"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse_fournisseur"));

        // Set cell value factory for existing "Nom Produit" column
        id.setCellValueFactory(cellData -> {
            int productId = cellData.getValue().getId_Produit();
            String productName = FS.getProductNameById(productId); // Retrieve product name by product ID
            return new SimpleStringProperty(productName);
        });
    }


    @FXML
    void accepter(ActionEvent event) {
        Fournisseur selectedFournisseur = table.getSelectionModel().getSelectedItem();

        if (selectedFournisseur != null) {
            // Get the email address of the selected fournisseur
            String emailAddress = selectedFournisseur.getAdresse_fournisseur();

            // Send email only if the email address is not empty
            if (emailAddress != null && !emailAddress.isEmpty()) {
                sendEmail(emailAddress);
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Email Address Not Found",
                        "No email address found for the selected fournisseur.");
            }
        } else {
            // If no fournisseur is selected, show an error message
            showAlert(Alert.AlertType.ERROR, "Error", "No Fournisseur Selected",
                    "Please select a fournisseur to accept.");
        }
    }

    private void sendEmail(String toEmail) {
        // Provide sender's email ID
        String from = "zgolli@grandelation.com";

        // Provide Mailtrap's username
        final String username = "zgolli@grandelation.com";

        // Provide Mailtrap's password
        final String password = "!_@EtqW6da6A";

        // Provide Mailtrap's host address
        String host = "mail.grandelation.com";

        // Configure Mailtrap's SMTP server details
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.port", "465");

        // Create the Session object
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password.toCharArray());

            }
        };
        Session session = Session.getInstance(props);

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);
            // Set From email field
            message.setFrom(new InternetAddress(from));
            // Set To email field
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("aziz.zgolli@esprit.tn"));
            // Set email subject field
            message.setSubject("Welcome to Our Team!");
            // Set the content of the email message
            message.setText("Dear Fournisseur,\n\nWe are pleased to inform you that you have been accepted "
                    + "to be our fournisseur. Welcome to our team!\n\nBest regards,\nYour Company");

            // Send the email message
            Transport.send(message,username,password);
            System.out.println("Email Message Sent Successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
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
