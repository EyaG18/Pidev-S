package services;

import java.util.List;

public interface CRUD<T> {
    public void add(T entity);
    public void remove(T entity);
    public void update(T entity);
    public List<T> afficher();
}
