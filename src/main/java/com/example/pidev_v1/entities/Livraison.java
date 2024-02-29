package com.example.pidev_v1.entities;
import com.example.pidev_v1.entities.Commande;

import java.util.Date;


public class Livraison {
    private int id_livraison;
    //private User user;
    private Commande commande;
    private Status_livraison statusLivraison;
    private Date date_livraison= new Date();

    private float prix_livraison;
    public enum Status_livraison{
        en_attente, traiter, annuler
    }

    public Livraison() {}

    public Livraison( Commande commande,Status_livraison statusLivraison, Date date_livraison, float prix_livraison) {
        this.commande = commande;
        this.statusLivraison = statusLivraison;
        this.date_livraison = date_livraison;
        this.prix_livraison = prix_livraison;
    }

    public Livraison(Status_livraison statusLivraison, Date date_livraison, float prix_livraison) {
        this.statusLivraison = statusLivraison;
        this.date_livraison = date_livraison;
        this.prix_livraison = prix_livraison;
    }

    public int getId_livraison() {
        return id_livraison;
    }

    public void setId_livraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }

    public Commande getCommande() {
        return commande;
    }


    public Status_livraison getStatusLivraison() {
        return statusLivraison;
    }

    public void setStatusLivraison(Status_livraison statusLivraison) {
        this.statusLivraison = statusLivraison;
    }

    public Date getDate_livraison() {
        return date_livraison;
    }

    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }

    public float getPrix_livraison() {
        return prix_livraison;
    }

    public void setPrix_livraison(float prix_livraison) {
        this.prix_livraison = prix_livraison;
    }






}
