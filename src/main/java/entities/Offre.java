package entities;

import java.time.LocalDate;

public class Offre {
    private int idOffre;
    private int Id_Produit;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private String reduction;
    private String titre_Offre;
    private String selectedProductId;


    public Offre(LocalDate value, LocalDate dateFinTFValue, String text, String titreOffreTFText) {
    }

    public Offre(int idOffre, int id_Produit, LocalDate date_debut, LocalDate date_fin, String reduction, String titre_Offre) {
        this.idOffre = idOffre;
        Id_Produit = id_Produit;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.reduction = reduction;
        this.titre_Offre = titre_Offre;
    }

    public Offre(int id_Produit, LocalDate date_debut, LocalDate date_fin, String reduction, String titre_Offre) {
        Id_Produit = id_Produit;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.reduction = reduction;
        this.titre_Offre = titre_Offre;
    }

    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public int getId_Produit() {
        return Id_Produit;
    }

    public void setId_Produit(int id_Produit) {
        Id_Produit = id_Produit;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public String getReduction() {
        return reduction;
    }

    public void setReduction(String reduction) {
        this.reduction = reduction;
    }

    public String getTitre_Offre() {
        return titre_Offre;
    }

    public void setTitre_Offre(String titre_Offre) {
        this.titre_Offre = titre_Offre;
    }

    @Override
    public String toString() {
        return "Offre{" +
                "idOffre=" + idOffre +
                ", Id_Produit=" + Id_Produit +
                ", date_debut=" + date_debut +
                ", date_fin=" + date_fin +
                ", reduction='" + reduction + '\'' +
                ", titre_Offre='" + titre_Offre + '\'' +
                '}';
    }
}
