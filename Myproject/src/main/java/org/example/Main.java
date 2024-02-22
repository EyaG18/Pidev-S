package org.example;

import Services.ServiceReclamation;
import entities.Reclamation;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

  //      Reclamation r2 =new Reclamation(5,3,"klm","en_attente","probléme_produit");
        Reclamation r3 =new Reclamation(3,"updated","en_attente","probléme_produit");
        ServiceReclamation sr = new ServiceReclamation();

       // sr.add(r1);
       // sr.delete(r2);
        //sr.update(r3);


        System.out.println(sr.readAll());

        System.out.println(sr.readById(1))
        ;

    }


}