package service;

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
}
