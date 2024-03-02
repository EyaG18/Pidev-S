package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.User;

import java.util.List;

public interface CRUD<T>   {
    public void add(T entity);
    public void remove(T entity);
    public void update(T entity);
    public List<T> afficher();

    public User RecuperUserById(int idu , List<User> users);

}
