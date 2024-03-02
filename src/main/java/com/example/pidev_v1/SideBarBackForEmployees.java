package com.example.pidev_v1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class SideBarBackForEmployees {

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
    private Button UsersBoutons;

    @FXML
    void GoToCategories(MouseEvent event) {
    }
    @FXML
    void GoToCommandesLivraison(MouseEvent event) {
    }

    @FXML
    void GoToFournieusseursOffere(ActionEvent event) {
    }

    @FXML
    void GoToProducts(MouseEvent event) {
NavigationControler.OpenAffichageProduitsBack(event,"AjouterProduit.fxml");

    }

    @FXML
    void GoToUsers(MouseEvent event) {

    }

    @FXML
    void GotoCRM(ActionEvent event) {

    }

}
