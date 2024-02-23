package com.example.pidev_v1.entities;

public class Produit {

private  int Id_Produit;
private int Id_Catégorie;
private String NomP;
private float PrixP ;
private int QteP ;
private int QteSeuilP ;
private String ImageP;

private String NomCatégorie;

    public Produit( String nomP, float prixP, int qteP, int qteSeuilP, String imageP,String nomCategorie) {

        NomP = nomP;
        PrixP = prixP;
        QteP = qteP;
        QteSeuilP = qteSeuilP;
        ImageP = imageP;
        NomCatégorie = nomCategorie;

    }




    public Produit() {
    }

    public String getNomCatégorie() {
        return NomCatégorie;
    }

    public Produit(int Id_Catégorie, String nomP, float prixP, int qteP, int qteSeuilP, String imageP) {
        this.Id_Catégorie=Id_Catégorie;
        //this.Id_Catégorie= catégorieP.getId_CatégorieC();
        NomP = nomP;
        PrixP = prixP;
        QteP = qteP;
        QteSeuilP = qteSeuilP;
        ImageP = imageP;
    }

    public Produit(int id_Produit, int id_Catégorie, String nomP, float prixP, int qteP, int qteSeuilP, String imageP) {
        Id_Produit = id_Produit;
        Id_Catégorie = id_Catégorie;
       // this.Id_Catégorie= catégorieP.getId_CatégorieC();
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

   /* public Catégorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Catégorie categorie) {
        this.categorie = categorie;
    }*/

    public int getId_Catégorie() {
        //return catégorieP.getId_CatégorieC();
        return Id_Catégorie;
    }

    public void setId_Catégorie(int id_Catégorie) {
        //catégorieP.getId_CatégorieC()=id_Catégorie;

         Id_Catégorie = id_Catégorie;
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
                ", categorie=" + Id_Catégorie +
                ", NomP='" + NomP + '\'' +
                ", PrixP=" + PrixP +
                ", QteP=" + QteP +
                ", QteSeuilP=" + QteSeuilP +
                ", ImageP='" + ImageP + '\'' +
                '}';
    }

    public void setNomCatégorie(String nomCategorie) {
    }
}
