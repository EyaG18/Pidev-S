package com.example.pidev_v1.entities;

import java.util.Date;

public class Reclamation {



    private  int ID_reclamation;
    private int ID_client;
    private  String description;
    private Date date_reclamation;
    private String statu_reclamation;
    private String type_reclamation ;
    private String nom_client;

    public Reclamation(int ID_reclamation) {
        this.ID_reclamation = ID_reclamation;
    }

    public Reclamation(int ID_reclamation , String description, String statu_reclamation, String type_reclamation) {
        this.ID_reclamation=ID_reclamation;
        this.description = description;
        this.statu_reclamation = statu_reclamation;
        this.type_reclamation = type_reclamation;
    }
    public Reclamation( int ID_client,String description,  String type_reclamation) {
        this.ID_client=ID_client;
        this.description = description;
        this.type_reclamation = type_reclamation;
    }

    public Reclamation(String nom_client, String description,Date date_reclamation, String statu_reclamation, String type_reclamation) {

        this.nom_client=nom_client;
        this.description = description;
        this.date_reclamation = date_reclamation;
        this.statu_reclamation = statu_reclamation;
        this.type_reclamation = type_reclamation;
    }
    public Reclamation(int ID_reclamation , Date date_reclamation, String description, String statu_reclamation, String type_reclamation) {
        this.ID_reclamation=ID_reclamation;
        this.ID_client = ID_client;
        this.description = description;
        this.date_reclamation = date_reclamation;
        this.statu_reclamation = statu_reclamation;
        this.type_reclamation = type_reclamation;
    }


    public int getID_reclamation() {
        return ID_reclamation;
    }

    public int getID_client() {
        return ID_client;
    }

    public void setID_client(int ID_client) {
        this.ID_client = ID_client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_reclamation() {
        return date_reclamation;
    }



    public String getStatu_reclamation() {
        return statu_reclamation;
    }

    public void setStatu_reclamation(String statu_reclamation) {
        this.statu_reclamation = statu_reclamation;
    }

    public String getType_reclamation() {
        return type_reclamation;
    }

    public void setType_reclamation(String type_reclamation) {
        this.type_reclamation = type_reclamation;
    }


    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "nom_client='" + nom_client + '\'' +
                "description='" + description + '\'' +
                ", date_reclamation=" + date_reclamation +
                ", statu_reclamation='" + statu_reclamation + '\'' +
                ", type_reclamation='" + type_reclamation + '\'' +

                '}';
    }






}
