package com.example.pidev_v1;

import com.example.pidev_v1.entities.User;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeFront implements Initializable {

    User user;
    User currentUser;

    @FXML
    private JFXButton FeedProduit;

    @FXML
    private Label LabelUser;

    @FXML
    private BorderPane borderpane;

    @FXML
    private JFXButton donnezReclamation;


    @FXML
    FXMLLoader fxmlLoader;






    FeedProduitsCoteClientsController fs = new FeedProduitsCoteClientsController();

    @FXML
    void gestionAfficherProduits(ActionEvent event) throws IOException {
        /*BorderPane view =  FXMLLoader.load(getClass().getResource("FeedProduitsCoteClients.fxml"));
        borderpane.setCenter(view);*/
        fxmlLoader = new FXMLLoader(getClass().getResource("FeedProduitsCoteClients.fxml"));
        Parent view = fxmlLoader.load();
        borderpane.setCenter(view);
        FeedProduitsCoteClientsController controller = fxmlLoader.getController();
        controller.setUser(currentUser);
    }

    @FXML
    void gestionReclamation(ActionEvent event) {

    }

    @FXML
    void GoToVotrePanier(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(getClass().getResource("PanierClient.fxml"));
        Parent view = fxmlLoader.load();
        borderpane.setCenter(view);
        PanierClient controller = fxmlLoader.getController();
        controller.setUser(currentUser);
    }


    public void setUser(User user) {
        currentUser = user;
        LabelUser.setText(currentUser.getNomuser()+" "+currentUser.getPrenomuser());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //fs.AfficherProduitsClients();

    }
}
