package com.example.pidev_v1.entities;




import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class Commande {
    private int id_commande;
    private int Id_Panier;
    private int id_user;
    private User user;
    Status statut;
    private int Reference;
    private Panier panier;
    private LocalDateTime date; // Changed to LocalDateTime

    private boolean livrable;
    private float Prix_total;

    public Commande(int reference, int id_user, boolean livrable, Status status) {
        this.Reference = reference;
        this.id_user = id_user;
        this.date = java.time.LocalDateTime.now(); // Set date to current date and time
        this.livrable = livrable;
        this.statut = status;
    }



    public int getId_commande() {
        return id_commande;
    }

    public User getUser() {
        return user;
    }

    public Commande(int Reference, int Id_Panier, int id_user, boolean livrable, Status status,float prix_total) {
        //User user=new User();
        this.Reference = Reference;
        this.Id_Panier = Id_Panier;
        this.id_user = id_user;
        this.date = java.time.LocalDateTime.now(); // Set date to current date and time
        this.livrable = livrable;
        this.statut = status;
        this.Prix_total= prix_total;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public float getPrix_total() {
        return Prix_total;
    }

    public void setPrix_total(float prix_total) {
        Prix_total = prix_total;
    }

    public Commande(int reference, int idClient, int panier, boolean livrable, LocalDateTime date) {

    }

    public Commande(int reference, int idClient, int panier, LocalDateTime date, boolean isAdomicile, Status statut) {
    }


    public Status getStatut() {
        return statut;
    }

    public void setStatut(Status statut) {
        this.statut = statut;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) { // Changed to LocalDateTime
        this.date = date;
    }


    public int getId_Panier() {
        return Id_Panier;
    }

    public void setId_Panier(int id_Panier) {
        Id_Panier = id_Panier;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public Commande() {

    }


    // Getters and setters
    public int getReference() {
        return Reference;
    }

    public void setReference(int Reference) {
        this.Reference = Reference;
    }


    public int getIdUser() {
        return id_user;
    }

    public void setIdUser(int user) {
        this.id_user = user;
    }


    public boolean isLivrable() {
        return livrable;
    }

    public void setLivrable(boolean livrable) {
        this.livrable = livrable;
    }

    @Override
    public String toString() {
        return "Commande{" +
                ", Reference=" + Reference +
                ", Client "+user+
                ", statut=" + statut +
                ", date=" + date +
                ", livrable=" + livrable +
                '}';
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setNomUser(String nomuser) {
        user.setNomuser(nomuser); // Assurez-vous que votre classe User a une méthode setNomuser(String nomuser)
    }


}
