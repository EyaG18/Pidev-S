package com.example.pidev_v1;

import com.example.pidev_v1.entities.Catégorie;
import com.example.pidev_v1.services.CategorieService;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DetailsCategorie implements Initializable {

    @FXML
    private Button BtnActualiserCategory;

    @FXML
    private Button BtnAjouterCategory;

    @FXML
    private Button BtnModifierCategory;

    @FXML
    private Button BtnSupprimCategory;

    @FXML
    private TextField CategoryFieldText;

   @FXML
    public TableColumn<Catégorie, Integer> ColumnIdCategory;

    @FXML
    private TableColumn<Catégorie, String> ColumnNameCategory;

    @FXML
    private TableView<Catégorie> TableViewCategory;

    @FXML
    private TextField KeywordsTextLabel;

    CategorieService cs = new CategorieService();
    private String newCategoryName;
    int indexCat = -1;


    @FXML
    void GetSelectedCategory(MouseEvent event) {
          indexCat = TableViewCategory.getSelectionModel().getSelectedItem().getId_CatégorieC();

          if (indexCat <= -1)
          { return; }
          CategoryFieldText.setText(ColumnNameCategory.getCellData(indexCat).toString());
    }


    @FXML
    void BtnAddCat(ActionEvent event) {
        try {
            String categoryName = CategoryFieldText.getText();
            // Vérifier si le nom de la catégorie n'est pas vide avant d'ajouter
            if (categoryName.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur lors de l'ajout !");
                alert.setContentText("Le nom de la catégorie ne peut pas être vide.");
                alert.showAndWait();
                return; // Sortie de la fonction si le nom de la catégorie est vide
            }
            // Ajout de la catégorie
            cs.addCategory(new Catégorie(categoryName));

            // Affichage d'un message de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès !");
            alert.setContentText("Catégorie ajoutée avec succès !");
            alert.showAndWait();
           //DisplayCategoriesScene();
            showCategories();
            // Effacer le champ de texte après l'ajout
            CategoryFieldText.clear();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur !");
            alert.setContentText("Une erreur s'est produite : " + e.getMessage());
            alert.showAndWait();
        }
    }

/******************Btn supprimer Category***********************************/
    @FXML
    void BtnDeleteCat(ActionEvent event) {
        try {
            // Obtenez le nom de la catégorie sélectionnée
            String selectedCategoryName = TableViewCategory.getSelectionModel().getSelectedItem().getNomCatégorie();

            // Appelez la fonction DeleteCategoryByName pour supprimer la catégorie sélectionnée
            cs.DeleteCategoryByName(selectedCategoryName);

            // Actualisez le TableView pour refléter les modifications après la suppression
            RefreshCategory(event);
        } catch (NullPointerException e) {
            // Aucune catégorie sélectionnée
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur !");
            alert.setContentText("Aucune catégorie sélectionnée ! Veuillez sélectionner une catégorie à supprimer.");
            alert.showAndWait();

        } catch (Exception e) {
            // Toutes les autres exceptions
            showAlert("Erreur", "Erreur inattendue", "Une erreur inattendue s'est produite : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(String erreur, String aucuneCatégorieSélectionnée, String s) {Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(erreur);
        alert.setHeaderText(aucuneCatégorieSélectionnée);
        alert.setContentText(s);
        alert.showAndWait();
    }


    /*********************Btn referesh **********************************/
    @FXML
    void RefreshCategory(ActionEvent event) {
        showCategories();
    }
    /******Finalement Update Fonctionnel !!!!!!!!**********/
    @FXML
    public void BtnUpdateCat(ActionEvent event) {
        TableViewCategory.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1) {
                        // Obtenez la ligne sélectionnée dans la TableView
                        Catégorie selectedCategory = TableViewCategory.getSelectionModel().getSelectedItem();
                        if (selectedCategory == null) {
                            return; // Aucune catégorie sélectionnée, sortie de la fonction
                        }
                        String oldCategoryName = selectedCategory.getNomCatégorie(); // Assurez-vous d'avoir la méthode getNom() dans votre classe de catégorie
                        String newCategoryName = CategoryFieldText.getText();
                        if (newCategoryName.isEmpty()) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Erreur lors de la modification !");
                            alert.setContentText("Le nom de la catégorie ne peut pas être vide.");
                            alert.showAndWait();
                            return;
                        }
                        // Mettre à jour la catégorie en utilisant le service
                        cs.UpdateCategoryByName(oldCategoryName, newCategoryName);
                        // Affichage d'un message de succès
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Succès !");
                        alert.setContentText("Catégorie modifiée avec succès !");
                        alert.showAndWait();


                        //DisplayCategoriesScene();
                        showCategories();
                        // Effacer le champ de texte après la modification
                        CategoryFieldText.clear();
                    }
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur !");
                    alert.setContentText("Une erreur s'est produite : " + e.getMessage());
                    alert.showAndWait();
                }
            }
        });
    }
    /*********************************************************************/

    ObservableList<Catégorie> ListCategoryObservable= FXCollections.observableArrayList();

    private static int call(TableColumn.CellDataFeatures<Catégorie, Integer> cellData) {
        return cellData.getValue().getId_CatégorieC();
    }


    /*********************************************************************/
    @Override
    public void initialize(URL url , ResourceBundle rb)
    {

        DisplayCategoriesScene();

        ColumnNameCategory.setCellValueFactory(new PropertyValueFactory<>("NomCatégorie"));
        TableViewCategory.setItems(ListCategoryObservable);
        // Utilisez la même liste observable pour la liste filtrée
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

        // Utilisez la même liste filtrée pour la liste triée
        SortedList<Catégorie> SortedCategoryList = new SortedList<>(catégorieFilteredList);
        SortedCategoryList.comparatorProperty().bind(TableViewCategory.comparatorProperty());
        TableViewCategory.setItems(SortedCategoryList);

    }
    /*********************************************************************/

    /*********************************************************************/
    public void DisplayCategoriesScene() {
        MyDataBase ct = new MyDataBase();
        Connection cnx = ct.getCnx();
        String queryCategory = "SELECT * FROM catégorie";
        try {
            Statement statement = cnx.createStatement();
            ResultSet QueryOutput = statement.executeQuery(queryCategory);
            ListCategoryObservable.clear();
            while (QueryOutput.next()) {
                int queryCategoryId = QueryOutput.getInt("Id_Catégorie");
                String queryCategoryName = QueryOutput.getString("NomCatégorie");
                // Affichage des valeurs récupérées
                System.out.println("Id_Catégorie: " + queryCategoryId);
                System.out.println("NomCatégorie: " + queryCategoryName);
                ListCategoryObservable.add(new Catégorie(queryCategoryId, queryCategoryName));
            }
            // Configuration de la colonne pour afficher l'ID de la catégorie
           TableColumn<Catégorie, Integer> ColumnIdCategory = new TableColumn<>("ID");
          //  ColumnIdCategory.setCellValueFactory(new PropertyValueFactory<>("id_Catégorie"));
            // Configuration de la colonne pour afficher le nom de la catégorie
            ColumnNameCategory.setCellValueFactory(new PropertyValueFactory<Catégorie, String>("NomCatégorie"));
            // Définir les éléments dans le TableView
            TableViewCategory.setItems(ListCategoryObservable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/*********************************************************************/
    public void showCategories() {
        ObservableList<Catégorie> listCategories = getCategoryList();

        //ColumnIdCategory.setCellValueFactory(new PropertyValueFactory<>("Id_Catégorie"));
        ColumnNameCategory.setCellValueFactory(new PropertyValueFactory<>("NomCatégorie"));
        // Assurez-vous de remplacer "author", "year" et "pages" par les noms de vos colonnes appropriés

        TableViewCategory.setItems(listCategories);
    }
    /*********************************************************************/
    public ObservableList<Catégorie> getCategoryList() {

        ObservableList<Catégorie> ListCategory = FXCollections.observableArrayList();
        MyDataBase ct = new MyDataBase();
        Connection cnx= ct.getCnx();
        String query = "SELECT * FROM catégorie"; // Assurez-vous de remplacer "books" par le nom de votre table approprié
        Statement st;
        ResultSet rs;
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Catégorie cat;
            while (rs.next()) {
                cat = new Catégorie(rs.getInt("Id_Catégorie"), rs.getString("NomCatégorie"));
                ListCategory.add(cat);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ListCategory;
    }
/*********************************************************************/
void DisplayCategoriesSorted()
{

    ObservableList<Catégorie> listCategories = getCategoryList();
    ColumnNameCategory.setCellValueFactory(new PropertyValueFactory<>("NomCatégorie"));
    TableViewCategory.setItems(listCategories);

    FilteredList<Catégorie> catégorieFilteredList = new FilteredList<>(listCategories,b->true);
    KeywordsTextLabel.textProperty().addListener((observableValue, oldValue, newValue) ->
            {
                catégorieFilteredList.setPredicate(Catégorie -> {
                    // if no search value then display all data with no changes
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }
                    String searchKeyWordsCategory = newValue.toLowerCase();
                    if (Catégorie.getNomCatégorie().toLowerCase().indexOf(searchKeyWordsCategory) > -1) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });
    SortedList<Catégorie> SortedCategoryList = new SortedList<>(catégorieFilteredList);
    // Bind sorted result with table view
    SortedCategoryList.comparatorProperty().bind(TableViewCategory.comparatorProperty());
    TableViewCategory.setItems(SortedCategoryList);
}







}
