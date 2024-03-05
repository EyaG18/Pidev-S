package com.example.pidev_v1;

import com.example.pidev_v1.entities.Fournisseur;
import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.services.FournisseurService;
//import jakarta.mail.Message;
//import jakarta.mail.MessagingException;
//import jakarta.mail.Session;
//import jakarta.mail.Transport;
//import jakarta.mail.internet.InternetAddress;
//import jakarta.mail.internet.MimeMessage;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import okhttp3.*;

import javafx.scene.control.Alert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
    private ImageView qrCodeImageView;

    @FXML
    void GoToCRM(MouseEvent event) {

    }
    private User currentUser;
    public void setUser(User user)
    {
        currentUser =user;
        System.out.println("nomcurrentUser"+currentUser.getId_user());
    }






    @FXML
    private TextField recherchenom;
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
        /*Fournisseur selectedFournisseur = table.getSelectionModel().getSelectedItem();

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
        }*/
    }

 /*   private void sendEmail(String toEmail) {
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
    }*/


    @FXML
    void rejeter(ActionEvent event) {
        Fournisseur selectedFournisseur = table.getSelectionModel().getSelectedItem();
        if (selectedFournisseur != null) {
            // Delete the selected Fournisseur from the database
            FS.delete(selectedFournisseur);

            // Remove the selected Fournisseur from the table view
            table.getItems().remove(selectedFournisseur);

            // Send SMS to the selected Fournisseur's phone number
            sendSmsToNumeroFournisseur(selectedFournisseur.getNum_fournisseur());

            showAlert(Alert.AlertType.INFORMATION, "Success", "Fournisseur Rejected", "Fournisseur has been rejected and removed from the database.");
        } else {
            // If no fournisseur is selected, show an error message
            showAlert(Alert.AlertType.ERROR, "Error", "No Fournisseur Selected", "Please select a fournisseur to reject.");
        }
    }
    public void sendSmsToNumeroFournisseur(String phoneNumber) {
        try {
            // Infobip API URL
            String url = "https://9lgjvy.api.infobip.com/sms/2/text/advanced";

            // Infobip API credentials
            String apiKey = "92c9a60fd771f042053cf3c0c1e5e0aa-8e4bfda1-98ca-4b32-8f3c-c9cc66e8eb7d";

            // Create OkHttpClient instance
            OkHttpClient client = new OkHttpClient();

            // Create JSON request body
            MediaType mediaType = MediaType.parse("application/json");
            String jsonBody = "{\"messages\":[{\"destinations\":[{\"to\":\"21625911783\"}],\"from\":\"ServiceSMS\",\"text\":\"Your fournisseur has been rejected.\"}]}";

            RequestBody body = RequestBody.create(jsonBody, mediaType);

            // Build HTTP request
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .addHeader("Authorization", "App " + apiKey)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build();

            // Execute HTTP request
            Response response = client.newCall(request).execute();

            // Check response status
            if (response.isSuccessful()) {
                System.out.println("SMS sent successfully to " + phoneNumber);
            } else {
                System.out.println("Failed to send SMS to " + phoneNumber);
            }

            // Close the response body
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
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


    @FXML
    void rechercher(ActionEvent event) {
        // Get the search term from the TextField
        String searchTerm = recherchenom.getText().trim().toLowerCase();

        // Create a filtered list to hold the filtered items
        FilteredList<Fournisseur> filteredList = new FilteredList<>(table.getItems());

        // Set a predicate to filter items based on the search term
        filteredList.setPredicate(fournisseur -> {
            if (searchTerm.isEmpty()) {
                // If the search term is empty, show all items
                return true;
            } else {
                // Otherwise, check if the fournisseur's nom contains the search term
                return fournisseur.getNom_fournisseur().toLowerCase().contains(searchTerm);
            }
        });

        // Wrap the filtered list in a SortedList to enable sorting
        SortedList<Fournisseur> sortedList = new SortedList<>(filteredList);

        // Bind the sorted list to the TableView
        sortedList.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedList);
    }


    @FXML
    void handleSearchInput(KeyEvent event) {
        // Trigger the search when the user types in the search field
        rechercher(null);

        // If the search field is empty, reload the original data into the table
        if (recherchenom.getText().isEmpty()) {
            refreshTable();
        }
    }


    @FXML
    void actualiser(ActionEvent event) {
        refreshTable();
    }



}