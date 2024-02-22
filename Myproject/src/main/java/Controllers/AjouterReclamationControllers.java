package Controllers;

import Services.ServiceReclamation;
import entities.Reclamation;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AjouterReclamationControllers implements Initializable {

    private ServiceReclamation rs=new ServiceReclamation();

    @FXML
    private TextField statuTF;

    @FXML
    private TextField typeTF;

    @FXML
    private TextField clientTF;

    @FXML
    private Button ajouterB;


    @FXML
    private TextArea descriptionTA;



    @FXML
    private TableColumn<Reclamation, Integer> column2;

    @FXML
    private TableColumn<Reclamation, String> column3;

    @FXML
    private TableColumn<Reclamation, Date> column4;

    @FXML
    private TableColumn<Reclamation, String> column5;

    @FXML
    private TableColumn<Reclamation, String> column6;

    @FXML
    private TableView<Reclamation> view;



   /*@FXML
    void ajouter(ActionEvent event) {
       rs.add(new Reclamation(Integer.parseInt(clientTF.getText()),
               descriptionTA.getText(),statuTF.getText(),typeTF.getText()));

    }*/


    public void ajouter(javafx.event.ActionEvent actionEvent) {
        rs.add(new Reclamation(Integer.parseInt(clientTF.getText()),
                descriptionTA.getText(),statuTF.getText(),typeTF.getText()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


            ObservableList<Reclamation> list = rs.readAll();
            view.setItems(list);

            column2.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("ID_client"));
            column3.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("description"));
            column4.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("date_reclamation"));
            column5.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("statu_reclamation"));
            column6.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("type_reclamation"));


    }
}
