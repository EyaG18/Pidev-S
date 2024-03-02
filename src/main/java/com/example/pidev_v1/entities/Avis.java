package com.example.pidev_v1.entities;

public class Avis {



    private int ID_avis;
    private int ID_client;
    private int ID_produit;
    private String commentaire;
    private int note;
    private String nom;
    private String nomP;

    //constructeur ajout
    public Avis(int ID_client, int ID_produit, String commentaire, int note) {
        this.ID_client = ID_client;
        this.ID_produit = ID_produit;
        this.commentaire = commentaire;
        this.note = note;
    }

    //constructeur affichage
    public Avis(String nom,String nomP,String commentaire, int note) {
        this.nom=nom;
        this.nomP=nomP;
        this.commentaire = commentaire;
        this.note = note;
    }

    public Avis(int ID_avis, int ID_client, int ID_produit, String commentaire, int note) {
        this.ID_avis = ID_avis;
        this.ID_client = ID_client;
        this.ID_produit = ID_produit;
        this.commentaire = commentaire;
        this.note = note;

    }

    public int getID_avis() {
        return ID_avis;
    }

    public int getID_client() {
        return ID_client;
    }

    public int getID_produit() {
        return ID_produit;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    @Override
    public String toString() {
        return "Avis{" +
                "nom='" + nom + '\'' +
                "nomP='" + nomP + '\'' +
                ", commentaire=" + commentaire +
                ", note='" + note + '\'' +
                '}';
    }







}
