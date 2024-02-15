package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Catégorie;

import java.util.List;

public interface ICategorie {

    void addCategory(Catégorie categorie);
    void UpdateCategory(int idC , String namec);
    void SearchCategoryById(int idC);
    void SearchCategoryByName(String namec);
    Catégorie GetCategoryById(int idC);
    void DeleteCategory(int idC);

    List<Catégorie> DisplayCategories();
    //public String GetCategoryNameById(int idC);
}
