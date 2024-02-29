package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Panier;
import com.example.pidev_v1.entities.Produit;
import com.example.pidev_v1.entities.User;

public interface IPanier {

    public void ajouterProduitDansPanier(Produit produit, int quantite);

    public void supprimerProduitDuPanier(Produit produit);


    public void creerPanier(User p , double tot);

    public void creerPanierById(int i, double tot);

}
