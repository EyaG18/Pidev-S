package com.example.pidev_v1;

import com.example.pidev_v1.entities.Catégorie;
import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.services.CategorieService;
import com.example.pidev_v1.services.ProduitService;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.pidev_v1.services.ImageTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
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
    private ComboBox<String> CamboNomProduit;

    @FXML
    private ComboBox<String> ComboCategorieProduitUpdate;

    @FXML
    private TextField KeywordsTextLabel;

    @FXML
    private TextField NameProduitLabelUpdate;

    @FXML
    private Label NomUserSession;

    @FXML
    private TextField ProductPriceFieldUpdate;

    @FXML
    private TextField QteSeuilProductLabelUpdate;

    @FXML
    private TextField QteStockProduitLabelUpdate;

    @FXML
    private Button confirmUpdatepro;



    @FXML
    private Button btnUpdateProd;


    @FXML
    private AnchorPane filter;

    @FXML
    private TextField productImageFieldUpdate;

    @FXML
    private AnchorPane profil;

    @FXML
    private Button selectimageproforupdate;


   CategorieService cs = new CategorieService();
    @FXML
    private AnchorPane AnchorAjouterProduit;
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
    private Button RefreshiProduiet;

    @FXML
    private LineChart<?, ?> SecondTry;

    @FXML
    private PieChart pieChart;

    @FXML
    private Button BtnCheckStatProduits;

    @FXML
    private TextField NameProduitLabel;
    @FXML
    private TextField productImageField;
    @FXML
    private TextField KeywordsTextLabelP;

    @FXML
    private ComboBox<String> ComboCategorieProduit;

    @FXML
    private TextField ProductPriceField;

    @FXML
    private TextField QteSeuilProductLabel;

@FXML
private AnchorPane AnchorPaneModifierProduit;

    @FXML
    private TextField QteStockProduitLabel;

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
        AnchorPaneModifierProduit.setVisible(true);


//NavigationControler.OpenInterfaceUpdateProduct(event,"ModifierProduit.fxml");
    }

    @FXML
    void GoToKPIsProduits(MouseEvent event) {
        NavigationControler.OpenKPISProduits(event,"KpisProduitsBack.fxml");
    }

    ProduitService ps = new ProduitService();
    @FXML
    void afficherInterfaceAjouterProd(MouseEvent event) throws IOException {
        //loadPageAjouterProduit();
        //NavigationControler.changeAddproductPage(event ,"AjouterProduit.fxml");
        AnchorAjouterProduit.setVisible(true);
    }
    /*******************************************************************************************************/
    ObservableList<Produit> ListProductObservable = getProductList();


    /*************************************************/
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
        QuantiteProColumn.setCellValueFactory(new PropertyValueFactory<>("QteP"));
        QteSeulProColumn.setCellValueFactory(new PropertyValueFactory<>("QteSeuilP"));
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
/*************INITIALISEREYA*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AfficherProd();
        //ReturnNewSelectedCategory();
        //ReturnProduitAfterChoosingItFromComboBox1();
        DisplayCategoriesInComboBox();
        DisplayProductsInComboBox();

        btnAjouterProdDansAffich.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    afficherInterfaceAjouterProd(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        AnchorPaneModifierProduit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BtnUpdateProduitt(event);
            }
        });

        DisplayCategoriesInComboBox1();
        CamboNomProduit.setOnAction ((ActionEvent event) -> {
            ReturnProduitAfterChoosingItFromComboBox(); } ) ;
        ComboCategorieProduitUpdate.setOnAction((ActionEvent event)-> {
            ReturnNewSelectedCategory();
        });

    /*
     FilteredList<Catégorie> catégorieFilteredList = new FilteredList<>(ListCategoryObservable, b -> true);
        KeywordsTextLabel.textProperty().addListener((observableValue, oldValue, newValue) -> {
            catégorieFilteredList.setPredicate(Catégorie -> {
                // if no search value then display all data with no changes
                if (newValue == null || newValue.isBlank()) {
                    return true;
                }
                String searchKeyWordsCategory = newValue.toLowerCase();
                return Catégorie.getNomCatégorie().toLowerCase().contains(searchKeyWordsCategory);
            });
        });

        SortedList<Catégorie> SortedCategoryList = new SortedList<>(catégorieFilteredList);
        SortedCategoryList.comparatorProperty().bind(TableViewCategory.comparatorProperty());
        TableViewCategory.setItems(SortedCategoryList);
   */
        /*FilteredList<Produit> catégorieFilteredList = new FilteredList<>(ListCategoryObservable, b -> true);
        KeywordsTextLabel.textProperty().addListener((observableValue, oldValue, newValue) -> {
            catégorieFilteredList.setPredicate(Catégorie -> {
                // if no search value then display all data with no changes
                if (newValue == null || newValue.isBlank()) {
                    return true;
                }
                String searchKeyWordsCategory = newValue.toLowerCase();
                return Catégorie.getNomCatégorie().toLowerCase().contains(searchKeyWordsCategory);
            });
        });

        SortedList<Produit> SortedCategoryList = new SortedList<Produit>(catégorieFilteredList);
        SortedCategoryList.comparatorProperty().bind(TableViewProductBack.comparatorProperty());
        TableViewProductBack.setItems(SortedCategoryList);
*/
        FilteredList<Produit> produitFilteredList = new FilteredList<>(ListProductObservable, b -> true);
        KeywordsTextLabelP.textProperty().addListener((observableValue, oldValue, newValue) -> {
            produitFilteredList.setPredicate(produit -> {
                if (newValue == null || newValue.isBlank()) {
                    return true; // Afficher toutes les données si le champ de recherche est vide
                }

                String searchTerm = newValue.toLowerCase();

                // Vérifier si le terme de recherche correspond à l'un des champs du produit
                return produit.getNomP().toLowerCase().contains(searchTerm)
                        || String.valueOf(produit.getPrixP()).contains(searchTerm)
                        || String.valueOf(produit.getQteP()).contains(searchTerm)
                        || String.valueOf(produit.getQteSeuilP()).contains(searchTerm)
                        || produit.getImageP().toLowerCase().contains(searchTerm)
                        || String.valueOf(produit.getId_Catégorie()).contains(searchTerm);
            });
        });

        SortedList<Produit> sortedProductList = new SortedList<>(produitFilteredList);
        sortedProductList.comparatorProperty().bind(TableViewProductBack.comparatorProperty());
        TableViewProductBack.setItems(sortedProductList);


    }

    public void DisplayCategoriesInComboBox1()
    {
        MyDataBase ct = new MyDataBase();
        Connection cnx = ct.getCnx();
        String queryCategory = "SELECT NomCatégorie FROM catégorie";
        try {
            Statement statement = cnx.createStatement();
            ResultSet QueryOutput = statement.executeQuery(queryCategory);

            // Clear ComboBox before adding new items
            ComboCategorieProduitUpdate.getItems().clear();

            while (QueryOutput.next()) {
                String queryCategoryName1 = QueryOutput.getString("NomCatégorie");
                // Add category name to ComboBox
                ComboCategorieProduitUpdate.getItems().add(queryCategoryName1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void DisplayProductsInComboBox() {

        MyDataBase ct = new MyDataBase();
        Connection cnx = ct.getCnx();
        String queryCategory = "SELECT NomP FROM produit";
        try {
            Statement statement = cnx.createStatement();
            ResultSet QueryOutput = statement.executeQuery(queryCategory);
            CamboNomProduit.getItems().clear();
            while (QueryOutput.next()) {
                String queryCategoryName = QueryOutput.getString("NomP");
                // Add category name to ComboBox
                CamboNomProduit.getItems().add(queryCategoryName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /***********Fonction Load page modifier produit ***********************************/

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
    /***************************************************************/
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


        AnchorAjouterProduit.setVisible(true);
        //NavigationControler.OpenAffichageProduitsBack(event,"AjouterProduit.fxml");

    }

/************************************/
    public void DisplayCategoriesInComboBox()
    {
        MyDataBase ct = new MyDataBase();
        Connection cnx = ct.getCnx();
        String queryCategory = "SELECT NomCatégorie FROM catégorie";
        try {
            Statement statement = cnx.createStatement();
            ResultSet QueryOutput = statement.executeQuery(queryCategory);
            ComboCategorieProduit.getItems().clear();

            while (QueryOutput.next()) {
                String queryCategoryName = QueryOutput.getString("NomCatégorie");
                // Add category name to ComboBox
                ComboCategorieProduit.getItems().add(queryCategoryName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/***************************************************/
@FXML
void addProduct(MouseEvent event) {
    try {
        String selectedCategoryNameProduct = ComboCategorieProduit.getValue();
        String productName = NameProduitLabel.getText();
        Float priceProduct = Float.parseFloat(ProductPriceField.getText());
        Integer qteStockProduct = Integer.parseInt(QteStockProduitLabel.getText());
        Integer qteSeuil = Integer.parseInt(QteSeuilProductLabel.getText());
        String productImage = productImageField.getText();

        if (selectedCategoryNameProduct == null || productName.isEmpty() || priceProduct == null || qteStockProduct == null || qteSeuil == null || productImage.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur lors de l'ajout !");
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }
        // Vérifier si un produit avec le même nom existe déjà
        if (ps.isProductNameExists(productName)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur lors de l'ajout !");
            alert.setContentText("Un produit avec le même nom existe déjà.");
            alert.showAndWait();
            return;
        }

        // Vérifier si un produit avec la même photo existe déjà
        if (ps.isProductImageExists(productImage)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur lors de l'ajout !");
            alert.setContentText("Un produit avec la même photo existe déjà.");
            alert.showAndWait();
            return;
        }

        int selectedCategoryId = cs.getCategoryIdFromName2(selectedCategoryNameProduct);
        if (selectedCategoryId == -1) {
            // Si la catégorie n'existe pas, afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur lors de l'ajout !");
            alert.setContentText("La catégorie sélectionnée n'existe pas.");
            alert.showAndWait();
            return;
        }
        ps.addProduct(new Produit(selectedCategoryId, productName, priceProduct, qteStockProduct, qteSeuil, productImage));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès !");
        alert.setContentText("Produit ajouté avec succès !");
        alert.showAndWait();
        //loadDetailsProduitsView();
        //af.ActualiserListeProduits(event);
        //af.AfficherProd();
        ActualiserListeProduits(event);
        AfficherProd();
        ComboCategorieProduit.getSelectionModel().clearSelection();
        NameProduitLabel.clear();
        ProductPriceField.clear();
        QteStockProduitLabel.clear();
        QteSeuilProductLabel.clear();
        productImageField.clear();
    } catch (Exception exception) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur !");
        alert.setContentText(exception.getMessage());
        System.out.println(exception.getMessage());
        alert.showAndWait();
    }
    AnchorAjouterProduit.setVisible(false);
}
/************************************************/
@FXML
void SelectImageProduct(MouseEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir une image");
    // Filtrer les types de fichiers pour n'afficher que les images
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Images", "*.jpg", "*.jpeg", "*.png", "*.gif");
    fileChooser.getExtensionFilters().add(extFilter);
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
        productImageField.setText(((File) selectedFile).getAbsolutePath());
    }
}
    @FXML
    void SelectImageProdUpdate(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        // Filtrer les types de fichiers pour n'afficher que les images
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Images", "*.jpg", "*.jpeg", "*.png", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            productImageFieldUpdate.setText(((File) selectedFile).getAbsolutePath());
        }
    }
    Produit produit ;
Produit produit1;

    @FXML
    void ConfirmerModifcation(MouseEvent event) {
        Produit produit1 = ReturnProduitAfterChoosingItFromComboBox();
        int selectedCategoryID;
        Catégorie c = ReturnNewSelectedCategory();
        selectedCategoryID = c.getId_CatégorieC();
String chosennewnameP = NameProduitLabelUpdate.getText();
String chosennewImageP = productImageFieldUpdate.getText();

        if (NameProduitLabelUpdate.getText().equals("") || ProductPriceFieldUpdate.getText().equals("") || productImageFieldUpdate.getText().equals("") || QteStockProduitLabelUpdate.getText().equals("") || QteSeuilProductLabelUpdate.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Le remplissage de tous les champs est obligatoire pour la Modification");
            alert.showAndWait();
            return;
        }


        if (ps.isProductImageExists(chosennewImageP)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur lors de la modification !");
            alert.setContentText("Un produit avec la même photo existe déjà.");
            alert.showAndWait();
            return;
        }


        if (ps.isProductNameExists(chosennewnameP)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur lors de la modification !");
            alert.setContentText("Un produit avec le même nom existe déjà.");
            alert.showAndWait();
            return;
        }


        produit1.setId_Catégorie(selectedCategoryID);
        produit1.setNomP(NameProduitLabelUpdate.getText());
        produit1.setPrixP(Float.parseFloat(ProductPriceFieldUpdate.getText()));
        produit1.setQteP(Integer.parseInt(QteStockProduitLabelUpdate.getText()));
        produit1.setQteSeuilP(Integer.parseInt(QteSeuilProductLabelUpdate.getText()));
        produit1.setImageP(productImageFieldUpdate.getText());
        ps.modifyProduct(produit1);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("La modification a été effectuée avec succès.");
        alert.showAndWait();


        AnchorPaneModifierProduit.setVisible(false);
    }




    public Catégorie ReturnNewSelectedCategory()
    {
        System.out.println("Bonjour , Fonction ReturnNewSelectedCategory");
        String selectedCategoryNme = ComboCategorieProduitUpdate.getValue(); // Récupérer le nom du produit sélectionné
        if (selectedCategoryNme != null)
        {
            Catégorie cat = cs.getCategoryBName(selectedCategoryNme);
            System.out.println("La catégorie est :" + cat);
            if (cat != null) {
                showAlert(Alert.AlertType.INFORMATION, "catégorie trouvé", "Le catégorie a été trouvé : " );
                return cat;
            } else {
                showAlert(Alert.AlertType.WARNING, "catégorie non trouvé", "Aucun catégorie correspondant trouvé.");
                return null;
            }

        } else {
            showAlert(Alert.AlertType.WARNING, "Aucune catégorie sélectionné", "Veuillez sélectionner une catégorie.");
            return null;
        }
    }


    public Produit ReturnProduitAfterChoosingItFromComboBox1() {
        System.out.println("Méthode ReturnProduitAfterChoosingItFromComboBox appelée");

        String selectedProductName = CamboNomProduit.getValue(); // Récupérer le nom du produit sélectionné
        if (selectedProductName != null) { // Vérifier si un produit est sélectionné
            Produit produit = ps.GetProductByName(selectedProductName); // Obtenir le produit à partir de son nom
            if (produit != null) {
                showAlert(Alert.AlertType.INFORMATION, "Produit trouvé", "Le produit a été trouvé : " + produit);
                return produit;
            } else {
                showAlert(Alert.AlertType.WARNING, "Produit non trouvé", "Aucun produit correspondant trouvé.");
                return null;
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Aucun produit sélectionné", "Veuillez sélectionner un produit.");
            return null;
        }
    }


    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }



    public Produit ReturnProduitAfterChoosingItFromComboBox() {
        String SelectedProductName = CamboNomProduit.getValue(); // Récupérer le nom du produit sélectionné
        if (SelectedProductName != null) { // Vérifier si un produit est sélectionné
            Produit produit = ps.GetProductByName(SelectedProductName); // Obtenir le produit à partir de son nom
            if (produit != null) {
                System.out.println("Produit trouvé : " + produit);
                return produit;
            } else {
                System.out.println("Produit non trouvé.");
                return null;
            }
        } else {
            System.out.println("Aucun produit sélectionné.");
            return null;
        }
    }

















}
