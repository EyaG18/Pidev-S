package Controllers;

import Services.ServiceReclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.MainFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class welcomeControllers implements Initializable {

    MainFX main=new MainFX();

    @FXML
    private BorderPane borderpane;


    @FXML
    void gestionAvis(ActionEvent event) throws IOException {
        AnchorPane view= FXMLLoader.load(getClass().getResource("/interfaceAvis.fxml"));

        borderpane.setCenter(view);

    }

    @FXML
    void gestionReclamation(ActionEvent event) throws IOException {

        StackPane view= FXMLLoader.load(getClass().getResource("/interfaceReclamation.fxml"));

        borderpane.setCenter(view);

    }


    @FXML
    void gestionReclamationFront(ActionEvent event) throws IOException {

        StackPane view= FXMLLoader.load(getClass().getResource("/InterfaceReclamationFront.fxml"));

        borderpane.setCenter(view);
    }

    /*@FXML
    void switchFront(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FeedProduitsCoteClients.fxml"));
        Parent root= loader.load();
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Front");
        secondaryStage.setScene(new Scene(root));
        secondaryStage.show();

    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


/*public void switchForm(ActionEvent event){
    if(event.getSource()==Breclamations)
    {
        Greclamation1.setVisible(true);
        Greclamation2.setVisible(true);
        Gproduit.setVisible(false);
    } else if (event.getSource()==Bproduits) {

        Greclamation1.setVisible(false);
        Greclamation2.setVisible(false);
        Gproduit.setVisible(true);
    }

}*/



   /*@FXML
    void ajouter(ActionEvent event) {
       rs.add(new Reclamation(Integer.parseInt(clientTF.getText()),
               descriptionTA.getText(),statuTF.getText(),typeTF.getText()));

    }*/



}
