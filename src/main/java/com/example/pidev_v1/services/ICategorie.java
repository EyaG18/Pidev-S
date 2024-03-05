package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Catégorie;
import javafx.collections.ObservableList;

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

    public void UpdateCategoryByName(String oldName, String newName);
    public void  DeleteCategoryByName(String namec);

    public int getCategoryIdFromName2(String selectedCategoryNameProduct);

    public String getCategoryNameById(int categoryId);

    public Catégorie getCategoryById(int categoryId);

    public Catégorie getCategoryBName(String chosenNameC);

    public boolean isCategoryNameExists(String nameCat);

    public ObservableList<Catégorie> getCategoryList();
}