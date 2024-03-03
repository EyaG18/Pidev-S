package com.example.pidev_v1;

import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.services.ProduitService;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.SocketTimeoutException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.pidev_v1.API.TwilioSMS.sendSMS;

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
    private BarChart<?, ?> barChart1;

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
    private Button btnNotifStock;
    ProduitService produitService = new ProduitService();


    private ObservableList<Produit> getProductList() {
        ObservableList<Produit> ListProduct = FXCollections.observableArrayList();
        MyDataBase db = new MyDataBase();
        Connection cnx = db.getCnx();
        String query = "SELECT * FROM produit";
        Statement st;
        ResultSet rs;
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                int idProduit = rs.getInt("Id_Produit");
                int idCategorie = rs.getInt("Id_Catégorie");
                String nomProduit = rs.getString("NomP");
                float prixProduit = rs.getFloat("PrixP");
                int qteProduit = rs.getInt("QteP");
                int qteSeuilProduit = rs.getInt("QteSeuilP");
                String imageProduit = rs.getString("ImageP");
                Produit produit1= new Produit(idProduit,idCategorie,nomProduit,prixProduit,qteProduit,qteSeuilProduit,imageProduit);
                ListProduct.add(produit1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListProduct;
    }

    ObservableList<Produit> listProduits;

    @FXML
    void GetNotifByApi(MouseEvent event) {
        ObservableList<Produit> listProduits = getProductList(); // Utiliser listProduits au lieu de listProduit
        StringBuilder notificationMsg = new StringBuilder("Produits en rupture de stock :\n");
        boolean stockInsuffisant = false; // Variable pour vérifier s'il y a un stock insuffisant

        for (Produit produit : listProduits) {
            if (produit.getQteP() <= produit.getQteSeuilP()) {
                // Ajouter l'identifiant du produit à la chaîne de notification
                notificationMsg.append(" Produit ID: ").append(produit.getId_Produit()).append("\n");
                System.out.println(produit.getId_Produit());
                // stockInsuffisant = true; // Définir à true car il y a un stock insuffisant
                if (notificationMsg.length() > 0) {
                    // Envoyer la notification par SMS si des produits sont en rupture de stock
                    sendSMS(23067230, notificationMsg.toString());
                }
            }
        }
        System.out.println("Tous les produits sont en stock.");
    }

/********************************************/
/*StringBuilder notificationMsg = new StringBuilder("Produits en rupture de stock :\n");
    boolean stockInsuffisant = false; // Variable pour vérifier s'il y a un stock insuffisant

        for (Produit produit : listProduits) {
        if (produit.getQteP() <= produit.getQteSeuilP()) {
            // Ajouter l'identifiant du produit à la chaîne de notification
            //notificationMsg.append(" Produit ID: ").append(produit.getId_Produit()).append("\n");
            System.out.println(produit.getId_Produit());
            //stockInsuffisant = true; // Définir à true car il y a un stock insuffisant
        }
    }
    / (stockInsuffisant) {
        // Envoyer la notification par SMS uniquement s'il y a un stock insuffisant
        sendSMS(23067230, notificationMsg.toString());
    } else {
        //System.out.println(notificationMsg);
        System.out.println("Tous les produits sont en stock.");
    }
        /*if (notificationMsg.length() > 0) {
            // Envoyer la notification par SMS si des produits sont en rupture de stock
            sendSMS(23067230, notificationMsg.toString());
        }*/
    /**************************************************/
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




    public void Statistiques8stock_Porudits()
    {
        System.out.println("fonction stat produits panier est appllee");
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Produit");
        yAxis.setLabel("Quantité");

        // Création du graphique BarChart
        BarChart<String, Number> barChart1 = new BarChart<>(xAxis, yAxis);
        barChart1.setTitle("Statistique de Stock et Panier");
        String sql = "SELECT p.NomP, SUM(p.QteP) AS Stock, IFNULL(SUM(pa.QuantiteParProduit), 0) AS Panier " +
                "FROM Produit p " +
                "LEFT JOIN Panier pa ON p.Id_Produit = pa.Id_Produit " +
                "GROUP BY p.NomP";
        // Exécution de la requête SQL pour récupérer les données de stock et de panier
        try {
            MyDataBase db = new MyDataBase();
            Connection cnx = db.getCnx();
            PreparedStatement statement;
            statement = cnx.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("eya");

            // Ajout des séries de données au graphique
            while (resultSet.next()) {
                String produit = resultSet.getString("NomP");
                int stock = resultSet.getInt("QteP");
                int panier = resultSet.getInt("QuantiteParProduit");

                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName(produit);
                series.getData().add(new XYChart.Data<>("Stock", stock));
                series.getData().add(new XYChart.Data<>("Panier", panier));
                barChart1.getData().addAll(series);

            }
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
@Override
public void initialize(URL url, ResourceBundle resourceBundle) {
    prixMoyenProduitsParCategorie();
    nombreProduitsParCategorie();
    evolutionPrixProduits();
    //Statistiques8stock_Porudits();
}
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

}
