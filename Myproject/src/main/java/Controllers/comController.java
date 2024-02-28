package Controllers;

import entities.Avis;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class comController {

    @FXML
    private TextField commentTF;

    @FXML
    private ImageView noteA;

    @FXML
    private ImageView noteB;

    @FXML
    private ImageView noteC;

    @FXML
    private ImageView noteD;

    @FXML
    private ImageView noteE;

    @FXML
    private ImageView pdp;

    @FXML
    private Label username;
    private Avis avisClique;
    public void setAvisInit(Avis DataProduit){
        this.avisClique=DataProduit;
    }

}
