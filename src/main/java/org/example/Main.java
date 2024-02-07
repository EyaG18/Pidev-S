package org.example;

import entities.Commande;
import services.CommandeService;
import utlis.DataSource;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        DataSource ds =new DataSource();
        CommandeService cs = new CommandeService();
        Commande commande = new Commande("produit", 10, Commande.status_com.en_attente, new Date());

        //Commande c1 = new Commande("PANTALON, CHEMISE,LUNETTES", 2, Commande.status_com.en_attente, new Date(2023, 8, 22));
       // Commande c2 = new Commande("PANTALON,PULL", 3, Commande.status_com.traiter, new Date(2023, 9, 32));

        int ajout=cs.Add(commande);
        //int ajou2=cs.Add(c2);

    }
}