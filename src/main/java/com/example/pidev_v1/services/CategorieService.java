package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Catégorie;
import com.example.pidev_v1.tools.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class CategorieService implements ICategorie{


    Connection cnx= null;
    Statement statement= null;
    private PreparedStatement ste;
    MyDataBase connect = new MyDataBase();

    public CategorieService() {
        cnx = connect.getCnx();
    }

    @Override
    public void addCategory(Catégorie categorie) {

    }

    @Override
    public void UpdateCategory(int idC, String namec) {

    }

    @Override
    public void SearchCategoryById(int idC) {

    }

    @Override
    public void SearchCategoryByName(String namec) {

    }
}
