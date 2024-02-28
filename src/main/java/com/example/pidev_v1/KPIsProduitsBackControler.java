package com.example.pidev_v1;

import com.example.pidev_v1.tools.MyDataBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class KPIsProduitsBackControler implements Initializable {

    @FXML
    private Button BrnFournisseursAppelsOffres;

    @FXML
    private Button BtnCRM;

    @FXML
    private Button BtnCategories;

    @FXML
    private Button BtnProduits;

    @FXML
    private Button CommandesLivraisosBTN;

    @FXML
    private TextField KeywordsTextLabel;

    @FXML
    private Label NomUserSession;

    @FXML
    private LineChart<?, ?> SecondTry;

    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private Button UsersBoutons;

    @FXML
    private AnchorPane filter;

    @FXML
    private PieChart pieChart;

    @FXML
    private AnchorPane profil;

    @FXML
    void GoToCategories(MouseEvent event) {
NavigationControler.OpenInterfaceCategories(event,"DetailsCategorie.fxml");
    }

    @FXML
    void GoToProducts(MouseEvent event) {
NavigationControler.OpenAffichageProduitsBack(event,"AfficherProduitBack.fxml");
    }
/**************************************/

    public void evolutionPrixProduits() {
        SecondTry.getData().clear();

        String sql = "SELECT p.Id_Catégorie, AVG(p.PrixP) " +
                "FROM produit p " +
                "GROUP BY p.Id_Catégorie";

        try {

            MyDataBase db = new MyDataBase();
            Connection cnx = db.getCnx();
            PreparedStatement statement = cnx.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            XYChart.Series series = new XYChart.Series();

            while (resultSet.next()) {
                series.getData().add(new XYChart.Data<>(String.valueOf(resultSet.getInt(1)), resultSet.getDouble(2)));
            }
            SecondTry.getData().add(series);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

/*******************************************************/

public void nombreProduitsParCategorie() {
    pieChart.getData().clear();

    String sql = "SELECT c.NomCatégorie, COUNT(p.Id_Produit) " +
            "FROM catégorie c " +
            "LEFT JOIN produit p ON c.Id_Catégorie = p.Id_Catégorie " +
            "GROUP BY c.NomCatégorie";

    try {

        MyDataBase db = new MyDataBase();
        Connection cnx = db.getCnx();
        PreparedStatement statement = cnx.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            pieChart.getData().add(new PieChart.Data(resultSet.getString(1), resultSet.getInt(2)));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

/***************************************************************/

public void prixMoyenProduitsParCategorie() {
    barChart.getData().clear();

    String sql = "SELECT c.NomCatégorie, AVG(p.PrixP) " +
            "FROM catégorie c " +
            "LEFT JOIN produit p ON c.Id_Catégorie = p.Id_Catégorie " +
            "GROUP BY c.NomCatégorie";

    try {
        MyDataBase db = new MyDataBase();
        Connection cnx = db.getCnx();
        PreparedStatement statement = cnx.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        XYChart.Series series = new XYChart.Series();

        while (resultSet.next()) {
            series.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getDouble(2)));
        }

        barChart.getData().add(series);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prixMoyenProduitsParCategorie();
        nombreProduitsParCategorie();
        evolutionPrixProduits();
    }
}
