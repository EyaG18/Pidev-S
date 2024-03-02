package com.example.pidev_v1.entities;

public class Panier_Produit {

   private int IdPanier_Produit;

   private Produit produit;

   private Panier panier;

   private int QuantiteParProduit;

   public Panier_Produit(int idPanier_Produit, Produit produit, Panier panier, int quantiteParProduit) {
      IdPanier_Produit = idPanier_Produit;
      this.produit = produit;
      this.panier = panier;
      QuantiteParProduit = quantiteParProduit;
   }

   public Panier_Produit(Produit produit, Panier panier, int quantiteParProduit) {
      this.produit = produit;
      this.panier = panier;
      QuantiteParProduit = quantiteParProduit;
   }

   public int getIdPanier_Produit() {
      return IdPanier_Produit;
   }

   public void setIdPanier_Produit(int idPanier_Produit) {
      IdPanier_Produit = idPanier_Produit;
   }

   public Produit getProduit() {
      return produit;
   }

   public void setProduit(Produit produit) {
      this.produit = produit;
   }

   public Panier getPanier() {
      return panier;
   }

   public void setPanier(Panier panier) {
      this.panier = panier;
   }

   public int getQuantiteParProduit() {
      return QuantiteParProduit;
   }

   public void setQuantiteParProduit(int quantiteParProduit) {
      QuantiteParProduit = quantiteParProduit;
   }
}
