package com.example.pidev_v1;

import com.example.pidev_v1.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class welcomeControllers {

    private User currentUser;
    FXMLLoader fxmlLoader;
    @FXML
    private Label LabelUser;


    @FXML
    private ImageView imageUser;
    @FXML
    private BorderPane borderpane;

    public void setUser(User user)
    {
        currentUser =user;
        LabelUser.setText(currentUser.getNomuser()+" "+currentUser.getPrenomuser());
        System.out.println("nomcurrentUser"+currentUser.getId_user());
        if (!user.getImage().isEmpty()) {
            String imagePath = "/upload/" + user.getImage(); // Replace this with the path to your image in resources
            InputStream inputStream = getClass().getResourceAsStream(imagePath);
            if (inputStream != null) {
                Image image = new Image(inputStream);
                imageUser.setImage(image);
            } else {
                // Handle the case when the resource stream is null
                System.err.println("Resource stream is null for image: " + imagePath);
                // Optionally, you can set a default image or display an error message
            }
        } else {
            // Handle the case when the user's image path is empty
            System.err.println("Image path is empty for user: " + currentUser.getNomuser());
            // Optionally, you can set a default image or display an error message
        }
    }
    @FXML
    void gestion_user(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("dashboardAdmin.fxml"));
        Parent view = fxmlLoader.load();
        borderpane.setCenter(view);
        dashboardAdmin controller = fxmlLoader.getController();
        controller.setUser(currentUser);
    }

    @FXML
    void gestionAvis(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("interfaceAvis.fxml"));
        Parent view = fxmlLoader.load();
        borderpane.setCenter(view);
        InterfaceAvisController controller = fxmlLoader.getController();
        controller.setUser(currentUser);
    }

    @FXML
    void gestionReclamation(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("interfaceReclamation.fxml"));
        Parent view = fxmlLoader.load();
        borderpane.setCenter(view);
        InterfaceReclamationController controller = fxmlLoader.getController();
        controller.setUser(currentUser);
    }

    @FXML
    void gestionProduits(ActionEvent event) {

    }
    @FXML
    void gestionFournisseurs(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("AfficherFournisseur.fxml"));
        Parent view = fxmlLoader.load();
        borderpane.setCenter(view);
        AfficherFournisseurController controller = fxmlLoader.getController();
        controller.setUser(currentUser);
    }
    @FXML
    void gestionOffres(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("AjouterOffre.fxml"));
        Parent view = fxmlLoader.load();
        borderpane.setCenter(view);
        AjouterOffreController controller = fxmlLoader.getController();
        controller.setUser(currentUser);
    }

    @FXML
    void gestionLivraisons(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("BackLivraison.fxml"));
        Parent view = fxmlLoader.load();
        borderpane.setCenter(view);
        BackLivraison controller = fxmlLoader.getController();
        controller.setUser(currentUser);

    }


    @FXML
    void gestionCommandes(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("BackCommandeLivraisonFXML.fxml"));
        Parent view = fxmlLoader.load();
        borderpane.setCenter(view);
        BackCommandeLivraison controller = fxmlLoader.getController();
        controller.setUser(currentUser);


    }

}
