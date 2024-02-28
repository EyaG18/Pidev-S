package Controllers;

import Services.ServiceAvis;
import com.jfoenix.controls.JFXButton;
import entities.Avis;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class InterfaceFrontAvisController {
int i;
ServiceAvis sa=new ServiceAvis();
    @FXML
    private BorderPane borderpane;
    @FXML
    private JFXButton B1;

    @FXML
    private JFXButton B2;

    @FXML
    private JFXButton B3;

    @FXML
    private JFXButton B4;

    @FXML
    private JFXButton B5;
    @FXML
    private ImageView etoile1;

    @FXML
    private ImageView etoile2;

    @FXML
    private ImageView etoile3;

    @FXML
    private ImageView etoile4;

    @FXML
    private ImageView etoile5;

    @FXML
    private ImageView etoileC1;

    @FXML
    private ImageView etoileC2;

    @FXML
    private ImageView etoileC3;

    @FXML
    private ImageView etoileC4;

    @FXML
    private ImageView etoileC5;
    @FXML
    private Label recuperer_IDL;

    @FXML
    private JFXButton affichercommentB;

    @FXML
    private
    TextField commentaireTF;
    @FXML
    private Label prod_name;



    @FXML
    void noteA(ActionEvent event) {
        etoileC1.setVisible(true);
        etoileC2.setVisible(false);
        etoileC3.setVisible(false);
        etoileC4.setVisible(false);
        etoileC5.setVisible(false);
        i=1;
    }

    @FXML
    void noteB(ActionEvent event) {
        etoileC1.setVisible(true);
        etoileC2.setVisible(true);
        etoileC3.setVisible(false);
        etoileC4.setVisible(false);
        etoileC5.setVisible(false);
        i=2;
    }

    @FXML
    void noteC(ActionEvent event) {
        etoileC1.setVisible(true);
        etoileC2.setVisible(true);
        etoileC3.setVisible(true);
        etoileC4.setVisible(false);
        etoileC5.setVisible(false);
        i=3;

    }

    @FXML
    void noteD(ActionEvent event) {
        etoileC1.setVisible(true);
        etoileC2.setVisible(true);
        etoileC3.setVisible(true);
        etoileC4.setVisible(true);
        etoileC5.setVisible(false);
        i=4;

    }

    @FXML
    void noteE(ActionEvent event) {
        etoileC1.setVisible(true);
        etoileC2.setVisible(true);
        etoileC3.setVisible(true);
        etoileC4.setVisible(true);
        etoileC5.setVisible(true);
        i=5;

    }

    @FXML
    void addBtnP(ActionEvent event) {
    }
    @FXML
    void affichercCommentaire(ActionEvent event) {
       /* try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Commentaire.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Ajout Réponse");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors du chargement de la vue de commentaire", e);
        }*/
    }
    @FXML
    void close(ActionEvent event) {

    }

    @FXML
    void commenter(ActionEvent event) {
        int idc= Integer.parseInt(recuperer_IDL.getText());
        int idp=sa.repurerID_produit(prod_name.getText());
        String com=commentaireTF.getText();
        sa.add(new Avis(idc,idp,com,i));

    }



}
