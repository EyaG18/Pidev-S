package Controllers;



import Services.ServiceAvis;
import com.jfoenix.controls.JFXButton;
import entities.Avis;
import entities.Reponse;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class InterfceAvisController implements Initializable {

    ServiceAvis sa=new ServiceAvis();
    int i=-1;
    @FXML
    private Label ajoutL;

    @FXML
    private Label modifierL;

    @FXML
    private JFXButton ajoutaviB;

    @FXML
    private JFXButton actualiseraviB;


    @FXML
    private AnchorPane anchorAvis;

    @FXML
    private TextArea commentaireTF;
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
    private JFXButton ajoutB;
    @FXML
    private JFXButton modifieraviB;
    @FXML
    private JFXButton supprimeraviB;
    @FXML
    private Label recupererID;
    @FXML
    private TextField nomPTF;
    @FXML
    private ImageView ajoutImage;
    @FXML
    private JFXButton confirmerB2;

    @FXML
    private ImageView modifierI;

    @FXML
    private Label modifierl;


    @FXML
    private TableColumn<Avis, String> commentaireC3;
    @FXML
    private TableColumn<Avis, String> nomPC2;

    @FXML
    private TableColumn<Avis, String> nomUC1;

    @FXML
    private TableColumn<Avis, Integer> noteC4;

    @FXML
    private TableView<Avis> table_avis;

    public void setCommentaireTF(String commentaireTF) {
        this.commentaireTF.setText(commentaireTF);
    }

    public void setNomPTF(String nomPTF) {
        this.nomPTF.setText(nomPTF);
    }

    public TextArea getCommentaireTF() {
        return commentaireTF;
    }

    public TextField getNomPTF() {
        return nomPTF;
    }

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
    public void refreshtable_avis() {
        table_avis.getItems().clear();
        table_avis.getItems().addAll(sa.readAll());
    }
    @FXML
    void actualiserAvis(ActionEvent event) {
        refreshtable_avis();
    }

    @FXML
    void ajoutanchor(ActionEvent event) {
         anchorAvis.setVisible(true);
         ajoutL.setVisible(true);
         ajoutaviB.setVisible(true);
         ajoutImage.setVisible(true);

    }


    @FXML
    void modifierAnchor(ActionEvent event) {
        anchorAvis.setVisible(true);
        modifierL.setVisible(true);
        confirmerB2.setVisible(true);
        modifierI.setVisible(true);
        setNomPTF( table_avis.getSelectionModel().getSelectedItem().getNomP());
        setCommentaireTF(table_avis.getSelectionModel().getSelectedItem().getCommentaire());
        int n=table_avis.getSelectionModel().getSelectedItem().getNote();
        switch (n)
        {
            case(1):
                etoileC1.setVisible(true);
                etoileC2.setVisible(false);
                etoileC3.setVisible(false);
                etoileC4.setVisible(false);
                etoileC5.setVisible(false);
                i=1;
                break;
            case(2):
                etoileC1.setVisible(true);
                etoileC2.setVisible(true);
                etoileC3.setVisible(false);
                etoileC4.setVisible(false);
                etoileC5.setVisible(false);
                i=2;
                break;
            case(3):
                etoileC1.setVisible(true);
                etoileC2.setVisible(true);
                etoileC3.setVisible(true);
                etoileC4.setVisible(false);
                etoileC5.setVisible(false);
                i=3;
                break;
            case(4):
                etoileC1.setVisible(true);
                etoileC2.setVisible(true);
                etoileC3.setVisible(true);
                etoileC4.setVisible(true);
                etoileC5.setVisible(false);
                i=4;
                break;
            case(5):
                etoileC1.setVisible(true);
                etoileC2.setVisible(true);
                etoileC3.setVisible(true);
                etoileC4.setVisible(true);
                etoileC5.setVisible(true);
                i=5;


        }
    }

    @FXML
    void modifieravis(ActionEvent event) {
        int idc= Integer.parseInt(recupererID.getText());
        int idp= sa.repurerID_produit(nomPTF.getText());
        int ida=sa.repurerID_avis(idp,idc);
        sa.update(new Avis(ida,idc,idp,commentaireTF.getText(),i));
        anchorAvis.setVisible(false);
        modifierL.setVisible(false);
        confirmerB2.setVisible(false);
        modifierI.setVisible(false);

    }



    @FXML
    void ajouteravis(ActionEvent event) {

        int id= Integer.parseInt(recupererID.getText());
        System.out.println(id);
        String commentaire=commentaireTF.getText();
        System.out.println(i);
        int idP=sa.repurerID_produit(nomPTF.getText());
        sa.add(new Avis(id,idP,commentaire,i));
        /********/
        anchorAvis.setVisible(false);
        ajoutL.setVisible(false);
        ajoutaviB.setVisible(false);
        ajoutImage.setVisible(false);
    }

    @FXML
    void supprimeravi(ActionEvent event) {
        try {
            Avis avis=new Avis(table_avis.getSelectionModel().getSelectedItem().getNom(),
                               table_avis.getSelectionModel().getSelectedItem().getNomP(),
                               table_avis.getSelectionModel().getSelectedItem().getCommentaire(),
                               table_avis.getSelectionModel().getSelectedItem().getNote());
            sa.delete(avis);
            refreshtable_avis();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Avis> list= sa.readAll();
        table_avis.setItems(list);

        nomUC1.setCellValueFactory(new PropertyValueFactory<Avis,String>("nom"));
        nomPC2.setCellValueFactory(new PropertyValueFactory<Avis,String>("nomP"));
        commentaireC3.setCellValueFactory(new PropertyValueFactory<Avis,String>("commentaire"));
        noteC4.setCellValueFactory(new PropertyValueFactory<Avis,Integer>("note"));


    }
}

