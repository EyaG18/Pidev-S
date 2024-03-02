package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Panier;
import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.entities.User;

import java.util.HashMap;
import java.util.Map;

public interface IPanier {

    public void supprimerProduitDuPanier(Produit produit);

    public void CreatePanierForuserOnly(User p);


    public void creerPanierbyIdUser(int idc);

    public void createPanierAll(Panier panier);

    public Double GetTotalPanier();
}
