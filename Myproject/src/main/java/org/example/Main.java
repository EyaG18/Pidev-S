package org.example;

import Services.ServiceAvis;
import Services.ServiceReclamation;
import Services.ServiceReponse;
import entities.Avis;
import entities.Reponse;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

  //      Reclamation r2 =new Reclamation(5,3,"klm","en_attente","probléme_produit");
        //Reclamation r3 =new Reclamation(3,"updated","en_attente","probléme_produit");
        ServiceReclamation sr = new ServiceReclamation();
        ServiceAvis sa = new ServiceAvis();
        Reponse rep1=new Reponse(3,"maandi manaamelek");
        Reponse rep3=new Reponse(6,"aaaaaaaaa");
        //Reponse rep2=new Reponse(2,2,"ooooo");

        Avis avis1=new Avis("yasmine", "dress", "woow",2);
        Avis avis2=new Avis(1,3,1,"hablih b rouge",5);


        //sa.add(avis1);
        sa.delete(avis1);


        System.out.println(sa.readAll());

        ServiceReponse ser=new ServiceReponse();


ser.add(rep3);

        //ser.delete(rep1);


       // sr.add(r1);
       // sr.delete(r2);
        //sr.update(r3);
System.out.println(sr.readAll());



        System.out.println(sr.readById(1));
        System.out.println(ser.readById(1));

        System.out.println(ser.repurerID_reclamation("klm"));

    }


}