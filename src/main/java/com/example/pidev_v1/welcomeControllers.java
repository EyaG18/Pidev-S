package com.example.pidev_v1;

import com.example.pidev_v1.entities.User;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class welcomeControllers {

    @FXML
    private JFXButton Bcatégories;

    @FXML
    private JFXButton Boffres;

    @FXML
    private JFXButton Bproduits;

    @FXML
    private JFXButton Bproduits1;

    @FXML
    private JFXButton Bproduits11;

    @FXML
    private JFXButton Bproduits111;

    @FXML
    private JFXButton Bproduits1111;

    @FXML
    private JFXButton Breclamations;

    private User currentUser;
    private User selectedUser;
    @FXML
    private Label EmployeSession;

    @FXML
    private BorderPane borderpane;


    @FXML
    private JFXButton btnKPIS;


    @FXML
    void GoToKpisProduits(MouseEvent event) throws IOException {
        StackPane view =  FXMLLoader.load(getClass().getResource("KpisProduitsBack.fxml"));

        borderpane.setCenter(view);

    }

    @FXML
    void GotoAffichageProduits(MouseEvent event) throws IOException {
        StackPane view= FXMLLoader.load(getClass().getResource("AfficherProduitBack.fxml"));

        borderpane.setCenter(view);

    }

    @FXML
    void GotoAffichagesCategories(MouseEvent event) throws IOException {
        StackPane view= FXMLLoader.load(getClass().getResource("DetailsCategorie.fxml"));

        borderpane.setCenter(view);
    }

    @FXML
    void gestionAvis(ActionEvent event) throws IOException {
        AnchorPane view= FXMLLoader.load(getClass().getResource("interfaceAvis.fxml"));

        borderpane.setCenter(view);
    }

    @FXML
    void gestionReclamation(ActionEvent event) throws IOException {
        StackPane view= FXMLLoader.load(getClass().getResource("interfaceReclamation.fxml"));

        borderpane.setCenter(view);
    }

    public void setUser(User user) {
        currentUser = user;
        EmployeSession.setText(currentUser.getNomuser()+" "+currentUser.getPrenomuser());
    }









}
