package com.example.pidev_v1.services;



import com.example.pidev_v1.entities.Commande;
import com.example.pidev_v1.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface IService <T>{
    public void Add(T t );

    void Add(Commande commande);

    public List<T> Afficher ()throws SQLException;
    public void Delete (T t)throws SQLException;
    public void Modify (T t, String n)throws Exception;

    public void DeleteCommandebyRef(int Reference);

    public User getUserById(int t);


}
