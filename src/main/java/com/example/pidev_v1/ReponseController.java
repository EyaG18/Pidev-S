package com.example.pidev_v1;




import com.example.pidev_v1.entities.Reclamation;
import com.example.pidev_v1.entities.Reponse;
import com.example.pidev_v1.services.ReclamationService;
import com.example.pidev_v1.services.ReponseServices;
import com.jfoenix.controls.JFXButton;
//import entities.Reclamation;
//import entities.Reponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReponseController {


    ReponseServices ser = new  ReponseServices();
    ReclamationService sr = new ReclamationService();



    @FXML
    private TextArea ReponseTF;

    @FXML
    private JFXButton confirmerReponseB;

    @FXML
    private TextField recupererID_TF;

    @FXML
    private TextField recupererdateTF;

    @FXML
    private TextField recupererstatuTF;

    @FXML
    private TextField recuperetypeTF;

    public void setRecupererID_TF(String recupererID_TF) {
        this.recupererID_TF.setText(recupererID_TF);
    }


    public void setRecupererstatuTF(String recupererstatuTF) {
        this.recupererstatuTF.setText(recupererstatuTF);
    }

    public void setRecuperetypeTF(String recuperetypeTF) {
        this.recuperetypeTF.setText(recuperetypeTF);
    }

    @FXML
    void confirmer(ActionEvent event) throws ParseException {


        int id=ser.repurerID_reclamation(recupererID_TF.getText());




        ser.add(new Reponse(id,ReponseTF.getText()));
        sr.updatepersonalise(new Reclamation(id,recupererID_TF.getText(),recupererstatuTF.getText(),recupererstatuTF.getText()));



    }

}

