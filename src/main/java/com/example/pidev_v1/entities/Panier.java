package com.example.pidev_v1.entities;

public class Panier {
   private int Id_Panier;

   private int id_user;

   private  int Id_Produit;
   private User utilisateurPan;
   private double totalPanier=0;

   private Produit produit;

   private int QuantiteParProduit;
   private double PrixPanierUnitaire;

   public Panier() {
   }

   public Panier(int id_user, int id_Produit, int quantiteParProduit) {
      this.id_user = id_user;
      Id_Produit = id_Produit;
      QuantiteParProduit = quantiteParProduit;
   }

   public int getId_user() {
      return id_user;
   }

   public Panier(int id_user, int id_Produit, int quantiteParProduit, double prixPanierUnitaire) {
      this.id_user = id_user;
      Id_Produit = id_Produit;
      QuantiteParProduit = quantiteParProduit;
      PrixPanierUnitaire = prixPanierUnitaire;
   }

   public Panier(User utilisateurPan, Produit produit, int quantiteParProduit, double prixPanierUnitaire) {
      this.utilisateurPan = utilisateurPan;
      this.produit = produit;
      QuantiteParProduit = quantiteParProduit;
      PrixPanierUnitaire = prixPanierUnitaire;
   }

   public void setId_user(int id_user) {
      this.id_user = id_user;
   }

   public int getId_Produit() {
      return Id_Produit;
   }

   public void setId_Produit(int id_Produit) {
      Id_Produit = id_Produit;
   }

   public Panier(User utilisateurPan, Produit produit, int quantiteParProduit) {
      this.utilisateurPan = utilisateurPan;
      this.produit = produit;
      QuantiteParProduit = quantiteParProduit;
   }

   public Panier(int id_Panier, User utilisateurPan, double totalPanier, Produit produit, int quantiteParProduit) {
      Id_Panier = id_Panier;
      this.utilisateurPan = utilisateurPan;
      this.totalPanier = totalPanier;
      this.produit = produit;
      QuantiteParProduit = quantiteParProduit;
   }



   public int getId_Panier() {
      return Id_Panier;
   }

   public void setId_Panier(int id_Panier) {
      Id_Panier = id_Panier;
   }

   public User getUtilisateurPan() {
      return utilisateurPan;
   }

   public void setUtilisateurPan(User utilisateurPan) {
      this.utilisateurPan = utilisateurPan;
   }

   public double getTotalPanier() {
      return totalPanier;
   }

   public void setTotalPanier(double totalPanier) {
      this.totalPanier = totalPanier;
   }

   public Produit getProduit() {
      return produit;
   }

   public void setProduit(Produit produit) {
      this.produit = produit;
   }

   public int getQuantiteParProduit() {
      return QuantiteParProduit;
   }

   public void setQuantiteParProduit(int quantiteParProduit) {
      QuantiteParProduit = quantiteParProduit;
   }

   public Panier(User utilisateurPan, double totalPanier, Produit produit, int quantiteParProduit) {
      this.utilisateurPan = utilisateurPan;
      this.totalPanier = totalPanier;
      this.produit = produit;
      QuantiteParProduit = quantiteParProduit;
   }

   public Panier(int id_Produit, int quantiteParProduit, double prixPanierUnitaire) {
      Id_Produit = id_Produit;
      QuantiteParProduit = quantiteParProduit;
      PrixPanierUnitaire = prixPanierUnitaire;
   }

   @Override
   public String toString() {
      return "Panier{" +
              "Id_Panier=" + Id_Panier +
              ", id_user=" + id_user +
              ", Id_Produit=" + Id_Produit +
              ", QuantiteParProduit=" + QuantiteParProduit +
              ", PrixPanierUnitaire=" + PrixPanierUnitaire +
              '}';
   }
}
