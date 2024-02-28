package com.example.pidev_v1.entities;
import java.util.Map;
public class Panier {
   private int Id_Panier;
   private User utilisateurPan;
   private double totalPanier;

   private Produit produit;

   private int QuantiteParProduit;

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
}
