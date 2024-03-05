package com.example.pidev_v1.entities;

import java.time.LocalDate;

public class Livraison {  private int id_livraison;
    private int Reference;
    private int id_commande;
    private int id_user;
    private String AdrUser;
    private User user;
    private Status status_livraison;

    private Commande commande;
    private LocalDate date_livraison;
    private float prix_livraison;


    public Livraison() {}

    public Livraison(int id_commande, int Reference, String AdrUser,int id_user, Status status_livraison, float prix_livraison) {
        this.Reference=Reference;
        this.id_commande = id_commande;
        this.AdrUser=AdrUser;
        this.id_user=id_user;
        this.status_livraison = status_livraison;
        this.date_livraison = java.time.LocalDate.now();
        this.prix_livraison = prix_livraison;
    }

    public int getReference() {
        return Reference;
    }

    public String getAdrUser(String AdrUser) {
        return AdrUser;
    }

    public void setAdrUser(String adrUser) {
        AdrUser = adrUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date_livraison;
    }

    public void setDate(LocalDate date) {
        this.date_livraison = date_livraison;
    }

    public int getId_livraison() {
        return id_livraison;
    }

    public void setId_livraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }



    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Status getStatus() {
        return status_livraison;
    }

    public void setStatus(Status status_livraison) {
        this.status_livraison = status_livraison;
    }

    public int getReference(int Reference) {
        return Reference;
    }

    public void setReference(int reference) {
        Reference = reference;
    }

    public void setDate_livraison(LocalDate date_livraison) {
        this.date_livraison = date_livraison;
    }

    public float getPrix_livraison() {
        return prix_livraison;
    }

    public void setPrix_livraison(float prix_livraison) {
        this.prix_livraison = 8;
    }

    @Override
    public String toString() {
        return "Livraison{" +
                "Reference=" + Reference +
                ", user=" + user +
                ",addresse du client"+AdrUser+
                ", status_livraison=" + status_livraison +
                ", commande=" + commande +
                ", date=" + date_livraison +
                ", prix_livraison=" + prix_livraison +
                '}';
    }

    public void setNomUser(String nomuser) {
        user.setNomuser(nomuser); // Assurez-vous que votre classe User a une méthode setNomuser(String nomuser)
    }
}
