package com.example.pidev_v1;

import com.example.pidev_v1.entities.Reclamation;
import com.example.pidev_v1.entities.Reponse;
import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.services.ReclamationService;
import com.example.pidev_v1.services.ReponseServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class InterfaceReclamationFront implements Initializable {
    ReclamationService sr=new ReclamationService();
    @FXML
    private JFXButton ajouterB;

    @FXML
    private TableColumn<Reclamation, Date> dateRC2;

    @FXML
    private TableColumn<Reclamation, String> descriptionC4;

    @FXML
    private TextField descriptionTF;

    @FXML
    private TableView<Reclamation> table_reclamation;

    @FXML
    private TableColumn<Reclamation, String> statuC3;

    @FXML
    private JFXButton supprimerB;

    @FXML
    private TableColumn<Reclamation, String> typeC1;

    @FXML
    private JFXComboBox<String> typeCB;
    private User currentUser;


    public void setUser(User user)
    {
        currentUser =user;

        System.out.println("nomcurrentUser5555555/***/"+currentUser.getId_user());
    }
    public void refreshtable_reclamation() {
        table_reclamation.getItems().clear();
        table_reclamation.getItems().addAll(sr.readAll());
    }



    @FXML
    void afficherreclamationNontraite(ActionEvent event) {
        ObservableList<Reclamation> list = sr.readAllNontraite(currentUser.getId_user());
        table_reclamation.setItems(list);
        typeC1.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("nom_client"));
        dateRC2.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("date_reclamation"));
        statuC3.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("statu_reclamation"));
        descriptionC4.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("description"));
    }

    @FXML
    void afficherreclamationtraite(ActionEvent event) {
        ObservableList<Reclamation> list = sr.readAlltraite(currentUser.getId_user());
        table_reclamation.setItems(list);
        typeC1.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("type_client"));
        dateRC2.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("date_reclamation"));
        statuC3.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("statu_reclamation"));
        descriptionC4.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("description"));
    }

    @FXML
    void ajouter(ActionEvent event) {
        sr.add(new Reclamation(currentUser.getId_user(), descriptionTF.getText(),typeCB.getValue()));
        refreshtable_reclamation();
    }

    @FXML
    void supprimer(ActionEvent event) {

    }

    @FXML
    void chatbot(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chatchout.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("chatchout");
        stage.setScene(new Scene(root));
        stage.show();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Reclamation> list = sr.readAll();
        table_reclamation.setItems(list);
        typeC1.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("type_reclamation"));
        dateRC2.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("date_reclamation"));
        statuC3.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("statu_reclamation"));
        descriptionC4.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("description"));

        typeCB.getItems().addAll("probléme produit","probléme livraison","service client insatisfaisant","autres");
    }
}