package com.example.pidev_v1.entities;

import java.util.Date;

public class Reponse {


    private int ID_reponse;
    private int ID_reclamation;
    private String reponse;
    private Date date_reponse;


    //constructeur afficherlist
    public Reponse(String reponse, Date date_reponse) {
        this.reponse = reponse;
        this.date_reponse = date_reponse;
    }

    //constructeur ajout
    public Reponse(int ID_reclamation,String reponse) {
        this.ID_reclamation=ID_reclamation;
        this.reponse = reponse;
    }

    //constructeur supprimer
    public Reponse(int ID_reclamation,int ID_reponse, String reponse,Date date_reponse) {
        this.ID_reclamation=ID_reclamation;
        this.ID_reponse=ID_reponse;
        this.reponse = reponse;
        this.date_reponse=date_reponse;
    }
    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public Date getDate_reponse() {
        return date_reponse;
    }

    public int getID_reponse() {
        return ID_reponse;
    }

    public int getID_reclamation() {
        return ID_reclamation;
    }

    @Override
    public String toString() {
        return "Reponse{" +
                "reponse='" + reponse + '\'' +
                ", date_reponse=" + date_reponse +
                '}';
    }


}
