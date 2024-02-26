package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Panier;
import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.entities.User;

import java.util.HashMap;
import java.util.Map;

public interface IPanier {

    public void ajouterProduitDansPanier(Produit produit, int quantite);

    public void supprimerProduitDuPanier(Produit produit);


    public void creerPanier(User p , double tot);

    public void CreatePanierSsTotal(User p , Map<Produit, Integer> mapProduitsDansPanier);

    public void CreatePanierAvecTotal(User p ,  Map<Produit, Integer> mapProduitsDansPanier);

    public void CreateAllPanier(User p , Map<Produit,Integer> mapProduitsDansPanier );

    public void creerPanierById(int i, double tot);
    public void CreatePanierForuserOnly(User p);

    public void creerPanierbyIdUser(int idc);

}
