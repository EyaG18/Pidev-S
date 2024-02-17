package com.example.pidev_v1.entities;
import java.util.HashMap;
import java.util.Map;

public class Panier {
   private int Id_Panier;
   //private user utilisateur ;
   private Map<Produit, Integer> produitsQuantite;

   public Panier(Map<Produit, Integer> produitsQuantite) {
      this.produitsQuantite = produitsQuantite;
   }

   public Map<Produit, Integer> getProduitsQuantite() {
      return produitsQuantite;
   }

   public void setProduitsQuantite(Map<Produit, Integer> produitsQuantite) {
      this.produitsQuantite = produitsQuantite;
   }

   @Override
   public String toString() {
      return "Panier{" +
              "Id_Panier=" + Id_Panier +
              ", produitsQuantite=" + produitsQuantite +
              '}';
   }
}
