package entities;

import java.util.Date;

public class Commande {
    private int id_commande,id_user,Id_Panier ;
    private String produit;
    private float Quantite	;
    private status_com status;
    private Date Date_com= new Date();
    private boolean livrable;
    private Livraison livraison;
    public Commande(String produit, int qte, status_com statusCom, Date date) {
    }

    public enum status_com {
        en_attente, traiter, annuler
    }

    public Commande() {}



    public Commande( int id_user, int id_Panier, String produit, float quantite, status_com status, Date date_com, boolean livrable) {
        this.id_user = id_user;
        Id_Panier = id_Panier;
        this.produit = produit;
        Quantite = quantite;
        this.status = status;
        Date_com = date_com;
        this.livrable = livrable;
    }

    public int getId_commande() {
        return id_commande;
    }

    public int getId_user() {
        return id_user;
    }

    public String getProduit() {
        return produit;
    }

    public float getQuantite() {
        return Quantite;
    }

    public status_com getStatus() {
        return status;
    }

    public Date getDate_com() {
        return Date_com;
    }

    public boolean isLivrable() {
        return livrable;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public void setQuantite(float quantite) {
        Quantite = quantite;
    }

    public void setStatus(status_com status) {
        this.status = status;
    }

    public void setDate_com(Date date_com) {
        Date_com = date_com;
    }

    public void setLivrable(boolean livrable) {
        this.livrable = livrable;
    }

    public int getId_Panier() {
        return Id_Panier;
    }
}
