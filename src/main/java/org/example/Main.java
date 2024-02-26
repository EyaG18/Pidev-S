package org.example;
import entities.Fournisseur;
import service.FournisseurService;
import entities.Offre;
import service.OffreService;
import utils.DataSource;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        //Offre offre1=new Offre(1,13,LocalDate.of(2022, 1, 17),LocalDate.of(2022, 3, 8),"20%","ZBAYDAR");
        //OffreService OS=new OffreService();
        Fournisseur fournisseur1=new Fournisseur(1,13,"BO3RI","BO4NA","az");
        FournisseurService FS=new FournisseurService();
        FS.add(fournisseur1);
       // Fournisseur fournisseur1=new Fournisseur(2,"YALLA","25911783","aziz@esprit.com");
        //FournisseurService FS= new FournisseurService();

        //FS.delete(fournisseur1);
        //FS.readAll().forEach(System.out::println);
        // OS.add(offre1);
       // OS.readAll().forEach(System.out::println);
        //OS.readById(1);
     //   int idOffreToRetrieve = 1; // Replace with the desired idOffre
       // Offre offre = OS.readById(idOffreToRetrieve);

        //if (offre != null) {
          //  System.out.println("Offre found: " + offre);
        //} else {
          //  System.out.println("No Offre found with idOffre: " + idOffreToRetrieve);
       // }
        //Offre offre2=new Offre(2,15,LocalDate.of(2000, 1, 17),LocalDate.of(2000, 3, 8),"20%","DLEL");

        //OS.update(offre1);
      //  OS.delete(offre1);
    }

    }
