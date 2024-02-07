package services;

import java.sql.SQLException;
import java.util.List;

public interface IService <T>{
    public int Add(T t );
    public List<T> Afficher ();
    public void Delete (T t);
    public void Modify (T t);
    public T getById(int T);

}
