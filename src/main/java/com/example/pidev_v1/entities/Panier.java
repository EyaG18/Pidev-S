package com.example.pidev_v1.entities;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Panier {
   private int Id_Panier;
   private User utilisateurPan;
   private Map<Produit, Integer> mapProduitsDansPanier; // Mapping des produits au nombre d'articles dans le panier
   private double totalPanier;
   public Panier(int id_Panier, User utilisateurPan) {
      Id_Panier = id_Panier;
      this.utilisateurPan = utilisateurPan;
      mapProduitsDansPanier = new HashMap<>();
      calculateTotalPanier();
   }

   public Panier(User utilisateurPan) {
      this.utilisateurPan = utilisateurPan;
   }

   public Panier(User utilisateurPan, Map<Produit, Integer> mapProduitsDansPanier) {
      this.utilisateurPan = utilisateurPan;
      this.mapProduitsDansPanier = mapProduitsDansPanier;
   }

   public Panier(int id_Panier, User utilisateurPan, Map<Produit, Integer> mapProduitsDansPanier) {
      Id_Panier = id_Panier;
      this.utilisateurPan = utilisateurPan;
      this.mapProduitsDansPanier = mapProduitsDansPanier;
   }

   public double getTotalPanier() {
      return totalPanier;
   }
   public void setTotalPanier(double totalPanier){
      this.totalPanier = totalPanier;
   }
   public User getUtilisateurPan() {
      return utilisateurPan;
   }
   public Map<Produit, Integer> getMapProduitsDansPanier() {
      return mapProduitsDansPanier;
   }
   public int getId_Panier() {
      return Id_Panier;
   }
   private void calculateTotalPanier() {
      double total = 0;
      for (Map.Entry<Produit, Integer> entry : mapProduitsDansPanier.entrySet()) {
         Produit produit = entry.getKey();
         int quantite = entry.getValue();
         total += produit.getPrixP() * quantite;
      }
      this.totalPanier = total;
      System.out.println(totalPanier);
   }






}
