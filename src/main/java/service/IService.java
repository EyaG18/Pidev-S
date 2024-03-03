package service;

import java.time.LocalDate;
import java.util.List;
import entities.Fournisseur;
import entities.Offre;

public interface IService <T>{
    void add(T t);
    void delete(T t);
    void update(T t);
    List<T> readAll();




    //  Fournisseur readById(int id);
     T readById(int id);



    void updateOffreByTitreOffre(String oldTitreOffre, String newTitreOffre, LocalDate newDateDebut, LocalDate newDateFin, String newReduction, int newIdProduit);



    float getPrixAfterReduction(int idProduit);



}
