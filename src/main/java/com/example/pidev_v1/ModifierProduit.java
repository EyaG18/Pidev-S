package com.example.pidev_v1;

import com.example.pidev_v1.entities.Catégorie;
import com.example.pidev_v1.services.ProduitService;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    void ConfirmerModifcation(MouseEvent event) {

    }

    ProduitService ps = new ProduitService();

    AfficherProduitBack AfP = new AfficherProduitBack();

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
    }
}
