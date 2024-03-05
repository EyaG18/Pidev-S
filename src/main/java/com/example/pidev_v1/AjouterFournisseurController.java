package com.example.pidev_v1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.example.pidev_v1.entities.Fournisseur;
import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.services.FournisseurService;
import com.example.pidev_v1.tools.MyDataBase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;



import javafx.fxml.FXMLLoader;


import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.stage.Stage;
import java.io.IOException;


import javafx.scene.Parent;


public class AjouterFournisseurController implements Initializable {

    private User currentUser;   public void setUser(User user)
    {
        currentUser =user;
        System.out.println("nomcurrentUser"+currentUser.getId_user());
    }

    private final FournisseurService FS =new FournisseurService();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField adresseTF;

    @FXML
    private ComboBox<String> idproduitTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField numTF;

    @FXML
    private Button BrnFournisseursAppelsOffres;

    @FXML
    private Button BtnCRM;

    @FXML
    private Button BtnCategories;

    @FXML
    private Button BtnOffres;

    @FXML
    private Button BtnProduits;

    @FXML
    private Button CommandesLivraisosBTN;
    @FXML
    private Button UsersBoutons;

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
    void GoToFournieusseursOffere(ActionEvent event) {

    }
    @FXML
    void GoToFournisseurs(MouseEvent event) {

    }
    @FXML
    void GoToOffres(MouseEvent event) {

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
    private ImageView qrCodeImageView;


    @FXML
    void ajouterF(ActionEvent event) {
        // Get data from text fields
        String nom = nomTF.getText();
        String num = numTF.getText();
        String adresse = adresseTF.getText();

        // Validate the length and format of the supplier number
        if (num.length() != 8) {
            showAlert(AlertType.ERROR, "Erreur", "Numéro Fournisseur Incorrect", "Le numéro du fournisseur doit avoir une longueur de 8 chiffres.");
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

        // Get the selected product name from the ComboBox
        String productName = idproduitTF.getValue();
        if (productName == null) {
            showAlert(AlertType.ERROR, "Erreur", "Produit non sélectionné", "Veuillez sélectionner un produit.");
            return;
        }

        // Get the product ID based on its name
        int productId = FS.GetProductIDbyName(productName);

        if (productId == -1) {
            showAlert(AlertType.ERROR, "Erreur", "Produit non trouvé", "Le produit sélectionné n'existe pas.");
            return;
        }

        // Create a new Fournisseur object
        Fournisseur fournisseur = new Fournisseur(productId, nom, num, adresse);

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
    @FXML
    public void initialize(URL url , ResourceBundle rb)
    {

        DisplayCategoriesInComboBox();
        QRCodeGenerator qrCodeGenerator = new QRCodeGenerator();

        try {
            // Call displayQRCode using the instance
            qrCodeImageView.setImage(qrCodeGenerator.new QRCodeDisplay().displayQRCode("https://www.facebook.com/profile.php?id=61555788222917", 200, 200));
        } catch (IOException | WriterException e) {
            e.printStackTrace();
        }
    }

    public void DisplayCategoriesInComboBox() {
        MyDataBase dataSource = new MyDataBase();
        Connection connection = dataSource.getCnx();

        // Clear ComboBox before adding new items
        idproduitTF.getItems().clear();

        try {
            String query = "SELECT NomP FROM produit";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String productName = resultSet.getString("NomP");
                // Add product name to ComboBox
                idproduitTF.getItems().add(productName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public class QRCodeGenerator {

        public static byte[] generateQRCodeImage(String text, int width, int height) throws WriterException, IOException {
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.MARGIN, 1);
            BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            return outputStream.toByteArray();
        }

        public class QRCodeDisplay {

            public static Image displayQRCode(String text, int width, int height) throws IOException, WriterException {
                byte[] qrCodeImage = QRCodeGenerator.generateQRCodeImage(text, width, height);
                Image qrCode = new Image(new ByteArrayInputStream(qrCodeImage));
                return qrCode;}
   /*
    public void generateAndDisplayQRCode(String data) {
        // Generate the QR code image as a byte array
        ByteArrayOutputStream out = QRCode.from(data).to(ImageType.PNG).stream();

        // Convert byte array to JavaFX Image
        InputStream in = new ByteArrayInputStream(out.toByteArray());
        Image image = new Image(in);

        // Set the generated QR code image to the ImageView
        qrCodeImageView.setImage(image);
    }
}*/

        }}}
