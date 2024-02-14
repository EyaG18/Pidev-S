package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Produit;

public interface IPanier {

    public void addProduit(Produit produit, int quantite_panier);
    public void removeProduit(Produit produit);
    public void updateQuantiteProduit(Produit produit, int nouvelleQuantite);



}
