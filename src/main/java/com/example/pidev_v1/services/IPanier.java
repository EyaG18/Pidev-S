package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Panier;
import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.entities.User;

import java.util.List;

public interface IPanier {

    public void supprimerProduitDuPanier(Produit produit);

    public void CreatePanierForuserOnly(User p);


    public void creerPanierbyIdUser(int idc);

    public void createPanierAll(int id_user,int id_Produit,int QuantitéParProduit,double PrixPanierUnitaire);

    public Double GetTotalPanier();

    public void CreerPanierByEntities(User user,Produit produit, int Quantite);


    public double afficherSuma();
    public void truncatePanier();

    public void suprimer(Panier t);
    public List<Panier> afficherCotenuPanier();






}