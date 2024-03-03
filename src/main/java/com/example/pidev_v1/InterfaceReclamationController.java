package com.example.pidev_v1;




import com.example.pidev_v1.entities.Reclamation;
import com.example.pidev_v1.entities.Reponse;
import com.example.pidev_v1.services.ReclamationService;
import com.example.pidev_v1.services.ReponseServices;
import com.jfoenix.controls.JFXButton;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class InterfaceReclamationController implements Initializable {
    ReclamationService sr=new ReclamationService ();
    ReponseServices ser=new ReponseServices();
    @FXML
    private JFXButton Bcommandes;

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

    @FXML
    private TableColumn<Reclamation, Date> dateC3;

    @FXML
    private TableColumn<Reponse, Date> daterC1;

    @FXML
    private TableColumn<Reclamation, String> descriptionC2;

    @FXML
    private TableColumn<Reclamation, String> nomC1;

    @FXML
    private TableColumn<Reponse, String> reponseC2;

    @FXML
    private TableColumn<Reclamation, String> statuC4;

    @FXML
    private TableView<Reclamation> table_reclamation;

    @FXML
    private TableView<Reponse> table_reponse;

    @FXML
    private TableColumn<Reclamation, String> typeC5;
    @FXML
    private TableColumn<Reclamation, Integer> IDC6;

    public void refreshtable_reponse() {
        table_reponse.getItems().clear();
        table_reponse.getItems().addAll(ser.readAll());
    }

    public void refreshtable_reclamation() {
        table_reclamation.getItems().clear();
        table_reclamation.getItems().addAll(sr.readAll());
    }


    @FXML
    void actualiserreclamation(ActionEvent event) {
        refreshtable_reclamation();
    }

    @FXML
    void actualiserreponse(ActionEvent event) {
        refreshtable_reponse();
    }


    @FXML
    void supprimerReponse() {
        try {
            Reponse reponse=new Reponse(table_reponse.getSelectionModel().getSelectedItem().getID_reclamation(),
                    table_reponse.getSelectionModel().getSelectedItem().getID_reponse(),
                    table_reponse.getSelectionModel().getSelectedItem().getReponse(),
                    table_reponse.getSelectionModel().getSelectedItem().getDate_reponse());
            ser.delete(reponse);
            refreshtable_reponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void repondre() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Reponse.fxml"));
        Parent root= loader.load();
        Stage tertiaryStage = new Stage();
        tertiaryStage.setTitle("Front");
        tertiaryStage.setScene(new Scene(root));
        tertiaryStage.show();

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


/**********ConsulterReclamation***************************/


        ObservableList<Reclamation> list = sr.readAll();
        table_reclamation.setItems(list);



        nomC1.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("nom_client"));
        descriptionC2.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("description"));
        dateC3.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("date_reclamation"));
        statuC4.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("statu_reclamation"));
        typeC5.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("type_reclamation"));



/*******************ConsulterReponse***********************/
        ObservableList<Reponse> list2= ser.readAll();
        table_reponse.setItems(list2);

        reponseC2.setCellValueFactory(new PropertyValueFactory<Reponse,String>("reponse"));
        daterC1.setCellValueFactory(new PropertyValueFactory<Reponse,Date>("date_reponse"));

    }
}