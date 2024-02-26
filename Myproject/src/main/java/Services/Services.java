package Services;

import java.util.List;

public interface Services <T>{
    void add(T t);
    void delete(T t);
   void update(T t);
   List<T> readAll();
   // T readById(int id);
}
