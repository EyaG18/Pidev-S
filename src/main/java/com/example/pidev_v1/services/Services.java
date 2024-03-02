package com.example.pidev_v1.services;

import javafx.collections.ObservableList;

public interface Services <T> {


    public void add(T t);
    public T readById(int id);

    public default ObservableList<T> readAll() {
        return null;
    }
    public void update(T t);
    public void delete(T t);


}
