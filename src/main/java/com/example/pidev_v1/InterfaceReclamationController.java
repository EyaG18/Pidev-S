package com.example.pidev_v1;




import com.example.pidev_v1.entities.Reclamation;
import com.example.pidev_v1.entities.Reponse;
import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.services.ReclamationService;
import com.example.pidev_v1.services.ReponseServices;
import com.jfoenix.controls.JFXButton;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.scene.control.TableCell;
import javafx.scene.paint.Color;
import javafx.util.Callback;

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
    @FXML
    private StackedBarChart<?, ?> statReponse;
    @FXML
    private PieChart statType;
    @FXML
    private Label RtotalL;

    @FXML
    private Label RtraiteL;

    private User currentUser;
    public void setUser(User user)
    {
        currentUser =user;
        System.out.println("nomcurrentUser"+currentUser.getId_user());
    }




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
        int ID=ser.repurerID_reclamation(table_reclamation.getSelectionModel().getSelectedItem().getDescription(),
                table_reclamation.getSelectionModel().getSelectedItem().getType_reclamation());

        System.out.println("id controlleur reclamation"+ID);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Reponse.fxml"));
        Parent root= loader.load();
        Stage tertiaryStage = new Stage();
        tertiaryStage.setTitle("Front");
        tertiaryStage.setScene(new Scene(root));
        ReponseController controller = loader.getController();
        controller.setID_Reclamation(ID);
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
        /********stat1**********************************************/
      XYChart.Series series1=new XYChart.Series();
      series1.setName("Hiver");
      series1.getData().add(new XYChart.Data("Décembre",ser.moyenneTempsdereponse(12)));
      series1.getData().add(new XYChart.Data("Janvier",ser.moyenneTempsdereponse(1)));
      series1.getData().add(new XYChart.Data("Février",ser.moyenneTempsdereponse(2)));
      System.out.println(ser.moyenneTempsdereponse(3));
      System.out.println(sr.CountType("probléme produit"));
      System.out.println(sr.CountType("probléme livraison"));
      System.out.println(sr.CountType("service client insatisfaisant"));
      System.out.println(sr.CountType("autres"));

      XYChart.Series series2=new XYChart.Series();
      series2.setName("Printemps");
      series2.getData().add(new XYChart.Data("Mars",ser.moyenneTempsdereponse(3)));
      series2.getData().add(new XYChart.Data("Avril",ser.moyenneTempsdereponse(4)));
      series2.getData().add(new XYChart.Data("Mai",ser.moyenneTempsdereponse(5)));

      XYChart.Series series3=new XYChart.Series();
      series3.setName("étè");
      series3.getData().add(new XYChart.Data("Juin",ser.moyenneTempsdereponse(6)));
      series3.getData().add(new XYChart.Data("Juillet",ser.moyenneTempsdereponse(7)));
      series3.getData().add(new XYChart.Data("Aout",ser.moyenneTempsdereponse(8)));

      XYChart.Series series4=new XYChart.Series();
      series4.setName("Autumn");
      series4.getData().add(new XYChart.Data("Septembre",ser.moyenneTempsdereponse(9)));
      series4.getData().add(new XYChart.Data("Novembre",ser.moyenneTempsdereponse(10)));
      series4.getData().add(new XYChart.Data("Octobre",ser.moyenneTempsdereponse(11)));

      statReponse.getData().addAll(series1,series2,series3,series4);

        /*********************stat2***************************/
        ObservableList<PieChart.Data> statTypeData=
                FXCollections.observableArrayList(
                        new PieChart.Data("probléme produit", sr.CountType("probléme produit")),
                        new PieChart.Data("probléme livraison", sr.CountType("probléme livraison")),
                        new PieChart.Data("service client insatisfaisant", sr.CountType("service client insatisfaisant")),
                        new PieChart.Data("autres", sr.CountType("autres")));

        statTypeData.forEach(data -> data.nameProperty().bind(Bindings.concat(data.getName()," Nb Total ",data.pieValueProperty())));
        statType.getData().addAll(statTypeData);
        /*****************************LabelCount***********************/
        int total=sr.CountType("probléme produit")+sr.CountType("probléme livraison")+sr.CountType("service client insatisfaisant")+sr.CountType("autres");
        RtotalL.setText(String.valueOf(total));
        RtraiteL.setText(String.valueOf(sr.Count()));
        /****************************************************************/

        statuC4.setCellFactory(new Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>>() {
            @Override
            public TableCell<Reclamation, String> call(TableColumn<Reclamation, String> column) {
                return new TableCell<Reclamation, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setStyle("");
                            return;
                        }

                        if (item.equals("traité")) {
                            setText(item);
                            setStyle("-fx-background-color: green;");
                        } else {
                            setText(item);
                            setStyle("-fx-background-color: red;");
                        }
                    }
                };
            }
        });
    }
        }

