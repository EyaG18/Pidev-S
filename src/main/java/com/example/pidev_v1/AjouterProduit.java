package com.example.pidev_v1;

import com.example.pidev_v1.entities.Catégorie;
import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.services.CategorieService;
import com.example.pidev_v1.services.ProduitService;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import com.example.pidev_v1.AfficherProduitBack;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AjouterProduit implements Initializable {

    @FXML
    private ComboBox<String> ComboCategorieProduit;

    @FXML
    private TextField NameProduitLabel;

    @FXML
    private TextField ProductPriceField;

    @FXML
    private TextField QteSeuilProductLabel;

    @FXML
    private TextField QteStockProduitLabel;

    @FXML
    private TextField productImageField;


    @FXML
    private Button btnConsulterProd;


    AfficherProduitBack af = new AfficherProduitBack();

    User user = new User();

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

    ObservableList<Catégorie> ListCategoryObservable = FXCollections.observableArrayList();

    CategorieService cs = new CategorieService();
    ProduitService ps = new ProduitService();

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
            af.ActualiserListeProduits(event);
           af.AfficherProd();
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
    }
/***********************************************/
public void loadDetailsCategorieView() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsCategorie.fxml"));
    Parent root = loader.load();
    Stage stage = new Stage();
    stage.setTitle("Détails Catégorie");
    stage.setScene(new Scene(root));
    stage.show();
}
/**************************************/
public void loadDetailsProduitsView() throws IOException
{
    FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduitCoteClient.fxml"));
    Parent root = loader.load();
    Stage stage = new Stage();
    stage.setTitle("Liste Des Prouits");
    stage.setScene(new Scene(root));
    stage.show();
}
/******************************************************/
    @FXML
    void ConsulterPageProd(MouseEvent event) {
        try {
            loadDetailsProduitsView();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    /*****************************************************************/

    @FXML
    public void initialize(URL url , ResourceBundle rb)
    {

        DisplayCategoriesInComboBox();
    }

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


    public void setInformation(User p) {
        user=p;

    }
}
