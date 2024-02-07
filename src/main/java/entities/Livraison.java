package entities;
import java.util.Date;


public class Livraison {
    private int id_livraison,	id_commande	,id_user;
    private Status_livraison statusLivraison;
    private Date date_livraison= new Date();

    private float prix_livraison;
    public enum Status_livraison{
        en_attente, traiter, annuler
    }

    public Livraison() {}

    public Livraison(int id_livraison, int id_commande, int id_user, Status_livraison statusLivraison, Date date_livraison, float prix_livraison) {
        this.id_livraison = id_livraison;
        this.id_commande = id_commande;
        this.id_user = id_user;
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
