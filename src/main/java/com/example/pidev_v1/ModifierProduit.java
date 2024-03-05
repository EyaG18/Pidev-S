package com.example.pidev_v1;

import com.example.pidev_v1.entities.Catégorie;
import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.services.CategorieService;
import com.example.pidev_v1.services.ProduitService;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ModifierProduit implements Initializable {

    @FXML
    private ComboBox<String> ComboCategorieProduitUpdate;

    @FXML
    private TextField NameProduitLabelUpdate;

    @FXML
    private TextField ProductPriceFieldUpdate;

    @FXML
    private TextField QteSeuilProductLabelUpdate;

    @FXML
    private TextField QteStockProduitLabelUpdate;

    @FXML
    private TextField productImageFieldUpdate;

    @FXML
    private ComboBox<String> CamboNomProduit;

    @FXML
    void ConfirmerModifcation(MouseEvent event) {

        Produit p = ReturnProduitAfterChoosingItFromComboBox();
        int selectedCategoryID ;
        Catégorie c = ReturnNewSelectedCategory();
        selectedCategoryID =c.getId_CatégorieC();


        if (NameProduitLabelUpdate.getText().equals("") ||ProductPriceFieldUpdate.getText().equals("") || productImageFieldUpdate.getText().equals("")  || QteStockProduitLabelUpdate.getText().equals("") || QteSeuilProductLabelUpdate.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Le remplissage de tous les champs est obligatoire pour la Modification");
            alert.showAndWait();
            return;
        }

        p.setId_Catégorie(selectedCategoryID);
        p.setNomP(NameProduitLabelUpdate.getText());
        p.setPrixP(Float.parseFloat(ProductPriceFieldUpdate.getText()));
        p.setQteP(Integer.parseInt(QteStockProduitLabelUpdate.getText()));
        p.setQteSeuilP(Integer.parseInt(QteSeuilProductLabelUpdate.getText()));
        p.setImageP(productImageFieldUpdate.getText());
        ps.modifyProduct(p);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("La modification a été effectuée avec succès.");
        alert.showAndWait();

    }

    Produit produit ;
    ProduitService ps = new ProduitService();
    CategorieService cs = new CategorieService();

    ObservableList<Produit> ListProduitsObservable = FXCollections.observableArrayList();

    @FXML
    private Button BtnCategories;

    @FXML
    private Button BtnProduits;

    @FXML
    void GoToCategories(MouseEvent event) {
        NavigationControler.OpenInterfaceCategories(event,"DetailsCategorie.fxml");
    }

    @FXML
    void GoToProducts(MouseEvent event) {
        NavigationControler.OpenAffichageProduitsBack(event,"AfficherProduitBack.fxml");

    }
/**************************************************************************************/
    /**************************************************************/
    public void DisplayProductsInComboBox()
    {
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
        /*String SelectedProductName = CamboNomProduit.getValue();
        ps.GetProductByName(SelectedProductName);
        Produit produit= ps.GetProductByName(SelectedProductName);
        if (produit!= null)
        {
            System.out.println("Produit trouvé : "+ produit);
        }
        else
        {
            System.out.println("Produit non trouvé.");
        }*/
    }
    /***************************************************************/
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
    /************************************************************/
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

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    /*fonction pour charger image*/
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
    /**fonction pour recuperer les noms  categorie selon id + Affichage combo Box*****************************************/

    public void DisplayCategoriesInComboBox()
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
    /*****Fonction Initialize*************/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DisplayCategoriesInComboBox();
        DisplayProductsInComboBox();
        CamboNomProduit.setOnAction ((ActionEvent event) -> {
            ReturnProduitAfterChoosingItFromComboBox(); } ) ;
        ComboCategorieProduitUpdate.setOnAction((ActionEvent event)-> {
            ReturnNewSelectedCategory();
        }); }


}