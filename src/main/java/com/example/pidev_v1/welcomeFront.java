package com.example.pidev_v1;

import com.example.pidev_v1.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class welcomeFront {

    @FXML
    private BorderPane borderpane;
     private User currentUser;
    FXMLLoader fxmlLoader;
    @FXML
    private Label LabelUser;

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
        /*StackPane view= FXMLLoader.load(getClass().getResource("InterfaceReclamationFront.fxml"));
        borderpane.setCenter(view);*/
         fxmlLoader = new FXMLLoader(getClass().getResource("InterfaceReclamationFront.fxml"));
        Parent view = fxmlLoader.load();
        borderpane.setCenter(view);
        InterfaceReclamationFront controller = fxmlLoader.getController();
        controller.setUser(currentUser);
    }

}
