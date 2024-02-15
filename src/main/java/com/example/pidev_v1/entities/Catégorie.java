package com.example.pidev_v1.entities;

public class Catégorie {

    private int Id_Catégorie;
    private String NomCatégorie ;

    public Catégorie() {
    }

    public Catégorie(String nomCatégorie) {
        NomCatégorie = nomCatégorie;
    }

    public Catégorie(int idCategorie, String nomCatégorie) {
    }

    public int getId_CatégorieC() {
        return Id_Catégorie;
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
