package com.example.pidev_v1.entities;

public class Catégorie {

    public int Id_Catégorie;
    public String NomCatégorie ;

    public Catégorie() {
    }

    public Catégorie(String NomCatégorie) {

        this.NomCatégorie = NomCatégorie;
    }

    public Catégorie(int Id_Catégorie, String NomCatégorie) {

        this.Id_Catégorie=Id_Catégorie;
        this.NomCatégorie=NomCatégorie;

    }

    public int getId_CatégorieC() {
        return this.Id_Catégorie;
    }

    public void setId_Catégorie(int id_Catégorie) {
        Id_Catégorie = id_Catégorie;
    }

    public String getNomCatégorie() {
        return NomCatégorie;
    }

    public void setNomCatégorie(String nomCatégorie) {
        NomCatégorie = nomCatégorie;
    }





    @Override
    public String toString() {
        return "Catégorie{" +
                "Id_Catégorie=" + Id_Catégorie +
                ", NomCatégorie='" + NomCatégorie + '\'' +
                '}';
    }
}
