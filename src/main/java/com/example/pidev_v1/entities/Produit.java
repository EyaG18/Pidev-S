package com.example.pidev_v1.entities;

public class Produit {

private  int Id_Produit;
private Catégorie categorie;

private String NomP;
private float PrixP ;
private int QteP ;
private int QteSeuilP ;
private String ImageP;


    public Produit(Catégorie categorie, String nomP, float prixP, int qteP, int qteSeuilP, String imageP) {
        this.categorie = categorie;
        NomP = nomP;
        PrixP = prixP;
        QteP = qteP;
        QteSeuilP = qteSeuilP;
        ImageP = imageP;
    }

    public int getId_Produit() {
        return Id_Produit;
    }

    public void setId_Produit(int id_Produit) {
        Id_Produit = id_Produit;
    }

    public Catégorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Catégorie categorie) {
        this.categorie = categorie;
    }

    public String getNomP() {
        return NomP;
    }

    public void setNomP(String nomP) {
        NomP = nomP;
    }

    public float getPrixP() {
        return PrixP;
    }

    public void setPrixP(float prixP) {
        PrixP = prixP;
    }

    public int getQteP() {
        return QteP;
    }

    public void setQteP(int qteP) {
        QteP = qteP;
    }

    public int getQteSeuilP() {
        return QteSeuilP;
    }

    public void setQteSeuilP(int qteSeuilP) {
        QteSeuilP = qteSeuilP;
    }

    public String getImageP() {
        return ImageP;
    }

    public void setImageP(String imageP) {
        ImageP = imageP;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "Id_Produit=" + Id_Produit +
                ", categorie=" + categorie +
                ", NomP='" + NomP + '\'' +
                ", PrixP=" + PrixP +
                ", QteP=" + QteP +
                ", QteSeuilP=" + QteSeuilP +
                ", ImageP='" + ImageP + '\'' +
                '}';
    }
}
