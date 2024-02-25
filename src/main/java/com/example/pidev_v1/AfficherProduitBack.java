package com.example.pidev_v1;

import com.example.pidev_v1.entities.Catégorie;
import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.services.ProduitService;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.pidev_v1.services.ImageTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
//import javax.security.auth.callback.Callback;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.PublicKey;
import java.sql.*;
import java.util.ResourceBundle;


public class AfficherProduitBack implements Initializable {


    @FXML
    private TableColumn<Produit,Void> tActions;
    @FXML
    private TableColumn<?, ?> ActionsColumn;


    @FXML
    private TableColumn<?, ?> IdCatProductColumn;

    @FXML
    private TableColumn<Produit, String> NameprodCATColumn;
    @FXML
    private TableColumn<Produit, String> ImageProductColumn;

    @FXML
    private TableColumn<Produit, Integer> NameCatProColumn;

    @FXML
    private TableColumn<Produit, String> NameProductColumn;

    @FXML
    private TableColumn<Produit, Float> PriceProductColumn;

    @FXML
    private TableColumn<Produit, Integer> QteSeulProColumn;

    @FXML
    private TableColumn<Produit, Integer> QuantiteProColumn;
    @FXML
    private TableView<Produit> TableViewProductBack;

    @FXML
    private Button btnAjouterProdDansAffich;

    @FXML
    private Button btnDeleteProd;

    @FXML
    private Button btnUpdateProd;

    @FXML
    private Button RefreshiProduiet;

    SideBarBackForEmployees sb = new SideBarBackForEmployees();
    //sb.GoToProducts(MouseEvent event);



    @FXML
    void ActualiserListeProduits(MouseEvent event) {
AfficherProd();
    }

    public TableView<Produit> getTableViewProductBack() {
        return TableViewProductBack;
    }

    @FXML
    void BtnDeletProduct(MouseEvent event) {

        try {
            // Obtenez le nom de la catégorie sélectionnée
            String selectedProductName = TableViewProductBack.getSelectionModel().getSelectedItem().getNomP();
            System.out.println("nom selectionne est:"+ selectedProductName);

            // Appelez la fonction DeleteCategoryByName pour supprimer la catégorie sélectionnée
            ps.DeleteProductByName(selectedProductName);

            // Actualisez le TableView pour refléter les modifications après la suppression
            //RefreshProduit(event);
            ActualiserListeProduits(event);
        } catch (NullPointerException e) {
            // Aucune catégorie sélectionnée
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur !");
            alert.setContentText("Aucun Produit sélectionné ! Veuillez sélectionner un Produit à supprimer.");
            alert.showAndWait();

        } catch (Exception e) {
            // Toutes les autres exceptions

            e.printStackTrace();
        }
    }

    @FXML
    void BtnUpdateProduitt(MouseEvent event) {
NavigationControler.OpenInterfaceUpdateProduct(event,"ModifierProduit.fxml");
    }

    ProduitService ps = new ProduitService();
    @FXML
    void afficherInterfaceAjouterProd(MouseEvent event) throws IOException {
        //loadPageAjouterProduit();
        NavigationControler.changeAddproductPage(event ,"AjouterProduit.fxml");
    }
    /*******************************************************************************************************/
    public ObservableList<Produit> getProductList() {
        ObservableList<Produit> ListProduct = FXCollections.observableArrayList();
        MyDataBase db = new MyDataBase();
        Connection cnx = db.getCnx();
        //String query = "SELECT p.*, c.NomCatégorie FROM produit p JOIN catégorie c ON p.Id_Catégorie = c.Id_Catégorie"; // Utilisation de la jointure pour récupérer le nom de la catégorie
       // String query = " SELECT * FROM VueProduit";
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
                Produit produit1= new Produit(idCategorie,nomProduit,prixProduit,qteProduit,qteSeuilProduit,imageProduit);
                ListProduct.add(produit1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListProduct;
    }
/***********************************************************************/
public ObservableList<Produit> getListeProduitsAvecCategorie() {
    ObservableList<Produit> listeProduits = FXCollections.observableArrayList();
  String query = "SELECT * FROM VueProduit";
    // Établir la connexion à la base de données
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev2", "root", "");
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(query)) {
        while (resultSet.next()) {
            Produit produit = new Produit();
            produit.setNomP(resultSet.getString("NomProduit"));
            produit.setPrixP(resultSet.getFloat("PrixP"));
            produit.setQteP(resultSet.getInt("QteP"));
            produit.setQteSeuilP(resultSet.getInt("QteSeuilP"));
            produit.setImageP(resultSet.getString("ImageP"));
            produit.setNomCatégorie(resultSet.getString("NomCatégorie"));

            listeProduits.add(produit);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listeProduits;
}
/****************************************************/

public void afficherProdavecCategories()
{

    ObservableList<Produit> listProdAvecccat= getListeProduitsAvecCategorie();

    NameProductColumn.setCellValueFactory(new PropertyValueFactory<>("NomP"));
    PriceProductColumn.setCellValueFactory(new PropertyValueFactory<>("PrixP"));
    QteSeulProColumn.setCellValueFactory(new PropertyValueFactory<>("QteP"));
    QuantiteProColumn.setCellValueFactory(new PropertyValueFactory<>("QteSeuilP"));
    ImageProductColumn.setCellValueFactory(new PropertyValueFactory<>("ImageP"));
    NameprodCATColumn.setCellValueFactory(new PropertyValueFactory<>("NomCatégorie"));

    TableViewProductBack.setItems(listProdAvecccat);
}
/*****************************************************************/

private String getCategoryNameById(int categoryId) {
    MyDataBase db = new MyDataBase();
    Connection cnx = db.getCnx();
    String query = "SELECT NomCatégorie FROM catégorie WHERE Id_Catégorie = ?";
    try {
        PreparedStatement ps = cnx.prepareStatement(query);
        ps.setInt(1, categoryId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("Yes");
            return rs.getString("NomCatégorie");

        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return null;
}

    public TableColumn<Produit, String> getImageProductColumn() {
        return ImageProductColumn;
    }

    /**********************************************/
    public void AfficherProd()
    {
        ObservableList<Produit> listProduits = getProductList();

        IdCatProductColumn.setCellValueFactory(new PropertyValueFactory<>("Id_Catégorie"));
        NameProductColumn.setCellValueFactory(new PropertyValueFactory<>("NomP"));
        PriceProductColumn.setCellValueFactory(new PropertyValueFactory<>("PrixP"));
        QteSeulProColumn.setCellValueFactory(new PropertyValueFactory<>("QteP"));
        QuantiteProColumn.setCellValueFactory(new PropertyValueFactory<>("QteSeuilP"));
        ImageProductColumn.setCellValueFactory(new PropertyValueFactory<>("ImageP"));

        TableViewProductBack.setItems(listProduits);
    }
    /**********************************************/
    public void loadPageAjouterProduit() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterProduit.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Gestion des Produits - Ajouter Un Produit");
        stage.setScene(new Scene(root));
        stage.show();
    }
/*****************************************************************/


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AfficherProd();

        tActions.setCellFactory(new Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>>() {
            @Override
            public TableCell<Produit, Void> call(TableColumn<Produit, Void> param) {
                return new TableCell<Produit, Void>() {
                    private final Button actionButton = new Button("supprimer");
                    {// Définir le comportement du bouton "Action"
                        actionButton.setOnAction(event -> {
                            Produit produit = getTableView().getItems().get(getIndex());
                            ps.DeleteProduct(produit.getId_Produit());
                            AfficherProd();
                            System.out.println("Action clicked for product: " + produit);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(actionButton);
                        }
                    }
                };
            }
        });
    }

    /***********Fonction Load page modifier produit ***********************************/

    public void loadUpdateProductView() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierProduit.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Liste Des Prouits");
        stage.setScene(new Scene(root));
        stage.show();
    }
    ////////////////////////////////////////////
    @FXML
    void GoToCategories(MouseEvent event) {
NavigationControler.OpenInterfaceCategories(event,"DetailsCategorie.fxml");
    }
    @FXML
    void GoToProducts(MouseEvent event) {
        NavigationControler.OpenAffichageProduitsBack(event,"AjouterProduit.fxml");
    }









}
