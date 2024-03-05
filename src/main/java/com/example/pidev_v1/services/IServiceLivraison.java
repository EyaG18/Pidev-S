package com.example.pidev_v1.services;

import java.util.List;


    public interface IServiceLivraison <T>{
        public void Add(T t );
        public List<T> Afficher () ;
        public void Modify (T t, String n)throws Exception;
        public void Delete (T t);

    }

