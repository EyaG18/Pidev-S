package com.example.pidev_v1;

import com.example.pidev_v1.entities.Catégorie;
import com.example.pidev_v1.services.CategorieService;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

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
    CategorieService cs = new CategorieService();
    private String newCategoryName;

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
           DisplayCategoriesScene();

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

    }



    /*********************Btn referesh **********************************/
    @FXML
    void RefreshCategory(ActionEvent event) {

    }
/**************Bouton update actegorie Eya *************************/
    @FXML
    void BtnUpdateCat(ActionEvent event) {
        TableViewCategory.setOnMouseClicked(event1 -> {
            try {
                // Vérifiez si un clic de souris a été effectué avec le bouton gauche
                if (event1.getButton().equals(MouseButton.PRIMARY) && event1.getClickCount() == 1) {
                    // Obtenez la ligne sélectionnée dans la TableView
                    String oldCategoryName = String.valueOf(TableViewCategory.getSelectionModel().getSelectedItem());
                    String newCategoryName = CategoryFieldText.getText();
                    if (newCategoryName.isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur lors de la modification !");
                        alert.setContentText("Le nom de la catégorie ne peut pas être vide.");
                        alert.showAndWait();
                        return;
                    }
                    // Modifier la catégorie en utilisant l'ancien nom
                    cs.UpdateCategoryByName(oldCategoryName, newCategoryName);
                    // Affichage d'un message de succès
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Succès !");
                    alert.setContentText("Catégorie modifiée avec succès !");
                    alert.showAndWait();
                    DisplayCategoriesScene();
                    // Effacer le champ de texte après la modification
                    CategoryFieldText.clear();
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur !");
                alert.setContentText("Une erreur s'est produite : " + e.getMessage());
                alert.showAndWait();
            }
        });

    }





    ObservableList<Catégorie> ListCategoryObservable= FXCollections.observableArrayList();

    private static int call(TableColumn.CellDataFeatures<Catégorie, Integer> cellData) {
        return cellData.getValue().getId_CatégorieC();
    }


    @FXML
    private void handleButtonAction(ActionEvent event) {

        if(event.getSource() == BtnAjouterCategory){
            insertRecordCategory();
        }else if (event.getSource() == BtnModifierCategory){
            updateRecordCategory();
        }else if(event.getSource() == BtnSupprimCategory){
            deleteButtonCategory();
        }


    }


    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        //DisplayCategoriesScene();
        showCategories();
    }

    private void deleteButtonCategory() {
    }

    private void updateRecordCategory() {
        
    }

    private void insertRecordCategory() {
        String categoryName = CategoryFieldText.getText();
        String queryCat = "INSERT INTO catégorie (NomCatégorie) VALUES ('" + categoryName + "')";
        executeQueryCat(queryCat);
        DisplayCategoriesScene();
    }


    private void executeQueryCat(String queryCat) {

        MyDataBase ct = new MyDataBase();
        Connection cnx= ct.getCnx();
        Statement st;
        try{
            st = cnx.createStatement();
            st.executeUpdate(queryCat);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void DisplayCategoriesScene() {
        MyDataBase ct = new MyDataBase();
        Connection cnx = ct.getCnx();
        String queryCategory = "SELECT * FROM catégorie";
        try {
            Statement statement = cnx.createStatement();
            ResultSet QueryOutput = statement.executeQuery(queryCategory);
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
            ColumnIdCategory.setCellValueFactory(new PropertyValueFactory<>("id_Catégorie")); // Assurez-vous que le getter est nommé getId_Catégorie dans votre classe Catégorie
            TableViewCategory.getColumns().add(ColumnIdCategory); // Ajoutez la colonne à votre TableView

            // Configuration de la colonne pour afficher le nom de la catégorie
            ColumnNameCategory.setCellValueFactory(new PropertyValueFactory<Catégorie, String>("NomCatégorie"));

            // Définir les éléments dans le TableView
            TableViewCategory.setItems(ListCategoryObservable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public TextField getCategoryFieldText() {
        return CategoryFieldText;
    }

    public void setCategoryFieldText(TextField categoryFieldText) {
        CategoryFieldText = categoryFieldText;
    }

    public TableColumn<Catégorie, String> getColumnNameCategory() {
        return ColumnNameCategory;
    }

    public void setColumnNameCategory(TableColumn<Catégorie, String> columnNameCategory) {
        ColumnNameCategory = columnNameCategory;
    }

    public TableView<Catégorie> getTableViewCategory() {
        return TableViewCategory;
    }

    public void setTableViewCategory(TableView<Catégorie> tableViewCategory) {
        TableViewCategory = tableViewCategory;
    }

    public void showCategories() {
        ObservableList<Catégorie> listCategories = getCategoryList();

        //ColumnIdCategory.setCellValueFactory(new PropertyValueFactory<>("Id_Catégorie"));
        ColumnNameCategory.setCellValueFactory(new PropertyValueFactory<>("NomCatégorie"));
        // Assurez-vous de remplacer "author", "year" et "pages" par les noms de vos colonnes appropriés

        TableViewCategory.setItems(listCategories);
    }

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
                // Assurez-vous de remplacer "id", "title", "author", "year", "pages" par les noms de vos colonnes appropriés
                ListCategory.add(cat);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ListCategory;
    }




}
