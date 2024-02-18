package org.example;

import com.example.pidev_v1.entities.Commande;
import com.example.pidev_v1.entities.Livraison;
import com.example.pidev_v1.services.CommandeService;
import com.example.pidev_v1.services.LivraisonService;
import utlis.DataSource;

import java.util.Date;
import java.util.List;

public class Main {

        public static void main(String[] args) {

                DataSource ds =new DataSource();
                CommandeService cs = new CommandeService();

                // Test CommandeService
                CommandeService commandeService = new CommandeService();

                // Test addCommande method
                Commande commande = new Commande("Produit 1", 5, Commande.status_com.en_attente, new Date());
                System.out.println("Adding commande: " + commande);
                int commandeId = commandeService.Add(commande);
                System.out.println("Added commande with ID: " + commandeId);

                // Test afficherCommande method
                List<Commande> commandes = commandeService.Afficher();
                System.out.println("Commandes:");
                for (Commande c : commandes) {
                        System.out.println(c);
                }

                // Test deleteCommande method
                Commande commandeToDelete = commandeService.getById(commandeId);
                System.out.println("Deleting commande: " + commandeToDelete);
                commandeService.Delete(commandeToDelete);
                System.out.println("Deleted commande with ID: " + commandeId);

                // Test modifyCommande method
                Commande modifiedCommande = new Commande("Modified Produit 1", 10, Commande.status_com.traiter, new Date());
                System.out.println("Modifying commande: " + modifiedCommande);
                commandeService.Modify(modifiedCommande);
                System.out.println("Modified commande with ID: " + modifiedCommande.getId_commande());

                // Test LivraisonService
                LivraisonService livraisonService = new LivraisonService();

                // Test addLivraison method
                Livraison livraison = new Livraison(Livraison.Status_livraison.en_attente, new Date(), 10.0f);
                System.out.println("Adding livraison: " + livraison);
                int livraisonId = livraisonService.Add(livraison);
                System.out.println("Added livraison with ID: " + livraisonId);

                // Test afficherLivraison method
                List<Livraison> livraisons = livraisonService.Afficher();
                System.out.println("Livraisons:");
                for (Livraison l : livraisons) {
                        System.out.println(l);
                }

                // Test deleteLivraison method
                Livraison livraisonToDelete = livraisonService.getById(livraisonId);
                System.out.println("Deleting livraison: " + livraisonToDelete);
                livraisonService.Delete(livraisonToDelete);
                System.out.println("Deleted livraison with ID: " + livraisonId);

                // Test modifyLivraison method
                Livraison modifiedLivraison = new Livraison(Livraison.Status_livraison.traiter, new Date(), 20.0f);
                System.out.println("Modifying livraison: " + modifiedLivraison);
                livraisonService.Modify(modifiedLivraison);
                System.out.println("Modified livraison with ID: " + modifiedLivraison.getId_livraison());
        }
}