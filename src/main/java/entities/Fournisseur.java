package entities;

public class Fournisseur {
    private int id_fournisseur;
    private int Id_Produit;
    private String nom_fournisseur;
    private String num_fournisseur;
    private String adresse_fournisseur;

    public Fournisseur() {
    }

    public Fournisseur(int id_fournisseur, int id_Produit, String nom_fournisseur, String num_fournisseur, String adresse_fournisseur) {
        this.id_fournisseur = id_fournisseur;
        Id_Produit = id_Produit;
        this.nom_fournisseur = nom_fournisseur;
        this.num_fournisseur = num_fournisseur;
        this.adresse_fournisseur = adresse_fournisseur;
    }

    public Fournisseur(int id_Produit, String nom_fournisseur, String num_fournisseur, String adresse_fournisseur) {
        Id_Produit = id_Produit;
        this.nom_fournisseur = nom_fournisseur;
        this.num_fournisseur = num_fournisseur;
        this.adresse_fournisseur = adresse_fournisseur;
    }

    public int getId_fournisseur() {
        return id_fournisseur;
    }

    public void setId_fournisseur(int id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    public int getId_Produit() {
        return Id_Produit;
    }

    public void setId_Produit(int id_Produit) {
        Id_Produit = id_Produit;
    }

    public String getNom_fournisseur() {
        return nom_fournisseur;
    }

    public void setNom_fournisseur(String nom_fournisseur) {
        this.nom_fournisseur = nom_fournisseur;
    }

    public String getNum_fournisseur() {
        return num_fournisseur;
    }

    public void setNum_fournisseur(String num_fournisseur) {
        this.num_fournisseur = num_fournisseur;
    }

    public String getAdresse_fournisseur() {
        return adresse_fournisseur;
    }

    public void setAdresse_fournisseur(String adresse_fournisseur) {
        this.adresse_fournisseur = adresse_fournisseur;
    }

    @Override
    public String toString() {
        return "Fournisseur{" +
                "id_fournisseur=" + id_fournisseur +
                ", Id_Produit=" + Id_Produit +
                ", nom_fournisseur='" + nom_fournisseur + '\'' +
                ", num_fournisseur='" + num_fournisseur + '\'' +
                ", adresse_fournisseur='" + adresse_fournisseur + '\'' +
                '}';
    }
}
