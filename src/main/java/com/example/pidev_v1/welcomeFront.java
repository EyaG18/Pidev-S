package com.example.pidev_v1;

import com.example.pidev_v1.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class welcomeFront {

    private User currentUser;
    FXMLLoader fxmlLoader;
    @FXML
    private Label LabelUser;

    @FXML
    private BorderPane borderpane;

    public void setUser(User user)
    {
        currentUser =user;
        LabelUser.setText(currentUser.getNomuser()+" "+currentUser.getPrenomuser());
        System.out.println("nomcurrentUser"+currentUser.getId_user());
    }

    @FXML
    void gestionAfficherProduits(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("FeedProduitsCoteClients.fxml"));
        Parent view = fxmlLoader.load();
        borderpane.setCenter(view);
        FeedProduitsCoteClientsController controller = fxmlLoader.getController();
        controller.setUser(currentUser);
    }

    @FXML
    void gestionReclamation(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("InterfaceReclamationFront.fxml"));
        Parent view = fxmlLoader.load();
        borderpane.setCenter(view);
        InterfaceReclamationFront controller = fxmlLoader.getController();
        controller.setUser(currentUser);

    }

    @FXML
    void gestionFournisseur(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("AjouterFournisseur.fxml"));
        Parent view = fxmlLoader.load();
        borderpane.setCenter(view);
        AjouterFournisseurController controller = fxmlLoader.getController();
        controller.setUser(currentUser);
    }

    @FXML
    void gestionOffre(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("AfficherOffre.fxml"));
        Parent view = fxmlLoader.load();
        borderpane.setCenter(view);
        AfficherOffreController controller = fxmlLoader.getController();
        controller.setUser(currentUser);
    }

}
