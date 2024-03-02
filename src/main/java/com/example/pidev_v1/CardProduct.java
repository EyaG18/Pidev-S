package com.example.pidev_v1;

import com.example.pidev_v1.entities.Avis;
import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.services.MyListener;
import com.example.pidev_v1.services.ProduitService;
import com.example.pidev_v1.services.ServiceAvis;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class CardProduct implements Initializable {

    @FXML
    private AnchorPane card_form;

    @FXML
    private Button prod_addBtn;

    @FXML
    private ImageView prod_imageView;

    @FXML
    private Label prod_name;

    @FXML
    private Label prod_price;

    @FXML
    private Spinner<Integer> prod_spinner;

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

    private SpinnerValueFactory<Integer> spin;

    private Produit produit;
    private Image image ;

    private int prodID;
    private String prod_image;
    private float price;

    private int ProdID;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private Alert alert;

ServiceAvis sa=new ServiceAvis();
    @FXML
    private TextField commentaireTF;

    private int qty;
    private double totalP ;
    private double pr;
    private MyListener myListener;

    private User currentUser;


    public void setUser(User user)
    {
        currentUser =user;

        System.out.println("nomcurrentUser5555555/***/"+currentUser.getId_user());

    }

    public void setDat(Produit produit , MyListener myListener) throws FileNotFoundException {
        prodID=produit.getId_Produit();
        this.produit = produit;
        this.myListener=myListener;
        InputStream imageStream = new FileInputStream(produit.getImageP());
        Image image = new Image(imageStream);
        ImageView imageView = new ImageView(image);
        prod_imageView.setImage(imageView.snapshot(null, null));
        prod_name.setText(produit.getNomP());
        prod_price.setText( String.valueOf(produit.getPrixP()) + "DT");
    }
    public void setQuantity() {
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        prod_spinner.setValueFactory(spin);
    }
    @FXML
    void addBtnP(ActionEvent event) {

    }
/********************Yasmine******************/
@FXML
void commenter(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCommentaire.fxml"));
    Parent root = loader.load();
    Stage stage = new Stage();
    stage.setTitle("Ajout Réponse");
    stage.setScene(new Scene(root));
    stage.show();
}
int i;
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
    public int getIDUser(User user)
    {
        currentUser =user;

        System.out.println("IDcurrentUser"+currentUser.getId_user());
        return currentUser.getId_user();

    }

    @FXML
    void ajouterAvis(ActionEvent event) {
System.out.println("note"+i);
       int idp=sa.repurerID_produit(prod_name.getText());
       int idc= currentUser.getId_user();
       String com=commentaireTF.getText();
       sa.add(new Avis(idc,idp,com,i));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setQuantity();

    }


}
